package dev.ecbn.rijksmuseum.view.profile

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import dev.ecbn.rijksmuseum.base.BaseFragment
import dev.ecbn.rijksmuseum.databinding.FragmentProfileBinding
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.view.splash.SplashActivity

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    ProfileViewModel::class,
    FragmentProfileBinding::inflate
) {
    override fun onInitUI(savedInstanceState: Bundle?) {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(profileViewState, ::onAccountReceived)
            getAccount()
        }
    }

    private fun onAccountReceived(state: ProfileViewState) {
        with(binding) {
            when (state) {
                is ProfileViewState.Success -> {
                    tvUsername.text = state.account.username
                    ivProfile.load(state.account.profileUrl) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
                is ProfileViewState.Logout -> {
                    gotoSplash()
                }
                else -> {

                }
            }
        }
    }

    private fun gotoSplash() {
        with(requireActivity()) {
            val intent = Intent(this, SplashActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}