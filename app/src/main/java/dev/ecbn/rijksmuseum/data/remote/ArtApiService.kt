package dev.ecbn.rijksmuseum.data.remote

import com.skydoves.sandwich.ApiResponse
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.data.model.ArtCollectionResponse
import dev.ecbn.rijksmuseum.data.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
interface ArtApiService {
    @GET("collection")
    suspend fun getArtCollections(): ApiResponse<DataResponse<List<Art>>>
}