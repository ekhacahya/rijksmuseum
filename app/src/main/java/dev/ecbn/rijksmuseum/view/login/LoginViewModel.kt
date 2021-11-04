package dev.ecbn.rijksmuseum.view.login

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
class LoginViewModel(
    private val accountRepo: IAccountRepository
) : BaseViewModel() {

    private val _loginViewState: MutableLiveData<LoginViewState> = MutableLiveData()
    val loginViewState: LiveData<LoginViewState> = _loginViewState

    fun validate(username: String, password: String) {
        if (username.isEmpty()) {
            _loginViewState.postValue(
                LoginViewState.ValidationError(
                    "username",
                    "Username should not be empty"
                )
            )
            return
        }
        if (password.isEmpty()) {
            _loginViewState.postValue(
                LoginViewState.ValidationError(
                    "password",
                    "Password should not be empty"
                )
            )
            return
        }
        _loginViewState.postValue(LoginViewState.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            accountRepo.loginAccount(username, password, object : Response<Account> {
                override fun onSuccess(data: Account) {
                    sessionHelper.setLogin(true)
                    sessionHelper.setAccount(data)
                    _loginViewState.postValue(LoginViewState.Success(data))
                }

                override fun onError(message: String) {
                    _loginViewState.postValue(LoginViewState.Error(message))
                }
            })
        }
    }
}