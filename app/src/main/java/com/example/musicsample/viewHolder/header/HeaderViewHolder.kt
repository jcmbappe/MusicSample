package com.example.musicsample.viewHolder.header

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsample.R
import com.example.musicsample.activity.mainActivity.MainActivityViewModel
import com.example.musicsample.databinding.ViewHolderHeaderBinding


class HeaderViewHolder(private val binding: ViewHolderHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val viewModel = HeaderViewModel()
    private val context: Context = binding.root.context

    init {
        binding.viewModel = viewModel
    }

    fun bind(searchState: MainActivityViewModel.SearchState, search: String?) {
        when (searchState) {
            MainActivityViewModel.SearchState.HOT_HUNDRED ->
                viewModel.title.set(context.getString(R.string.title_top_hundred))
            MainActivityViewModel.SearchState.SEARCH -> {
                val label = context.getString(R.string.title_search_for)
                binding.title.text = SpannableStringBuilder(String.format("%s %s", label, search)).apply {
                    setSpan(StyleSpan(Typeface.BOLD), label.length, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(ForegroundColorSpan(Color.BLACK), label.length, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
    }
}