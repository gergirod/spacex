package ger.girod.spacex.domain.models.launch

import com.google.gson.annotations.SerializedName
import ger.girod.spacex.domain.models.rocket.RocketOneModel
import ger.girod.spacex.util.DateUtils

data class LaunchModel(

    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("launch_year") val launchYear: String,
    @SerializedName("mission_id") val missionId: List<String>,
    val upcoming: Boolean,
    @SerializedName("launch_date_unix") val launchDateUnix: Long,
    @SerializedName("launch_date_utc") val launchDateUtc: String,
    @SerializedName("launch_date_local") val launchDateLocal: String,
    @SerializedName("is_tentative") val isTentative: Boolean,
    @SerializedName("tentative_max_precision") val tentativeMaxPrecision: String,
    val tbd: Boolean,
    @SerializedName("launch_window") val launchWindow: Int,
    val rocket: RocketOneModel,
    val details: String,
    val links: LinksModel,
    @SerializedName("launch_success") val launchSuccess: Boolean,
    @SerializedName("launch_failure_reason") val launchFailureDetailsModel: LaunchFailureDetailsModel,
    @SerializedName("launch_site") val launchSite: LaunchSiteModel
) {

    fun getSuccessString(): String {
        return when (launchSuccess) {
            true -> "Succeeded"
            false -> "Failed"
        }
    }

    fun getStringDate(): String {
        return DateUtils.fromLongToString(launchDateUnix * 1000L)
    }

}