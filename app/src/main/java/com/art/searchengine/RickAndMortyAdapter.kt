package com.art.searchengine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RickAndMortyAdapter(
    var RickAndMortyList: List<RickAndMortyResponse> = emptyList(),
    private val onItemSelected:(String) -> Unit
) :
    RecyclerView.Adapter<RickAndMortyViewHolder>() {

    fun updateList(RickAndMortyList: List<RickAndMortyResponse>) {
        this.RickAndMortyList = RickAndMortyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        val view = LayoutInflater.from(parent.context)
        return RickAndMortyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rickandmorty, parent, false)
        )
    }

    override fun getItemCount(): Int = RickAndMortyList.size

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        holder.bind(RickAndMortyList[position], onItemSelected)
    }

}