package com.example.sampleapp.ui.adapter.core

interface ItemClickListener<T> {
    fun onClick(item: T)
    fun onLongClick(item: T)
}