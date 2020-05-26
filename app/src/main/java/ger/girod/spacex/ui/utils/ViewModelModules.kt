package ger.girod.spacex.ui.utils

import ger.girod.spacex.ui.detail.DetailViewModel
import ger.girod.spacex.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModules = module {
    factory { MainViewModel(get()) }
    factory { DetailViewModel(get(), get()) }
}