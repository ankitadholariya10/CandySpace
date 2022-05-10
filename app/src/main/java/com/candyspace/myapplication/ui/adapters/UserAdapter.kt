package com.candyspace.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.candyspace.myapplication.ui.interfaces.OnItemClickListener
import com.candyspace.myapplication.R
import com.candyspace.myapplication.databinding.ItemUserBinding
import com.candyspace.myapplication.models.Item

class UserAdapter(
    private val users: List<Item>,
    private val onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemUserBinding.item = users[position]
        holder.itemUserBinding.root.setOnClickListener {
            onItemClickListener.onItemClick(holder.itemUserBinding.root, users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }


    inner class UserViewHolder(
        val itemUserBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(itemUserBinding.root) {

    }
}