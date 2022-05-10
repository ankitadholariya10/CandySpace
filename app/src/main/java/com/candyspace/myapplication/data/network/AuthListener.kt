package com.candyspace.myapplication.data.network


interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)

}