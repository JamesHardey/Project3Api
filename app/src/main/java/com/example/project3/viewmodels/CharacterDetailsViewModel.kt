package com.example.project3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3.api.Repository
import com.example.project3.api.model.Episode
import com.example.project3.api.model.Episodes
import com.example.project3.api.model.RickMorty
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(private val repository: Repository): ViewModel() {

    private val _episodes = MutableLiveData<Episode>()
    val episodes : LiveData<Episode> get() = _episodes

    private val _isReloadingData = MutableLiveData<Boolean>()
    val isReloadingData: LiveData<Boolean> get() = _isReloadingData



    private fun getCharacterEpisode(dList:List<String>){
        viewModelScope.launch {
            _isReloadingData.value = true
            Log.d(CharacterDetailsViewModel::class.simpleName,"Retrive")

            try{
                for(episode in dList){
                    val episodeId = getEpisodeNumber(episode)
                    Log.d(CharacterDetailsViewModel::class.simpleName,"$episodeId")
                    _episodes.value = repository.getEpisode(episodeId)
                }
                Log.d(CharacterDetailsViewModel::class.simpleName,"${_episodes.value}")
            } catch(e:Exception){
                Log.e(CharacterDetailsViewModel::class.simpleName,e.message.toString())
            }
            _isReloadingData.value = false
        }
    }

    fun getEpisodes(list:List<String>){
        getCharacterEpisode(list)
    }

    private fun getEpisodeNumber(url:String):Int{
        val pos =url.lastIndexOf("/")
        return url.substring(pos+1,url.length).toInt()
    }

}