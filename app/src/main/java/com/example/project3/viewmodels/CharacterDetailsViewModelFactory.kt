package com.example.project3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project3.api.Repository

class CharacterDetailsViewModelFactory (private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        @SuppressWarnings("Unchecked cast")
        if(modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)){
            return CharacterDetailsViewModel(repository) as T
        }
        else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }
}