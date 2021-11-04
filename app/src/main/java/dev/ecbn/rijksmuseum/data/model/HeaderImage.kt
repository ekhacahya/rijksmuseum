package dev.ecbn.rijksmuseum.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaderImage(
    @SerializedName("guid") var guid: String,
    @SerializedName("url") var url: String
): Parcelable