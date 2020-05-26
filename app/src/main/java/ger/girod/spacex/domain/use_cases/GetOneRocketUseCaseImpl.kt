package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.rocket.RocketOneModel
import ger.girod.spacex.domain.repository.SpaceXRepository
import ger.girod.spacex.domain.util.ResultWrapper

class GetOneRocketUseCaseImpl(private val spaceXRepository: SpaceXRepository) :
    GetOneRocketUseCase {

    override suspend fun getOneRocketUsecase(rocketId: String): ResultWrapper<RocketOneModel> {
        return spaceXRepository.getOneRocket(rocketId)
    }
}