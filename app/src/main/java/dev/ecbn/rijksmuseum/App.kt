package dev.ecbn.rijksmuseum

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.skydoves.sandwich.SandwichInitializer
import dev.ecbn.rijksmuseum.data.remote.GlobalResponseOperator
import dev.ecbn.rijksmuseum.di.localDBModule
import dev.ecbn.rijksmuseum.di.networkModule
import dev.ecbn.rijksmuseum.di.repositoryModule
import dev.ecbn.rijksmuseum.di.viewModelModule
import dev.ecbn.rijksmuseum.utils.timberInitialization
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(repositoryModule)
            modules(localDBModule)
            modules(viewModelModule)
        }
        Hawk.init(this).build()
        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)
        timberInitialization()
    }
}