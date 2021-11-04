package dev.ecbn.rijksmuseum.di

import androidx.room.Room
import dev.ecbn.rijksmuseum.R
import dev.ecbn.rijksmuseum.data.local.LocalDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
val localDBModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            LocalDB::class.java,
            androidApplication().getString(R.string.database)
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<LocalDB>().artDao() }
    single { get<LocalDB>().accountDao() }
}