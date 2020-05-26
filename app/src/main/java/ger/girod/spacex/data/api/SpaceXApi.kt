package ger.girod.spacex.data.api

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.models.rocket.RocketOneModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET("/v3/launches")
    suspend fun getAllLaunches(): List<LaunchModel>

    @GET("/v3/launches")
    suspend fun getTest(): String

    @GET("launches/{flight_number}")
    suspend fun getOneLaunch(@Path("flight_number") flightNumber: Int): LaunchModel

    @GET("rockets/{rocket_id}")
    suspend fun getOneRocket(@Path("rocket_id") rocketId: String): RocketOneModel

}