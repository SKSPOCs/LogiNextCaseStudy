package com.loginext.casestudy.ui.home.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

/**
 * Base adater will accept the type of Object used for list item.
 * @param T : Class of BaseViewHolder
 * @param K : Data type of POJO
 */
open abstract class BaseAdapter<T : BaseAdapter.BaseViewHolder<K>, K> : RecyclerView.Adapter<T>() {


    /**
     * Common binding view for subclasses
     * it has to be initialized in onCreateView Method
     */
    protected var binding: ViewDataBinding? = null

    /**
     * Adapter can carry any type of list. Type has to provide from extended class declaration.
     * For reference check any other adapters like  [CollectionAdapter], [FoodCategoryAdapter]
     */
    protected var listData: MutableList<K> = mutableListOf()

    /**
     * default method to get the list count.
     */
    override fun getItemCount(): Int = listData.size


    /**
     * common method to reload data for adapter. It reduced the number of lines for child classes
     */
    fun reloadData(data: List<K>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()

    }

    /**
     * There is base class for ViewHolder named "[BaseViewHolder<K>]"
     */
    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(listData[position])
    }

    /**
     * Base ViewHolder Class (Child of [ViewHolder])
     * @param binding : Layout ViewBinding Object
     */
    abstract class BaseViewHolder<K>(binding: ViewBinding) : ViewHolder(binding.root) {

        /**
         * method used to bind data for ViewHolder
         */
        abstract fun bind(item: K)
    }
}