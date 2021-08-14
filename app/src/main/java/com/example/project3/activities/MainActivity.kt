package com.example.project3.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.project3.adapters.MainAdapter
import com.example.project3.viewmodels.MainViewModel
import com.example.project3.viewmodels.MainViewModelFactory
import com.example.project3.api.Api
import com.example.project3.api.Repository
import com.example.project3.api.model.RickMorty
import com.example.project3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefreshLayout:SwipeRefreshLayout
    private lateinit var recyclerAdapter: MainAdapter
    private lateinit var recyclerView:RecyclerView

    private val viewModel: MainViewModel by lazy{
        ViewModelProvider(this, MainViewModelFactory(Repository(Api.apiService)))
            .get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "RickAndMorty"
        recyclerView=binding.recyclerV
        swipeRefreshLayout = binding.swiperefresh
        swipeRefreshLayout.setOnRefreshListener(this)
        recyclerAdapter = MainAdapter{
            changeActivity(it)
        }
        recyclerView.adapter = recyclerAdapter


        viewModel.characters.observe(this, {
            recyclerAdapter.addNewCharacters(it)
        })

        viewModel.isReloadingData.observe(this,{ isLoading->
            swipeRefreshLayout.isRefreshing = isLoading
        })

    }

    private fun changeActivity(rickMorty: RickMorty) {
        val intent = Intent(this@MainActivity,CharacterDetail::class.java)
        intent.putExtra("character",rickMorty)
        startActivity(intent)
    }

    override fun onRefresh() {
        updateInformation()
    }

    private fun updateInformation(){
        viewModel.retrieveData()
    }

}
