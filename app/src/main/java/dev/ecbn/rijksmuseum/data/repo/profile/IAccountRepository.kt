package dev.ecbn.rijksmuseum.data.repo.profile

import dev.ecbn.rijksmuseum.data.model.Account
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.data.remote.Response

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
interface IAccountRepository {
    fun registerAccount(username: String, password: String, response: Response<Account>)
    fun loginAccount(username: String, password: String, response: Response<Account>)
    fun getAccount()
}