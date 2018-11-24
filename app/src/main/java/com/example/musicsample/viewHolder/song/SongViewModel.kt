package com.example.musicsample.viewHolder.song

import androidx.databinding.ObservableField

class SongViewModel {
    var title = ObservableField<String>()
    var artist = ObservableField<String>()
    var cover = ObservableField<String>()
}