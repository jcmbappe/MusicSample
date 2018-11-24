package com.example.musicsample.viewHolder.song

import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.data.Song
import com.example.musicsample.databinding.ViewHolderSongBinding

class SongViewHolder(private val binding: ViewHolderSongBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val viewModel: SongViewModel = SongViewModel()

    init {
        binding.viewModel = viewModel
    }

    fun bind(song: Song) {
        viewModel.let {
            it.title.set(song.title)
            it.artist.set(song.artist)
            it.cover.set(song.coverUrl)
        }
    }
}