package com.muazekici.relaxingsounds.ui.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(
    private val spaceHeight: Int,
    private val marginStart: Boolean = true,
    private val marginTop: Boolean = true,
    private val marginEnd: Boolean = true,
    private val marginBottom: Boolean = true
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            top = if (marginTop) spaceHeight else 0
            left = if (marginStart) spaceHeight else 0
            right = if (marginEnd) spaceHeight else 0
            bottom = if (marginBottom) spaceHeight else 0
        }
    }
}