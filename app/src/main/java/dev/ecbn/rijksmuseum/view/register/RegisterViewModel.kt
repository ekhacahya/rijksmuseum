package dev.ecbn.rijksmuseum.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.ecbn.rijksmuseum.base.BaseViewModel
import dev.ecbn.rijksmuseum.data.model.Account
import dev.ecbn.rijksmuseum.data.remote.Response
import dev.ecbn.rijksmuseum.data.repo.profile.IAccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
class RegisterViewModel(
    private val accountRepo: IAccountRepository
) : BaseViewModel() {
    private val _registrationViewState: MutableLiveData<RegistrationViewState> = MutableLiveData()
    val registrationViewState: LiveData<RegistrationViewState> = _registrationViewState

    fun validate(username: String, password: String, isAgree: Boolean) {
        if (username.isEmpty()) {
            _registrationViewState.postValue(
                RegistrationViewState.ValidationError(
                    "username",
                    "Username should not be empty"
                )
            )
            return
        }
        if (password.isEmpty()) {
            _registrationViewState.postValue(
                RegistrationViewState.ValidationError(
                    "password",
                    "Password should not be empty"
                )
            )
            return
        }
        if (!isAgree) {
            _registrationViewState.postValue(
                RegistrationViewState.ValidationError(
                    "agreement",
                    "Agreement should be checked"
                )
            )
            return
        }
        _registrationViewState.postValue(RegistrationViewState.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            accountRepo.registerAccount(username, password, object : Response<Account> {
                override fun onSuccess(data: Account) {
                    sessionHelper.setLogin(true)
                    sessionHelper.setAccount(data)
                    _registrationViewState.postValue(RegistrationViewState.Success(data))
                }

                override fun onError(message: String) {
                    _registrationViewState.postValue(RegistrationViewState.Error(message))
                }
            })
        }
    }
}