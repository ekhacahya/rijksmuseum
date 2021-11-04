package dev.ecbn.rijksmuseum.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dev.ecbn.rijksmuseum.BuildConfig
import dev.ecbn.rijksmuseum.data.remote.ArtApiService
import dev.ecbn.rijksmuseum.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ArtApiService::class.java)
    }
}