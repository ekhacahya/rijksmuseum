package dev.ecbn.rijksmuseum.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.ecbn.rijksmuseum.base.BaseViewModel

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
class ProfileViewModel : BaseViewModel() {
    private val _profileViewState: MutableLiveData<ProfileViewState> = MutableLiveData()
    val profileViewState: LiveData<ProfileViewState> = _profileViewState

    fun getAccount(){
        val account = sessionHelper.getAccount()
        _profileViewState.postValue(ProfileViewState.Success(account))
    }

    fun logout(){
        sessionHelper.clearSession()
        _profileViewState.postValue(ProfileViewState.Logout)
    }
}