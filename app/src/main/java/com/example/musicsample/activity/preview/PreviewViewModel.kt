package com.example.musicsample.activity.preview

import androidx.databinding.ObservableField
import com.example.musicsample.repository.StreamingPlatformRepository
import com.example.musicsample.utilis.error.UIResolver
import com.example.musicsample.viewModel.StreamingPlatformViewModel

class PreviewViewModel(resolver: UIResolver, repository: StreamingPlatformRepository) :
    StreamingPlatformViewModel(resolver, repository) {

    val title = ObservableField<String>()
    val artiste = ObservableField<String>()
    var cover = ObservableField<String>()
}