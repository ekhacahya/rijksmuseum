package dev.ecbn.rijksmuseum.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.ecbn.rijksmuseum.base.BaseViewModel
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.data.remote.Response
import dev.ecbn.rijksmuseum.data.repo.art.IArtRepository
import kotlinx.coroutines.launch

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
class HomeViewModel(
    private val artRepo: IArtRepository
): BaseViewModel() {

    private val _artViewState: MutableLiveData<ArtViewState> = MutableLiveData()
    val artViewState: LiveData<ArtViewState> = _artViewState

    fun fetchArtCollections(){
        _artViewState.value = ArtViewState.Loading(true)
        viewModelScope.launch {
            artRepo.getArtCollections(object : Response<List<Art>> {
                override fun onSuccess(data: List<Art>) {
                    _artViewState.value = ArtViewState.Success(data)
                    _artViewState.value = ArtViewState.Loading(false)
                }

                override fun onError(message: String) {
                    _artViewState.value = ArtViewState.Error(message)
                    _artViewState.value = ArtViewState.Loading(false)
                }

            })
        }
    }
}