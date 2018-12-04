package com.example.musicsample.activity.mainActivity

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.musicsample.data.searched.SearchResult
import com.example.musicsample.data.Song
import com.example.musicsample.data.topHundred.TopHundredResult
import com.example.musicsample.repository.StreamingPlatformRepository
import com.example.musicsample.utilis.NetworkCallback
import com.example.musicsample.utilis.error.UIResolver
import com.example.musicsample.viewModel.StreamingPlatformViewModel
import okhttp3.ResponseBody


class MainActivityViewModel(resolver: UIResolver, repository: StreamingPlatformRepository) :
    StreamingPlatformViewModel(resolver, repository) {

    val search = ObservableField<String>()
    val recyclerViewVisibility = ObservableField<Boolean>(false)
    val lottieLayoutVisibility = ObservableField<Boolean>(false)
    val lottieMessage = ObservableField<String>()

    val songLiveData = MutableLiveData<ArrayList<Song>>()
    val searchState = MutableLiveData<SearchState>()

    val loading = MutableLiveData<Boolean>()


    enum class SearchState {
        HOT_HUNDRED, SEARCH
    }

    init {
        loading.value = false
    }

    fun getTopHundredHotSongs() {
        loading.value = true
        repository.getTopHundredHotSongs(object : NetworkCallback(resolver, loading) {
            override fun onSuccess(body: ResponseBody?) {
                val responseData = body?.string()

                searchState.postValue(SearchState.HOT_HUNDRED)
                loading.postValue(false)
                repository.gson.fromJson(responseData, TopHundredResult::class.java)
                    .let { topHundred ->
                        ArrayList<Song>().let { songList ->
                            topHundred?.feed?.entries?.toCollection(songList)
                            songLiveData.postValue(songList)
                        }
                    }

            }
        })
    }

    fun getSearch() {
        search.get()?.let {
            loading.value = true
            repository.getSearch(it, object : NetworkCallback(resolver, loading) {
                override fun onSuccess(body: ResponseBody?) {
                    val responseData = body?.string()

                    searchState.postValue(SearchState.SEARCH)
                    loading.postValue(false)
                    repository.gson.fromJson(responseData, SearchResult::class.java)
                        .let { searchResult ->
                            ArrayList<Song>().let { songList ->
                                searchResult?.results?.toCollection(songList)
                                songLiveData.postValue(songList)
                            }
                        }
                }
            })
        }
    }
}