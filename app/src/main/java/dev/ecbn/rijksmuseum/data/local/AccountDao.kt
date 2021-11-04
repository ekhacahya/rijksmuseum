package dev.ecbn.rijksmuseum.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ecbn.rijksmuseum.data.model.Account

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Account)

    @Query("SELECT * FROM Account WHERE username = :username")
    fun getAccount(username: String): Account?

    @Query("SELECT * FROM Account")
    fun getArtCollections(): List<Account>
}