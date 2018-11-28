package com.example.musicsample.activity.mainActivity

import android.os.Bundle
import android.view.KeyEvent
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

    private val repository: ItuneRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        val adapter = SongAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        MainActivityViewModel(SnackBarResolver(binding.root), repository).apply {
            binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getSearch()
                    return@OnEditorActionListener true
                }
                false
            })

            binding.viewModel = this

            songLiveData.observe(this@MainActivity, Observer {
                adapter.songList = it
                adapter.notifyDataSetChanged()
            })

            searchState.observe(this@MainActivity, Observer {
                adapter.searchState = it
            })
        }
    }
}
