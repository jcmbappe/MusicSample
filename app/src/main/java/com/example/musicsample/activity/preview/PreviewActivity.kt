package com.example.musicsample.activity.preview

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.musicsample.R
import com.example.musicsample.data.Song
import com.example.musicsample.databinding.ActivityPreviewBinding
import com.example.musicsample.repository.ItuneRepository
import com.example.musicsample.utilis.RequestCode
import com.example.musicsample.utilis.error.SnackBarResolver
import dm.audiostreamer.AudioStreamingManager
import dm.audiostreamer.CurrentSessionCallback
import dm.audiostreamer.MediaMetaData
import org.koin.android.ext.android.inject


class PreviewActivity : AppCompatActivity(), CurrentSessionCallback {

    private val repository: ItuneRepository by inject()
    private lateinit var viewModel: PreviewViewModel
    private lateinit var binding: ActivityPreviewBinding
    private var streamingManager: AudioStreamingManager? = null
    private var selectedSong: Int = 0
    private var resolver: SnackBarResolver? = null


    companion object {
        private const val SONG_LIST_ARG = "SONG_LIST_ARG"
        private const val SELECTED_SONG_ARG = "SELECTED_SONG_ARG"

        fun start(activity: Activity, selectedSong: Int, songList: ArrayList<Song>) {
            Intent(activity, PreviewActivity::class.java).apply {
                putExtra(SELECTED_SONG_ARG, selectedSong)
                putExtra(SONG_LIST_ARG, songList)

                activity.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview)

        resolver = SnackBarResolver(binding.root).apply {
            viewModel = PreviewViewModel(this, repository).apply {
                binding.viewModel = this
            }
        }

        intent.apply {
            viewModel.songList = getParcelableArrayListExtra(SONG_LIST_ARG)
            selectedSong = getIntExtra(SELECTED_SONG_ARG, 0)
        }

        streamingManager = AudioStreamingManager.getInstance(this).apply {
            setMediaList(viewModel.mediaMetaData)
            isPlayMultiple = true
            val permissionCheck =
                ContextCompat.checkSelfPermission(
                    this@PreviewActivity,
                    Manifest.permission.READ_PHONE_STATE
                )

            when {
                permissionCheck != PackageManager.PERMISSION_GRANTED -> ActivityCompat.requestPermissions(
                    this@PreviewActivity,
                    arrayOf(Manifest.permission.WAKE_LOCK),
                    RequestCode.WOKE_STATE_PERMISSION.ordinal
                )
                permissionCheck == PackageManager.PERMISSION_GRANTED -> onPlay(viewModel.mediaMetaData[selectedSong])
                else -> resolver?.displayMessage(R.string.error_provide_permission)
            }
        }

        binding.apply {
           previous.setOnClickListener {
                streamingManager?.onSkipToPrevious()
            }

            next.setOnClickListener {
                streamingManager?.onSkipToNext()
            }

            playPause.setOnClickListener {
                when (viewModel?.isPlaying?.value) {
                    true -> {
                        streamingManager?.onPause()
                        viewModel?.isPlaying?.value = false
                    }
                    false -> {
                        streamingManager?.onPlay(streamingManager?.currentAudio)
                        viewModel?.isPlaying?.value = true
                    }
                }
            }
        }

        viewModel.isPlaying.observe(this, Observer { isPlaying ->
            binding.playPause.setImageResource(
                if (isPlaying) {
                    R.drawable.ic_pause_circle_outline_black_24dp
                } else {
                    R.drawable.ic_play_circle_outline_black_24dp
                }
            )
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RequestCode.WOKE_STATE_PERMISSION.ordinal -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    streamingManager?.onPlay(viewModel.mediaMetaData[selectedSong])
                } else {
                    resolver?.displayMessage(R.string.error_provide_permission)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        streamingManager?.subscribesCallBack(this)
        viewModel.isPlaying.value = true
    }

    override fun onStop() {
        super.onStop()
        streamingManager?.unSubscribeCallBack()
        viewModel.isPlaying.value = false
    }

    override fun currentSeekBarPosition(p0: Int) {
        viewModel.progression.set(p0)
        viewModel.time.set(String.format("0:%d", (p0 / 1000.0).toInt()))
    }

    override fun playSongComplete() {
        viewModel.progression.set(0)
        viewModel.time.set(String.format("0:%d", 0))
    }

    override fun playNext(p0: Int, p1: MediaMetaData?) {
    }

    override fun updatePlaybackState(p0: Int) {
    }

    override fun playCurrent(p0: Int, media: MediaMetaData?) {
        viewModel.title.set(media?.mediaTitle)
        viewModel.artiste.set(media?.mediaArtist)
        viewModel.cover.set(media?.mediaArt)
    }

    override fun playPrevious(p0: Int, p1: MediaMetaData?) {
    }

    override fun onBackPressed() {
        streamingManager?.onStop()
        super.onBackPressed()
    }
}
