package com.example.musicsample.viewHolder.song

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.activity.preview.PreviewActivity
import com.example.musicsample.data.Song
import com.example.musicsample.databinding.ViewHolderSongBinding

class SongViewHolder(
    binding: ViewHolderSongBinding,
    private val activity: Activity,
    private val songList: ArrayList<Song>
) :
    RecyclerView.ViewHolder(binding.root) {

    private val viewModel: SongViewModel = SongViewModel()

    init {
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            PreviewActivity.start(activity, adapterPosition - 1, songList)
        }
    }

    fun bind(song: Song) {
        viewModel.let {
            it.title.set(song.title)
            it.artist.set(song.artist)
            it.cover.set(song.coverUrl)
        }
    }
}