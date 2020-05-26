package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.repository.SpaceXRepository
import ger.girod.spacex.domain.util.ResultWrapper

class GetLaunchesUseCaseImpl(private val spaceXRepository: SpaceXRepository) : GetLaunchesUseCase {

    override suspend fun getAllLaunches(): ResultWrapper<List<LaunchModel>> {
        return spaceXRepository.getAllLaunches()
    }
}