package dev.ecbn.rijksmuseum.view.register

import dev.ecbn.rijksmuseum.data.model.Account

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
sealed class RegistrationViewState {
    data class Success(val account: Account) : RegistrationViewState()
    data class Loading(val isLoading: Boolean) : RegistrationViewState()
    data class Error(val message: String) : RegistrationViewState()
    data class ValidationError(val type: String, val message: String) : RegistrationViewState()
}