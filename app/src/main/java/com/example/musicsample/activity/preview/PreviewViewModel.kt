package com.example.musicsample.activity.preview

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.musicsample.data.Song
import com.example.musicsample.repository.StreamingPlatformRepository
import com.example.musicsample.utilis.error.UIResolver
import com.example.musicsample.viewModel.StreamingPlatformViewModel
import dm.audiostreamer.MediaMetaData

class PreviewViewModel(resolver: UIResolver, repository: StreamingPlatformRepository) :
    StreamingPlatformViewModel(resolver, repository) {

    val title = ObservableField<String>()
    val artiste = ObservableField<String>()
    var cover = ObservableField<String>()
    var time = ObservableField<String>("0:0")
    var progression = ObservableField<Int>()

    var isPlaying = MutableLiveData<Boolean>()

    var mediaMetaData: List<MediaMetaData> = listOf()
    var songList: List<Song> = listOf()
        set(value) {
            field = value
            mediaMetaData = value.map { song ->
                MediaMetaData().apply {
                    mediaId = song.id.toString()
                    mediaArtist = song.artist
                    mediaTitle = song.title
                    mediaArt = song.coverUrl
                    mediaUrl = song.previewUrl
                }
            }
        }
}