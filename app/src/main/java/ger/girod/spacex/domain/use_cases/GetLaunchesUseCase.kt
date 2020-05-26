package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.util.ResultWrapper

interface GetLaunchesUseCase {
    suspend fun getAllLaunches(): ResultWrapper<List<LaunchModel>>
}