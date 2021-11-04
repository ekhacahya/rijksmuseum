package dev.ecbn.rijksmuseum.view.profile

import dev.ecbn.rijksmuseum.data.model.Account

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
sealed class ProfileViewState {
    data class Success(val account: Account) : ProfileViewState()
    data class Loading(val isLoading: Boolean) : ProfileViewState()
    data class Error(val message: String) : ProfileViewState()
    object Logout : ProfileViewState()
}