package com.candyspace.myapplication.ui.interfaces

import android.view.View
import com.candyspace.myapplication.models.Item

interface OnItemClickListener {

    public fun onItemClick(view: View, user: Item)
}