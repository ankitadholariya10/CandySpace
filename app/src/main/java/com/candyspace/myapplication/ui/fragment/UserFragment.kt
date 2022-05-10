package com.candyspace.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.candyspace.myapplication.R
import com.candyspace.myapplication.data.network.Api
import com.candyspace.myapplication.data.network.AuthListener
import com.candyspace.myapplication.data.repositories.NetworkRepository
import com.candyspace.myapplication.models.Item
import com.candyspace.myapplication.ui.adapters.UserAdapter
import com.candyspace.myapplication.ui.interfaces.OnItemClickListener
import com.candyspace.myapplication.ui.viewmodels.UserViewModel
import com.candyspace.myapplication.ui.viewmodels.factory.UserViewModelFactory
import com.candyspace.myapplication.util.hide
import com.candyspace.myapplication.util.show
import com.candyspace.myapplication.util.toast
import kotlinx.android.synthetic.main.user_fragment.*


class UserFragment : Fragment(), OnItemClickListener, AuthListener {


    private lateinit var viewModel: UserViewModel
    private lateinit var factory: UserViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = Api()
        val repository = NetworkRepository(api)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        viewModel.authListener = this
        viewModel.getUsers()
        viewModel.users.observe(viewLifecycleOwner, Observer { users ->
            rv_movie.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = UserAdapter(users, this)
            }
        })
    }

    override fun onItemClick(view: View, user: Item) {
        user.topTag = user.getTopTags()
        val bundle = Bundle()
        bundle.putParcelable("userdetail", user)
        Navigation.findNavController(view).navigate(R.id.userDetailFragment, bundle);
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess() {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        requireContext().toast(message)
    }


}