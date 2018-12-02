package com.example.musicsample.viewHolder.song

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.activity.preview.PreviewActivity
import com.example.musicsample.data.Song
import com.example.musicsample.databinding.ViewHolderSongBinding

class SongViewHolder(
    binding: ViewHolderSongBinding,
    private val activity: Activity
) : RecyclerView.ViewHolder(binding.root) {

    private val viewModel: SongViewModel = SongViewModel()
    private var songList: ArrayList<Song>? = null

    init {
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            songList?.let { it1 -> PreviewActivity.start(activity, adapterPosition - 1, it1) }
        }
    }

    fun bind(song: Song, songList: ArrayList<Song>) {
        this.songList = songList
        viewModel.let {
            it.title.set(song.title)
            it.artist.set(song.artist)
            it.cover.set(song.coverUrl)
        }
    }
}