package dev.ecbn.rijksmuseum.view.home

import dev.ecbn.rijksmuseum.data.model.Art

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
sealed class ArtViewState {
    data class Success(val artCollections: List<Art>): ArtViewState()
    data class Loading(val isLoading: Boolean): ArtViewState()
    data class Error(val message: String): ArtViewState()
}
