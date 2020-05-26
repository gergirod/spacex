package ger.girod.spacex.domain.models.launch

data class LaunchFailureDetailsModel(
    val time: Int,
    val altitude: Double,
    val reason: String
)