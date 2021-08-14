package com.example.project3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.project3.api.model.RickMorty
import com.example.project3.databinding.ItemViewBinding

class MainAdapter(private val listener:(RickMorty)->Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private val characters = mutableListOf<RickMorty>()

    fun addNewCharacters(list:List<RickMorty>){
        characters.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class MainViewHolder(private val binding:ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: RickMorty){
            binding.imageview.load(character.image)
            binding.name.text=character.name
            binding.status.text=character.status
            binding.species.text = character.species
        }
    }
}