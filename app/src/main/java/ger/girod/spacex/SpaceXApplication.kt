package ger.girod.spacex

import android.app.Application
import ger.girod.spacex.data.api.networkModule
import ger.girod.spacex.domain.repository.repositoryModule
import ger.girod.spacex.domain.use_cases.useCasesModules
import ger.girod.spacex.ui.utils.viewModelModules
import ger.girod.spacex.util.internetModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpaceXApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SpaceXApplication)
            modules(
                listOf(
                    networkModule,
                    useCasesModules,
                    internetModule,
                    viewModelModules,
                    repositoryModule
                )
            )
        }
    }
}