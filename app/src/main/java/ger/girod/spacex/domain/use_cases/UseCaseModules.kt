package ger.girod.spacex.domain.use_cases

import ger.girod.spacex.domain.repository.SpaceXRepository
import org.koin.dsl.module

fun provideLaunchesUseCase(spaceXRepository: SpaceXRepository): GetLaunchesUseCase {
    return GetLaunchesUseCaseImpl(spaceXRepository)
}

fun provideOneLaunchUseCase(spaceXRepository: SpaceXRepository): GetOneLaunchUseCase {
    return GetOneLaunchUseCaseImpl(spaceXRepository)
}

fun provideOneRockerUseCase(spaceXRepository: SpaceXRepository): GetOneRocketUseCase {
    return GetOneRocketUseCaseImpl(spaceXRepository)
}

val useCasesModules = module {
    factory { provideLaunchesUseCase(get()) }
    factory { provideOneLaunchUseCase(get()) }
    factory { provideOneRockerUseCase(get()) }
}