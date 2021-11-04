package dev.ecbn.rijksmuseum.data.remote

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
interface Response<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}