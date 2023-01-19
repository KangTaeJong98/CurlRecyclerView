package com.example.curlclone

import androidx.recyclerview.widget.DiffUtil


class TestDiffUtil : DiffUtil.ItemCallback<TestUiState>() {
    override fun areItemsTheSame(
        oldItem: TestUiState,
        newItem: TestUiState
    ) = oldItem.uri == newItem.uri

    override fun areContentsTheSame(
        oldItem: TestUiState,
        newItem: TestUiState
    ) = oldItem == newItem
}