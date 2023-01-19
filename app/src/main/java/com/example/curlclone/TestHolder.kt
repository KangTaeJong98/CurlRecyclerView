package com.example.curlclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.curlclone.databinding.TestHolderBinding

class TestHolder(
    private val binding: TestHolderBinding
) : ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        TestHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun bind(uiState: TestUiState) {
        Glide.with(binding.imageView)
            .load(uiState.uri)
            .into(binding.imageView)

        binding.root.tag = bindingAdapterPosition
    }
}