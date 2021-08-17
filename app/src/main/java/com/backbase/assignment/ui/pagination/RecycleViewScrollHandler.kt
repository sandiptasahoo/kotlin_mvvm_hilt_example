package com.backbase.assignment.ui.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleViewScrollHandler(
    private val layoutManager: LinearLayoutManager,
    private val nextPage: Int = 2,
    private val scrollListener: (() -> Unit)?
) : RecyclerView.OnScrollListener(){


    private var previousTotal = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount)
            <= (firstVisibleItem + nextPage)) {
            scrollListener?.invoke()
            loading = true
        }
    }
}