package dev.ecbn.rijksmuseum.di

import dev.ecbn.rijksmuseum.view.home.HomeViewModel
import dev.ecbn.rijksmuseum.view.login.LoginViewModel
import dev.ecbn.rijksmuseum.view.profile.ProfileViewModel
import dev.ecbn.rijksmuseum.view.register.RegisterViewModel
import dev.ecbn.rijksmuseum.view.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel() }
}