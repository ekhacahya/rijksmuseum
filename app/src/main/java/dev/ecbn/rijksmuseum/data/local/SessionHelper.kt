package dev.ecbn.rijksmuseum.data.local

import com.orhanobut.hawk.Hawk
import dev.ecbn.rijksmuseum.data.model.Account

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */

const val IS_LOGIN = "is_login"
const val ACCOUNT = "logged_in_account"

class SessionHelper {
    fun setLogin(isLogin: Boolean) = Hawk.put(IS_LOGIN, isLogin)

    fun isLogin(): Boolean = Hawk.get(IS_LOGIN, false)

    fun setAccount(account: Account) = Hawk.put(ACCOUNT, account)

    fun getAccount(): Account = Hawk.get(ACCOUNT)

    fun deleteSession(key: String) = Hawk.delete(key)

    fun clearSession() = Hawk.deleteAll()
}