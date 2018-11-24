package com.example.musicsample.viewHolder.header

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.R
import com.example.musicsample.activity.mainActivity.MainActivityViewModel
import com.example.musicsample.databinding.ViewHolderHeaderBinding

class HeaderViewHolder(binding: ViewHolderHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val viewModel = HeaderViewModel()
    private val context: Context = binding.root.context

    init {
        binding.viewModel = viewModel
    }

    fun bind(searchState: MainActivityViewModel.SearchState) {
        viewModel.title.set(
            when (searchState) {
                MainActivityViewModel.SearchState.HOT_HUNDRED ->
                    context.getString(R.string.title_top_hundred)
                MainActivityViewModel.SearchState.SEARCH -> context.getString(R.string.title_top_hundred)
            }
        )
    }
}