package com.example.project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project3.api.Api
import com.example.project3.api.Repository
import com.example.project3.api.RickMorty
import com.example.project3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var characters = mutableListOf<RickMorty>()

    private val viewModel: MainViewModel by lazy{
        ViewModelProvider(this, MainViewModelFactory(Repository(Api.apiService)))
            .get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView=binding.recyclerV
        val adapter = MainAdapter(characters)
        recyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
        viewModel.characters.observe(this, {
            this.characters.addAll(it)
            adapter.notifyDataSetChanged()

        })
    }
}
