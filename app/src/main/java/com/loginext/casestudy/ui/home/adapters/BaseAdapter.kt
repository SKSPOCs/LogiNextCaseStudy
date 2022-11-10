package com.loginext.casestudy.ui.home.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

open abstract class BaseAdapter<T : BaseAdapter.BaseViewHolder<K>, K> : RecyclerView.Adapter<T>() {


    protected var binding: ViewDataBinding? = null

    protected var listData: MutableList<K> = mutableListOf()

    override fun getItemCount(): Int = listData.size


    fun reloadData(data: List<K>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(listData[position])
    }


    abstract class BaseViewHolder<K>(binding: ViewBinding) : ViewHolder(binding.root) {
        abstract fun bind(item: K)
    }
}