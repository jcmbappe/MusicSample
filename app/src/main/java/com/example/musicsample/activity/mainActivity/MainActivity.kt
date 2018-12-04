package com.example.musicsample.activity.mainActivity

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicsample.R
import com.example.musicsample.adapter.SongAdapter
import com.example.musicsample.databinding.ActivityMainBinding
import com.example.musicsample.repository.ItuneRepository
import com.example.musicsample.utilis.error.SnackBarResolver
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private val repository: ItuneRepository by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = SongAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = MainActivityViewModel(SnackBarResolver(binding.root), repository).apply {
            binding.viewModel = this

            songLiveData.observe(this@MainActivity, Observer {
                adapter.songList = it
                adapter.notifyDataSetChanged()
                lottieLayoutAnimation(
                    adapter.itemCount == 0,
                    R.raw.sky,
                    R.string.label_nothing_found
                )
            })

            searchState.observe(this@MainActivity, Observer {
                adapter.searchState = it
                adapter.search = this.search.get()
            })

            loading.observe(this@MainActivity, Observer {
                lottieLayoutAnimation(it == true, R.raw.sound_visualizer, R.string.label_loading)
            })

            getTopHundredHotSongs()
        }

        binding.search.apply {
            binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (viewModel.search.get().isNullOrEmpty()) {
                        viewModel.getTopHundredHotSongs()
                    } else {
                        viewModel.getSearch()
                    }

                    return@OnEditorActionListener true
                }
                false
            })
        }
    }

    private fun lottieLayoutAnimation(isVisible: Boolean, rawRes: Int, message: Int) {
        if (isVisible) {
            binding.include.lottie.setAnimation(rawRes)
            viewModel.lottieMessage.set(getString(message))
            viewModel.lottieLayoutVisibility.set(true)
            viewModel.recyclerViewVisibility.set(false)
        } else {
            viewModel.lottieLayoutVisibility.set(false)
            viewModel.recyclerViewVisibility.set(true)
        }
    }
}
