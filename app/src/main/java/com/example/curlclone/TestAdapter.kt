package com.example.curlclone

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class TestAdapter : ListAdapter<TestUiState, TestHolder>(TestDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = TestHolder(parent)

    override fun onBindViewHolder(
        holder: TestHolder,
        position: Int
    ) = holder.bind(getItem(position))
}