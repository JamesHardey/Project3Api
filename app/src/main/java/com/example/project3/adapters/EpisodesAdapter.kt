package com.example.project3.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.project3.R
import com.example.project3.api.model.Episode

class EpisodesAdapter(private val getContext: Context,private var episodes:MutableList<Episode>):
    ArrayAdapter<Episode>(getContext,0,episodes){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listLayout = convertView
        val holder: ViewHolder

        if(listLayout == null){

            val inflateList =(getContext as Activity).layoutInflater
            listLayout = inflateList.inflate(R.layout.episode_item,parent,false)

            holder = ViewHolder()
            holder.text1 = listLayout.findViewById(R.id.episodeItem)
            holder.text2 = listLayout.findViewById(R.id.episodeAirDate)
            holder.text3 = listLayout.findViewById(R.id.episodeName)
            listLayout.tag = holder
        }

        else{
            holder = listLayout.tag as ViewHolder
        }

        val listItem = episodes[position]

        holder.text1!!.text = listItem.episode
        holder.text2!!.text = "Air Date: ${listItem.airDate}"
        holder.text3!!.text = listItem.name
        Log.d(EpisodesAdapter::class.simpleName,"${holder.text2!!.text}")


        return listLayout!!
    }

    fun addToList(episode:Episode){
        add(episode)
        Log.d(EpisodesAdapter::class.simpleName,"Retrive---------")
        Log.d(EpisodesAdapter::class.simpleName,"$episode")
        notifyDataSetChanged()
    }

     class ViewHolder{
         internal var text1: TextView? = null
         internal var text2: TextView? = null
         internal var text3: TextView? = null
         internal var goBtn: Button? = null

    }
}