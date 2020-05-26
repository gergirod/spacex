package ger.girod.spacex.domain.repository

import org.koin.dsl.module

val repositoryModule = module {
    factory { SpaceXRepository(get(), get()) }
}