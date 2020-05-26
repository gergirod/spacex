package ger.girod.spacex.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.use_cases.GetLaunchesUseCase
import ger.girod.spacex.domain.util.ResultWrapper
import ger.girod.spacex.ui.models.LaunchListItem
import ger.girod.spacex.ui.models.ListItem
import ger.girod.spacex.ui.utils.ScreenState
import ger.girod.spacex.ui.utils.SortType
import ger.girod.spacex.util.MapUtils
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModel(private val getLaunchesUseCase: GetLaunchesUseCase) : ViewModel(),
    KoinComponent {

    private var launchList: ArrayList<LaunchModel> = ArrayList()
    private var filterList: ArrayList<LaunchModel> = ArrayList()
    var launchData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val errorData: MutableLiveData<String> = MutableLiveData()
    var screenStateData: MutableLiveData<ScreenState> = MutableLiveData()
    var removeFilterdata: MutableLiveData<Boolean> = MutableLiveData()
    private var sortType: SortType? = null
    private var isFilter = false

    fun getLaunches() {

        viewModelScope.launch {

            screenStateData.value = ScreenState.Loading
            when (val response = getLaunchesUseCase.getAllLaunches()) {
                is ResultWrapper.Success -> {
                    val listMapped = response.value.map { LaunchListItem(it) }
                    launchList.addAll(response.value)
                    launchData.value = listMapped
                }
                is ResultWrapper.Error -> errorData.value = response.errorMessage
            }
            screenStateData.value = ScreenState.LoadingFinish

        }

    }

    fun filterByLaunchSuccess() {
        isFilter = true
        filterList = launchList
        filterList =
            filterList.filter { launchModel -> launchModel.launchSuccess } as ArrayList<LaunchModel>

        when (sortType) {
            SortType.MissionName -> {
                launchData.value =
                    MapUtils.mapListIntoGroupBySortType(filterList, SortType.MissionName)
            }
            SortType.LaunchDate -> {
                launchData.value =
                    MapUtils.mapListIntoGroupBySortType(filterList, SortType.LaunchDate)
            }
            else -> {
                launchData.value = filterList.map { LaunchListItem(it) }
            }
        }

        removeFilterdata.value = isFilter
    }

    fun removeFilter() {
        isFilter = false
        when (sortType) {
            SortType.MissionName -> {
                launchData.value =
                    MapUtils.mapListIntoGroupBySortType(launchList, SortType.MissionName)
            }
            SortType.LaunchDate -> {
                launchData.value =
                    MapUtils.mapListIntoGroupBySortType(launchList, SortType.LaunchDate)
            }
            else -> {
                launchData.value = launchList.map { LaunchListItem(it) }
            }
        }

        removeFilterdata.value = isFilter
    }

    fun sortByLaunchDate() {
        sortType = SortType.LaunchDate
        launchList.sortBy { it.launchYear }
        val list = if (isFilter) filterList else launchList
        launchData.value = MapUtils.mapListIntoGroupBySortType(list, SortType.LaunchDate)
    }

    fun sortByDescendingDate() {
        sortType = SortType.LaunchDate
        launchList.sortByDescending { it.launchYear }
        val list = if (isFilter) filterList else launchList
        launchData.value = MapUtils.mapListIntoGroupBySortType(list, SortType.LaunchDate)
    }

    fun sortByMissionName() {
        sortType = SortType.MissionName
        launchList.sortBy { it.missionName }
        val list = if (isFilter) filterList else launchList
        launchData.value = MapUtils.mapListIntoGroupBySortType(list, SortType.MissionName)
    }

    fun sortByMissionNameDescending() {
        sortType = SortType.MissionName
        launchList.sortByDescending { it.missionName }
        val list = if (isFilter) filterList else launchList
        launchData.value = MapUtils.mapListIntoGroupBySortType(list, SortType.MissionName)
    }

}
