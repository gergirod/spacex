package ger.girod.spacex.util

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val internetModule = module {
    single { NetworkHandler(androidContext()) }
}