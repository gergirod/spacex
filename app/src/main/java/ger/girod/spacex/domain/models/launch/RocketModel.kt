package ger.girod.spacex.domain.models.launch

import com.google.gson.annotations.SerializedName

data class RocketModel(
    @SerializedName("rocket_id") val rocketId: String,
    @SerializedName("rocket_name") val rocketName: String,
    @SerializedName("rocket_type") val rocketType: String
)