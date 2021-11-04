package dev.ecbn.rijksmuseum.view.login

import android.content.Intent
import android.os.Bundle
import dev.ecbn.rijksmuseum.BuildConfig
import dev.ecbn.rijksmuseum.base.BaseActivity
import dev.ecbn.rijksmuseum.databinding.ActivityLoginBinding
import dev.ecbn.rijksmuseum.utils.logInfo
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.utils.snackBarDismissAction
import dev.ecbn.rijksmuseum.view.main.MainActivity
import dev.ecbn.rijksmuseum.view.register.RegisterActivity

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(
    LoginViewModel::class,
    ActivityLoginBinding::inflate
) {

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            setSupportActionBar(toolbar)
            btnLogin.setOnClickListener {
                viewModel.validate(
                    tilUsername.editText?.text.toString(),
                    tilPassword.editText?.text.toString()
                )
            }
            tvRegister.setOnClickListener {
                gotoRegister()
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(loginViewState, ::onLoginViewStateReceived)
        }
    }

    private fun onLoginViewStateReceived(state: LoginViewState) {
        logInfo("onLoginViewStateReceived $state")
        when (state) {
            is LoginViewState.ValidationError -> {
                removeError()
                with(binding) {
                    if (state.type == "username") tilUsername.error = state.message
                    else tilPassword.error = state.message
                }
            }
            is LoginViewState.Loading -> {
                removeError()
            }
            is LoginViewState.Error -> {
                mRootView?.snackBarDismissAction("OK", state.message)
            }
            is LoginViewState.Success -> {
                gotoMain()
            }
        }
    }

    private fun removeError() {
        with(binding) {
            tilUsername.error = ""
            tilPassword.error = ""
        }
    }

    private fun gotoRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}