package com.example.curlclone

import android.view.View
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView

class CurlScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager ?: return
        for (i in 0 until layoutManager.childCount) {
            val view = layoutManager.getChildAt(i) ?: continue
            val factor = view.left.toFloat() / (view.measuredWidth + view.marginStart + view.marginEnd).toFloat()

            updateTranslation(view, factor)
            updateCurlFactor(view, factor)
        }
    }

    private fun updateTranslation(view: View, factor: Float) {
        view.translationX = if (-1F < factor && factor < 1F) {
            -factor * view.width
        } else {
            0F
        }
    }

    private fun updateCurlFactor(view: View, factor: Float) {
        (view as? CurlFrameLayout)?.setCurlFactor(factor)
        (view as? CurlFrameLayout)?.setCurlFactor(factor)
    }
}