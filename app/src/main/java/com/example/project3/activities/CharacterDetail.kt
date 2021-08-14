package com.example.project3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.project3.adapters.EpisodesAdapter
import com.example.project3.api.Api
import com.example.project3.api.Repository
import com.example.project3.api.model.Episode
import com.example.project3.api.model.RickMorty
import com.example.project3.databinding.ActivityCharacterDetailBinding
import com.example.project3.viewmodels.CharacterDetailsViewModel
import com.example.project3.viewmodels.CharacterDetailsViewModelFactory


class CharacterDetail : AppCompatActivity() {

    private lateinit var binding:ActivityCharacterDetailBinding
    private lateinit var name:TextView
    private lateinit var status:TextView
    private lateinit var species:TextView
    private lateinit var gender:TextView
    private lateinit var image: ImageView
    private lateinit var character:RickMorty
    private lateinit var listView: ListView
    private lateinit var episodes:MutableList<Episode>
    private lateinit var episodesAdapter: EpisodesAdapter

    private val characterDetailsViewModel: CharacterDetailsViewModel by lazy{
        ViewModelProvider(this, CharacterDetailsViewModelFactory(Repository(Api.apiService)))
            .get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = intent.getSerializableExtra("character") as RickMorty
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title= character.name

        initializeBindings()
        setupCharacterData()
        episodes = mutableListOf()
        setupEpisodeAdapter()

        characterDetailsViewModel.getEpisodes(character.episodes)

        characterDetailsViewModel.episodes.observe(this,{
            episodesAdapter.addToList(it)
        })

        characterDetailsViewModel.isReloadingData.observe(this,{

        })


    }

    private fun initializeBindings(){
        image=binding.characterImage
        name = binding.characterName
        status =  binding.characterStatus
        species = binding.characterSpecies
        gender = binding.characterGender
        listView = binding.listView
    }

    private fun setupCharacterData(){
        image.load(character.image)
        name.text= character.name
        status.text = character.status
        species.text = character.species
        gender.text = character.gender
    }

    private fun setupEpisodeAdapter(){
        episodesAdapter= EpisodesAdapter(this,episodes)
        listView.adapter = episodesAdapter
    }



}