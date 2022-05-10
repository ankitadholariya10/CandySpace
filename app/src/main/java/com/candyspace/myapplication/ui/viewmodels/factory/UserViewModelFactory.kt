package com.candyspace.myapplication.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.candyspace.myapplication.data.repositories.NetworkRepository
import com.candyspace.myapplication.ui.viewmodels.UserViewModel

class UserViewModelFactory(
    private val repository: NetworkRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}