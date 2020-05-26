package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.util.ResultWrapper

interface GetOneLaunchUseCase {
    suspend fun getOneLaunchUseCase(flightNumber: Int): ResultWrapper<LaunchModel>
}