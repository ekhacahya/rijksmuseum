package dev.ecbn.rijksmuseum.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import dev.ecbn.rijksmuseum.base.BaseActivity
import dev.ecbn.rijksmuseum.databinding.ActivitySplashBinding
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.view.main.MainActivity
import dev.ecbn.rijksmuseum.view.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>(
    SplashViewModel::class,
    ActivitySplashBinding::inflate
) {

    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {
        with(viewModel) {
            observe(isLoggedIn, ::onLoggedInReceived)
            checkSession()
        }
    }

    private fun onLoggedInReceived(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            gotoMain()
        } else {
            gotoAuth()
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun gotoAuth() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}