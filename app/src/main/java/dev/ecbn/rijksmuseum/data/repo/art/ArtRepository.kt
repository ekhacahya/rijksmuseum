package dev.ecbn.rijksmuseum.data.repo.art

import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.data.remote.ArtApiService
import dev.ecbn.rijksmuseum.data.remote.Response

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
class ArtRepository constructor(
    private val remote: ArtApiService
) : IArtRepository {

    override suspend fun getArtCollections(response: Response<List<Art>>) {
        //this is local data if you want to load local data just load it via response callback
        remote.getArtCollections()
            .suspendOnSuccess {
                val data = data.results ?: emptyList()
                response.onSuccess(data)
            }
            .suspendOnError {
                response.onError(raw.message)
            }
            .suspendOnFailure {
                response.onError(this.toString())
            }
            .suspendOnException {
                response.onError(message.toString())
            }
    }
}