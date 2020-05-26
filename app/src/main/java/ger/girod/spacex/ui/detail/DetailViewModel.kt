package ger.girod.spacex.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ger.girod.spacex.domain.use_cases.GetOneLaunchUseCase
import ger.girod.spacex.domain.use_cases.GetOneRocketUseCase
import ger.girod.spacex.domain.util.ResultWrapper
import ger.girod.spacex.ui.models.DetailModel
import ger.girod.spacex.ui.utils.ScreenState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getOneLaunchUseCase: GetOneLaunchUseCase,
    private val getOneRocketUseCase: GetOneRocketUseCase
) : ViewModel() {


    var screenData: MutableLiveData<ScreenState> = MutableLiveData()
    var detailData: MutableLiveData<DetailModel> = MutableLiveData()
    var errorData: MutableLiveData<String> = MutableLiveData()


    fun getLaunchDetails(flightNumber: Int?, rocketId: String?) {

        viewModelScope.launch {

            screenData.value = ScreenState.Loading
            val responseLaunch = async { getOneLaunchUseCase.getOneLaunchUseCase(flightNumber!!) }
            val responseRocket = async { getOneRocketUseCase.getOneRocketUsecase(rocketId!!) }

            val finalResponseLaunch = responseLaunch.await()
            val finalResponseRocket = responseRocket.await()

            if (finalResponseLaunch is ResultWrapper.Success && finalResponseRocket is ResultWrapper.Success) {

                val viewResponse = DetailModel(finalResponseLaunch.value, finalResponseRocket.value)
                detailData.postValue(viewResponse)

            } else if (finalResponseLaunch is ResultWrapper.Error && finalResponseRocket is ResultWrapper.Error) {
                errorData.value = finalResponseLaunch.errorMessage

            } else if (finalResponseLaunch is ResultWrapper.Success && finalResponseRocket is ResultWrapper.Error) {

                val viewResponse = DetailModel(finalResponseLaunch.value, null)
                detailData.value = viewResponse
                errorData.value = "error loading rocket data"

            } else if (finalResponseLaunch is ResultWrapper.Error && finalResponseRocket is ResultWrapper.Success) {
                val viewResponse = DetailModel(null, finalResponseRocket.value)
                detailData.value = viewResponse
                errorData.value = "error loading launch data"
            }

            screenData.value = ScreenState.LoadingFinish
        }

    }
}