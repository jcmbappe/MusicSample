package com.example.musicsample.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.activity.mainActivity.MainActivityViewModel
import com.example.musicsample.data.Song
import com.example.musicsample.databinding.ViewHolderHeaderBinding
import com.example.musicsample.databinding.ViewHolderSongBinding
import com.example.musicsample.viewHolder.header.HeaderViewHolder
import com.example.musicsample.viewHolder.song.SongViewHolder

class SongAdapter(val activity : Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var songList: ArrayList<Song> = ArrayList()
    var searchState: MainActivityViewModel.SearchState =
        MainActivityViewModel.SearchState.HOT_HUNDRED
    var search : String? = null

    private enum class TYPE {
        HEADER, SONG
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE.HEADER.ordinal) {
            HeaderViewHolder(
                ViewHolderHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            SongViewHolder(
                ViewHolderSongBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                activity,
                songList
            )
        }
    }

    override fun getItemCount(): Int {
        return if (songList.size > 0) {
            songList.size.plus(1)
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(searchState, search)
        } else if (holder is SongViewHolder) {
            songList[position - 1].let { holder.bind(it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE.HEADER.ordinal
        } else {
            TYPE.SONG.ordinal
        }
    }
}