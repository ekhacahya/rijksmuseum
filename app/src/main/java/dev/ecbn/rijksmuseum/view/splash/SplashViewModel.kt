package dev.ecbn.rijksmuseum.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.ecbn.rijksmuseum.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
class SplashViewModel : BaseViewModel() {

    private val _isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkSession(){
        viewModelScope.launch {
            delay(1000)
            _isLoggedIn.postValue(sessionHelper.isLogin())
        }
    }
}