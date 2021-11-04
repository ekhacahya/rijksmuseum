package dev.ecbn.rijksmuseum.data.repo.profile

import dev.ecbn.rijksmuseum.data.local.AccountDao
import dev.ecbn.rijksmuseum.data.model.Account
import dev.ecbn.rijksmuseum.data.remote.Response
import dev.ecbn.rijksmuseum.utils.logInfo

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
class AccountRepository constructor(
    private val local: AccountDao
) : IAccountRepository {
    override fun registerAccount(
        username: String,
        password: String,
        response: Response<Account>
    ) {
        val user = local.getAccount(username)
        if (user == null) {
            local.insertAccount(Account(username, password))
            val checkUser = local.getAccount(username)
            if (checkUser != null) response.onSuccess(checkUser)
            else response.onError("Registration failed")
        } else {
            response.onError("User already exist")
        }
    }

    override fun loginAccount(
        username: String,
        password: String,
        response: Response<Account>
    ) {
        val user = local.getAccount(username)
        logInfo("USER -> $user")
        if (user == null) {
            response.onError("User not found")
            return
        }
        if (user.password == password) {
            logInfo("USER ->onSuccess $user")
            response.onSuccess(user)
        } else {
            response.onError("Wrong password")
        }
    }

    override fun getAccount() {

    }
}