package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.rocket.RocketOneModel
import ger.girod.spacex.domain.util.ResultWrapper

interface GetOneRocketUseCase {
    suspend fun getOneRocketUsecase(rocketId: String): ResultWrapper<RocketOneModel>
}