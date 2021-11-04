package dev.ecbn.rijksmuseum.data.repo.art

import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.data.remote.Response

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
interface IArtRepository {
    suspend fun getArtCollections(response: Response<List<Art>>)
}