package com.candyspace.myapplication.models

data class Test1(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)