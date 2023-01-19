package com.example.curlclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.curlclone.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val adapter = TestAdapter()
    private val snapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        initRecyclerView()
    }

    private fun initAdapter() {
        adapter.submitList(
            listOf(
                TestUiState("https://w.namu.la/s/59bbf73b123d0f9f693be3c3de9506b24a1f2a3067b4ffd0207a3a08eee32d750ebf1ca3e33084aa3bbcd6916bd0a8a187cc4556b87fa269c25f1a7ff3ea279f1e372d23aa0a6eee8d5932c70d5dac0e5f841dfc90dde3994dede8f08ffd122e5805de0423483da38ef6cf7f66d9f369"),
                TestUiState("https://upload.wikimedia.org/wikipedia/commons/0/0f/IU_posing_for_Marie_Claire_Korea_March_2022_issue_03.jpg"),
                TestUiState("https://pbs.twimg.com/profile_images/1374979417915547648/vKspl9Et_400x400.jpg"),
                TestUiState("https://dimg.donga.com/wps/NEWS/IMAGE/2022/11/11/116428366.2.jpg"),
                TestUiState("https://thumb.mt.co.kr/06/2022/12/2022121313041921427_1.jpg/dims/optimize/")
            )
        )
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(CurlScrollListener())
        snapHelper.attachToRecyclerView(binding.recyclerView)
    }
}