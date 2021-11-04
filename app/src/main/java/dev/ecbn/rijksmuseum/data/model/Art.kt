package dev.ecbn.rijksmuseum.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.ecbn.rijksmuseum.data.local.HeaderImageConverter
import dev.ecbn.rijksmuseum.data.local.WebImageConverter
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Art(
    @SerializedName("hasImage") var hasImage: Boolean,
    @SerializedName("headerImage") var headerImage: HeaderImage,
    @PrimaryKey
    @SerializedName("id") var id: String,
    @SerializedName("longTitle") var longTitle: String,
    @SerializedName("principalOrFirstMaker") var principalOrFirstMaker: String,
    @SerializedName("title") var title: String,
    @SerializedName("webImage") var webImage: WebImage
): Parcelable