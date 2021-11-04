package dev.ecbn.rijksmuseum.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ecbn.rijksmuseum.data.model.Art

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
@Dao
interface ArtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtCollections(movies: List<Art>)

    @Query("SELECT * FROM Art WHERE id = :id_")
    fun getArt(id_: Long): Art

    @Query("SELECT * FROM Art")
    fun getArtCollections(): List<Art>
}