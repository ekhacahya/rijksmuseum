package dev.ecbn.rijksmuseum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.ecbn.rijksmuseum.data.model.Account
import dev.ecbn.rijksmuseum.data.model.Art

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
@Database(entities = [Art::class, Account::class], version = 1, exportSchema = true)
@TypeConverters(HeaderImageConverter::class, WebImageConverter::class)
abstract class LocalDB: RoomDatabase() {
    abstract fun artDao(): ArtDao
    abstract fun accountDao(): AccountDao
}