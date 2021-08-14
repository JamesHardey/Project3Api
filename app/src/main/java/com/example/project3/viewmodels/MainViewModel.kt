package com.example.project3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3.api.Repository
import com.example.project3.api.model.RickMorty
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    private val _characters = MutableLiveData<List<RickMorty>>()
    val characters : LiveData<List<RickMorty>> get() = _characters

    private val _isReloadingData = MutableLiveData<Boolean>()
    val isReloadingData:LiveData<Boolean> get() = _isReloadingData

    init{
        getCharacters()
    }

    private fun getCharacters(){
        viewModelScope.launch {
            _isReloadingData.value = true
            try{
                Log.d(MainViewModel::class.simpleName,"Retrieving data")
                _characters.value = repository.getCharacters().characters
                Log.d(MainViewModel::class.simpleName,"${_characters.value}")
            } catch(e:Exception){
                Log.e(MainViewModel::class.simpleName,e.message.toString())
            }
            _isReloadingData.value = false
        }
    }

    fun retrieveData(){
        getCharacters()
    }
}