package com.example.musicsample.activity.preview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.musicsample.R
import com.example.musicsample.databinding.ActivityPreviewBinding
import com.example.musicsample.repository.ItuneRepository
import com.example.musicsample.utilis.error.SnackBarResolver
import org.koin.android.ext.android.inject

class PreviewActivity : AppCompatActivity() {

    private val repository: ItuneRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPreviewBinding>(this, R.layout.activity_preview)

        val viewModel = PreviewViewModel(SnackBarResolver(binding.root), repository).apply {
            binding.viewModel = this

        }
    }
}
