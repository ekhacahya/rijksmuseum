package dev.ecbn.rijksmuseum.view.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import coil.load
import coil.transform.CircleCropTransformation
import dev.ecbn.rijksmuseum.R
import dev.ecbn.rijksmuseum.base.BaseActivity
import dev.ecbn.rijksmuseum.databinding.ActivityMainBinding
import dev.ecbn.rijksmuseum.databinding.NavHeaderBinding
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.view.profile.ProfileViewModel
import dev.ecbn.rijksmuseum.view.profile.ProfileViewState

class MainActivity : BaseActivity<ProfileViewModel, ActivityMainBinding>(
    ProfileViewModel::class,
    ActivityMainBinding::inflate
) {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        return@lazy Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupDrawerLayout()
    }

    private fun setupDrawerLayout() {
        with(binding) {
            setSupportActionBar(toolbar)
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.homeFragment, R.id.profileFragment),
                drawerLayout
            )
            navView.setupWithNavController(navController)
            NavigationUI.setupActionBarWithNavController(
                this@MainActivity,
                navController,
                appBarConfiguration
            )
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(profileViewState, ::onProfileReceived)
            getAccount()
        }
    }

    private fun onProfileReceived(data: ProfileViewState) {
        when (data) {
            is ProfileViewState.Success -> {
                with(binding) {
                    if (navView.headerCount > 0) {
                        val headerView: View = navView.getHeaderView(0)
                        val headerBinding: NavHeaderBinding = NavHeaderBinding.bind(headerView)
                        with(headerBinding) {
                            ivProfile.load(data.account.profileUrl) {
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
                            tvWelcome.text = String.format(getString(R.string.message_welcome), data.account.username)
                        }
                    }
                }
            }
            is ProfileViewState.Loading -> {

            }
        }
    }
    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}