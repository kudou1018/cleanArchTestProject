package com.tk.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tk.cleanarchtest.R
import kotlinx.android.synthetic.main.local_list_item.view.*
import javax.inject.Inject

class LocalListAdapter @Inject constructor() :
    RecyclerView.Adapter<LocalListAdapter.CustomViewHolder>() {

    private lateinit var mLocalList: Array<String>

    private lateinit var mListener: OnItemClickListener

    fun setLocalList(list: Array<String>) {
        mLocalList = list
        notifyDataSetChanged()
    }

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.local_list_item, parent, false)
        return CustomViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mLocalList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.weather_title.text = mLocalList[position]
        holder.view.setOnClickListener {
            mListener.onItemClickListener(it, position, mLocalList[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }
}