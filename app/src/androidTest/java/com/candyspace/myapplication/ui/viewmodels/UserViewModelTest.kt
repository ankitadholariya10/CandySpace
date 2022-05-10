package com.candyspace.myapplication.ui.viewmodels

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.candyspace.myapplication.data.network.Api
import com.candyspace.myapplication.data.repositories.NetworkRepository
import com.candyspace.myapplication.getOrAwaitValue
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserViewModelTest : TestCase() {
    private lateinit var viewModel: UserViewModel


    public override fun setUp() {
        super.setUp()
        val repository = NetworkRepository(Api.invoke())
        viewModel = UserViewModel(repository)
    }

    @Test
    fun testViewModel() {
            viewModel.getUsers()
        viewModel.users.getOrAwaitValue().find {
            it.userName == "Ankita"
        }
    }
}