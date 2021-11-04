package dev.ecbn.rijksmuseum.view.detail

import android.os.Bundle
import android.view.MenuItem
import coil.load
import dev.ecbn.rijksmuseum.base.BaseActivity
import dev.ecbn.rijksmuseum.base.BaseViewModel
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.databinding.ActivityDetailBinding

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
const val ART_DATA = "art_data"
class DetailActivity : BaseActivity<BaseViewModel, ActivityDetailBinding>(
    BaseViewModel::class,
    ActivityDetailBinding::inflate
) {

    private val art: Art? by lazy {
        return@lazy mDataReceived?.getParcelable<Art>(ART_DATA)
    }
    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding){
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            tvTitle.text = art?.title
            ivArt.load(art?.webImage?.url)
        }
    }

    override fun onInitData() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}