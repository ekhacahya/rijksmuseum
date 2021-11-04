package dev.ecbn.rijksmuseum.view.login

import dev.ecbn.rijksmuseum.data.model.Account

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
sealed class LoginViewState {
    data class Success(val account: Account) : LoginViewState()
    data class Loading(val isLoading: Boolean) : LoginViewState()
    data class Error(val message: String) : LoginViewState()
    data class ValidationError(val type: String, val message: String) : LoginViewState()
}