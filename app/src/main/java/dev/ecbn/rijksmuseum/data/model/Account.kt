package dev.ecbn.rijksmuseum.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
@Entity
@Parcelize
data class Account(
    @PrimaryKey
    var username: String,
    var password: String,
    var profileUrl: String = "https://picsum.photos/200"
) : Parcelable
