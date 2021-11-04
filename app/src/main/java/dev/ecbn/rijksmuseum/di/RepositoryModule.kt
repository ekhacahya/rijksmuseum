package dev.ecbn.rijksmuseum.di

import dev.ecbn.rijksmuseum.data.repo.art.ArtRepository
import dev.ecbn.rijksmuseum.data.repo.art.IArtRepository
import dev.ecbn.rijksmuseum.data.repo.profile.AccountRepository
import dev.ecbn.rijksmuseum.data.repo.profile.IAccountRepository
import org.koin.dsl.module

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
val repositoryModule = module {
    single<IArtRepository> { ArtRepository(get()) }
    single<IAccountRepository> { AccountRepository(get()) }
}