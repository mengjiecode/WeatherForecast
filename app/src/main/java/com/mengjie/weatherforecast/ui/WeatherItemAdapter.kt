package com.mengjie.weatherforecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mengjie.weatherforecast.R
import com.mengjie.weatherforecast.databinding.RecyclerViewItemBinding
import com.mengjie.weatherforecast.data.WeatherItem

class WeatherItemAdapter(private val items: List<WeatherItem>?) :
    RecyclerView.Adapter<WeatherItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerViewItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            items?.get(position) ?: WeatherItem(
                "",
                "",
                "",
                ""
            )
        )
    }

    inner class ViewHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherItem) {
            binding.weatherItem = item
            binding.executePendingBindings()
        }
    }
}