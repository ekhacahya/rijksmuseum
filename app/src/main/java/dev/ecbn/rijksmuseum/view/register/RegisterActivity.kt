package dev.ecbn.rijksmuseum.view.register

import android.os.Bundle
import android.view.MenuItem
import dev.ecbn.rijksmuseum.base.BaseActivity
import dev.ecbn.rijksmuseum.databinding.ActivityRegisterBinding
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.utils.snackBarDismissAction
import dev.ecbn.rijksmuseum.utils.toast

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>(
    RegisterViewModel::class,
    ActivityRegisterBinding::inflate
) {

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            btnRegister.setOnClickListener {
                viewModel.validate(
                    tilUsername.editText?.text.toString(),
                    tilPassword.editText?.text.toString(),
                    chkConfirm.isChecked
                )
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(registrationViewState, ::onRegistrationViewStateReceived)
        }
    }

    private fun onRegistrationViewStateReceived(state: RegistrationViewState) {
        when (state) {
            is RegistrationViewState.ValidationError -> {
                removeError()
                with(binding) {
                    when (state.type) {
                        "username" -> tilUsername.error = state.message
                        "password" -> tilPassword.error = state.message
                        else -> mRootView?.snackBarDismissAction("OK", state.message)
                    }
                }
            }
            is RegistrationViewState.Loading -> {
                removeError()
            }
            is RegistrationViewState.Error -> {
                mRootView?.snackBarDismissAction("OK", state.message)
            }
            is RegistrationViewState.Success -> {
                toast("Registration successfully, please login!")
                finish()
            }
        }
    }

    private fun removeError() {
        with(binding) {
            tilUsername.error = ""
            tilPassword.error = ""
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}