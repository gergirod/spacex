package ger.girod.spacex.data.api

import org.koin.dsl.module

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideSpaceXApi(get()) }
    single { provideRetrofit(get()) }
}