package com.example.musicsample.viewModel

import androidx.lifecycle.ViewModel
import com.example.musicsample.repository.StreamingPlatformRepository
import com.example.musicsample.utilis.error.UIResolver

open class StreamingPlatformViewModel(
    val resolver : UIResolver,
    val repository: StreamingPlatformRepository
) : ViewModel()