package com.example.project3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.project3.api.RickMorty
import com.example.project3.databinding.ItemViewBinding

class MainAdapter(private val characters:List<RickMorty>): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class MainViewHolder(private val binding:ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character:RickMorty){
            binding.imageview.load(character.image)
            binding.name.text=character.name
            binding.status.text=character.status
            binding.species.text = character.species
        }
    }

}