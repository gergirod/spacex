package ger.girod.spacex.domain.models.launch

import com.google.gson.annotations.SerializedName

data class CoreModel(
    @SerializedName("core_serial") val coreSerial: String,
    val flight: String,
    val block: Int,
    val gridfins: Boolean,
    val legs: Boolean,
    val reused: Boolean,
    @SerializedName("land_success") val landSuccess: Boolean?,
    @SerializedName("landing_intent") val landingIntent: Boolean?,
    @SerializedName("landing_type") val landingType: String,
    @SerializedName("landing_vehicle") val landingVehicle: String
)