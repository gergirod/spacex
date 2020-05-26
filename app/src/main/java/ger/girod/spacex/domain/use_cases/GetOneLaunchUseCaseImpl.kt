package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.repository.SpaceXRepository
import ger.girod.spacex.domain.util.ResultWrapper

class GetOneLaunchUseCaseImpl(private val spaceXRepository: SpaceXRepository) :
    GetOneLaunchUseCase {

    override suspend fun getOneLaunchUseCase(flightNumber: Int): ResultWrapper<LaunchModel> {
        return spaceXRepository.getOneLaunch(flightNumber)
    }
}