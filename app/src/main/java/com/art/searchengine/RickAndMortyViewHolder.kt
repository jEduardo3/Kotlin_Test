package com.art.searchengine

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.art.searchengine.databinding.ItemRickandmortyBinding
import com.squareup.picasso.Picasso

class RickAndMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemRickandmortyBinding.bind(view)

    fun bind(rickAndMortyResponse: RickAndMortyResponse, onItemSelected:(String) -> Unit) {
        binding.tvRMName.text = rickAndMortyResponse.name
        binding.tvRMSpecies.text = rickAndMortyResponse.species
        binding.tvRMStatus.text = rickAndMortyResponse.status
        Picasso.get().load(rickAndMortyResponse.image).into(binding.image)

        // more details:
        binding.root.setOnClickListener { onItemSelected(rickAndMortyResponse.id) }
    }
}