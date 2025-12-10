package com.example.pokemonrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(
    private val pokemonList: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSprite: ImageView = itemView.findViewById(R.id.ivSprite)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.tvName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        holder.tvId.text = "#${pokemon.id.toString().padStart(3, '0')}"

        Glide.with(holder.itemView.context)
            .load(pokemon.imageUrl)
            .into(holder.ivSprite)

        holder.itemView.setOnClickListener {
            onItemClick(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}
