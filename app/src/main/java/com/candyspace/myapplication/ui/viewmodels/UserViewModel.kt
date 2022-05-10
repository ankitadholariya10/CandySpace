package com.candyspace.myapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candyspace.myapplication.data.network.AuthListener
import com.candyspace.myapplication.data.repositories.ApiException
import com.candyspace.myapplication.data.repositories.NetworkRepository
import com.candyspace.myapplication.models.Item
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: NetworkRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<Item>>()
    var authListener: AuthListener? = null
    val users: LiveData<List<Item>>
        get() = _users

    fun getUsers() {
        authListener!!.onStarted()
        viewModelScope.launch {
            try {
                _users.value = repository.getUser().items
                authListener?.onSuccess()
            } catch (exception: ApiException) {
                authListener?.onFailure(exception.message.toString())
            }


        }
    }

}