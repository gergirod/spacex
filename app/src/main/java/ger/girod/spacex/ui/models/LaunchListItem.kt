package ger.girod.spacex.ui.models

import ger.girod.spacex.domain.models.launch.LaunchModel

class LaunchListItem(private val launchModel: LaunchModel) : ListItem() {

    override fun getType(): Int {
        return GENERAL_TYPE
    }

    fun getLaunch(): LaunchModel {
        return launchModel
    }
}