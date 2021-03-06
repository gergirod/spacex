package ger.girod.spacex.util

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.ui.models.HeaderListItem
import ger.girod.spacex.ui.models.LaunchListItem
import ger.girod.spacex.ui.models.ListItem
import ger.girod.spacex.ui.utils.SortType

object MapUtils {

    fun mapListIntoGroupBySortType(list: List<LaunchModel>, sortType: SortType): List<ListItem> {

        val map = groupDataIntoHashMap(list, sortType)
        var itemlist: ArrayList<ListItem> = ArrayList()
        for ((key, value) in map) {
            itemlist.add(HeaderListItem(key))
            for (launch in value) {
                itemlist.add(LaunchListItem(launch))
            }
        }

        return itemlist
    }


    private fun groupDataIntoHashMap(
        list: List<LaunchModel>,
        sortType: SortType
    ): LinkedHashMap<String, ArrayList<LaunchModel>> {

        var linkedHashMap: LinkedHashMap<String, ArrayList<LaunchModel>> = LinkedHashMap()

        for (launch in list) {

            var key = when (sortType) {
                SortType.MissionName -> launch.missionName[0].toString()
                SortType.LaunchDate -> launch.launchYear
            }

            if (linkedHashMap.containsKey(key)) {
                linkedHashMap[key]?.add(launch)
            } else {
                var itemlist: ArrayList<LaunchModel> = ArrayList()
                itemlist.add(launch)
                linkedHashMap[key] = itemlist
            }
        }

        return linkedHashMap
    }
}