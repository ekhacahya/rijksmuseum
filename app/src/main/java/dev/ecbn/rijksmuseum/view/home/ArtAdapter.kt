package dev.ecbn.rijksmuseum.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.ecbn.rijksmuseum.data.model.Art
import dev.ecbn.rijksmuseum.databinding.ItemArtVerticalBinding

/**
 * Created by Eka Cahya Nugraha on 04/11/21.
 * ecbn95@gmail.com
 */
class ArtAdapter(val listener: ArtSelectedListener) :
    RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {

    var mListMove: List<Art> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArtViewHolder(ItemArtVerticalBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        (holder as Binder<Art>).bind(mListMove[position])
        holder.itemView.tag = mListMove[position]
    }

    override fun getItemCount() = mListMove.size

    fun setItem(list: List<Art>) {
        this.mListMove = list
        notifyDataSetChanged()
    }

    inner class ArtViewHolder(private val binding: ItemArtVerticalBinding) :
        RecyclerView.ViewHolder(binding.root),
        Binder<Art> {
        override fun bind(data: Art) {
            binding.apply {
                tvTitle.text = data.title
                ivPoster.load(data.webImage.url)
                root.setOnClickListener {
                    listener.onArtSelected(data)
                }
            }
        }
    }
}

interface Binder<T> {
    fun bind(data: T)
}

interface ArtSelectedListener {
    fun onArtSelected(art: Art)
}