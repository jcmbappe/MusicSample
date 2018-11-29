package com.example.musicsample.activity.mainActivity

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.musicsample.data.SearchResult
import com.example.musicsample.data.Song
import com.example.musicsample.data.TopHundredResult
import com.example.musicsample.repository.StreamingPlatformRepository
import com.example.musicsample.utilis.NetworkCallback
import com.example.musicsample.utilis.error.UIResolver
import com.example.musicsample.viewModel.StreamingPlatformViewModel
import okhttp3.ResponseBody


class MainActivityViewModel(resolver: UIResolver, repository: StreamingPlatformRepository) :
    StreamingPlatformViewModel(resolver, repository) {

    val search = ObservableField<String>()

    val songLiveData = MutableLiveData<ArrayList<Song>>()
    val searchState = MutableLiveData<SearchState>()

    enum class SearchState {
        HOT_HUNDRED, SEARCH
    }

    init {
        getTopHundredHotSongs()
    }

     fun getTopHundredHotSongs() {
        repository.getTopHundredHotSongs(object : NetworkCallback(resolver) {
            override fun onSuccess(body: ResponseBody?) {
                val responseData = body?.string()

                searchState.postValue(SearchState.HOT_HUNDRED)

                repository.gson.fromJson(responseData, TopHundredResult::class.java).let { topHundred ->
                    ArrayList<Song>().let { songList ->
                        topHundred?.feed?.entries?.toCollection(songList)
                        songLiveData.postValue(songList)
                    }
                }
            }

            override fun onUnsuccessful(code: Int, message: String, body: ResponseBody?) {
                resolver.displayMessage(message)
            }
        })
    }

    fun getSearch() {
        search.get()?.let {
            repository.getSearch(it, object : NetworkCallback(resolver) {
                override fun onSuccess(body: ResponseBody?) {
                    val responseData = body?.string()

                    searchState.postValue(SearchState.SEARCH)

                    repository.gson.fromJson(responseData, SearchResult::class.java).let { searchResult ->
                        ArrayList<Song>().let { songList ->
                            searchResult?.results?.toCollection(songList)
                            songLiveData.postValue(songList)
                        }
                    }
                }

                override fun onUnsuccessful(code: Int, message: String, body: ResponseBody?) {
                }
            })
        }
    }
}