package dev.ecbn.rijksmuseum.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
data class ArtCollectionResponse(
    @SerializedName("artObjects") var artObjects: List<Art>
)

open class BaseResponse(
    @SerializedName("count") var count: Int = 1,
    @SerializedName("elapsedMilliseconds") var elapsedMilliseconds: Int = 0,
)

data class DataResponse<T>(
    @SerializedName("artObjects") var results: T?
): BaseResponse()
