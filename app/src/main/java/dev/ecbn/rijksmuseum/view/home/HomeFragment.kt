package dev.ecbn.rijksmuseum.view.home

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.ecbn.rijksmuseum.base.BaseFragment
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.databinding.FragmentHomeBinding
import dev.ecbn.rijksmuseum.utils.SpacesItemDecoration
import dev.ecbn.rijksmuseum.utils.observe
import dev.ecbn.rijksmuseum.utils.showIf
import dev.ecbn.rijksmuseum.utils.toast
import dev.ecbn.rijksmuseum.view.detail.ART_DATA
import dev.ecbn.rijksmuseum.view.detail.DetailActivity

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    HomeViewModel::class,
    FragmentHomeBinding::inflate
), ArtSelectedListener {

    private val artAdapter: ArtAdapter by lazy {
        return@lazy ArtAdapter(this)
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvArt.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                itemAnimator = DefaultItemAnimator()
                if (itemDecorationCount == 0) addItemDecoration(SpacesItemDecoration(16))
                setHasFixedSize(true)
                adapter = artAdapter
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(artViewState, ::onArtViewStateReceived)
            fetchArtCollections()
        }
    }

    private fun onArtViewStateReceived(artViewState: ArtViewState) {
        when (artViewState) {
            is ArtViewState.Success -> {
                artAdapter.setItem(artViewState.artCollections)
            }
            is ArtViewState.Error -> {
                binding.tvErrMessage.text = artViewState.message
            }
            is ArtViewState.Loading -> {
                binding.pbLoading.showIf(artViewState.isLoading)
            }
        }
    }

    override fun onArtSelected(art: Art) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(ART_DATA, art)
        startActivity(intent)
    }
}