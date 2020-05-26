package ger.girod.spacex.domain.repository

import ger.girod.spacex.data.api.SpaceXApi
import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.models.rocket.RocketOneModel
import ger.girod.spacex.domain.repository.util.executeNetworkRequest
import ger.girod.spacex.domain.util.ResultWrapper
import ger.girod.spacex.util.NetworkHandler
import kotlinx.coroutines.Dispatchers

class SpaceXRepository(
    private val networkHandler: NetworkHandler,
    private val spaceXApi: SpaceXApi
) {


    suspend fun getAllLaunches(): ResultWrapper<List<LaunchModel>> {
        return executeNetworkRequest(Dispatchers.IO, networkHandler.isInternetAvailable()) {
            spaceXApi.getAllLaunches()
        }
    }

    suspend fun getOneLaunch(flightNumber: Int): ResultWrapper<LaunchModel> {
        return executeNetworkRequest(Dispatchers.IO, networkHandler.isInternetAvailable()) {
            spaceXApi.getOneLaunch(flightNumber)
        }
    }

    suspend fun getOneRocket(rocketId: String): ResultWrapper<RocketOneModel> {
        return executeNetworkRequest(Dispatchers.IO, networkHandler.isInternetAvailable()) {
            spaceXApi.getOneRocket(rocketId)
        }
    }

}