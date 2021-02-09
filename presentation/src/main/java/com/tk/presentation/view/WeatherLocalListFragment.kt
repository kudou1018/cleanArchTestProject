package com.tk.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tk.cleanarchtest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_local_list_fragment.view.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherLocalListFragment : Fragment(), LocalListAdapter.OnItemClickListener {

    @Inject
    lateinit var mAdapter: LocalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather_local_list_fragment, container, false)
        mAdapter.setOnItemClickListener(this)
        mAdapter.setLocalList(resources.getStringArray(R.array.japan_ui))
        view.local_list.layoutManager = LinearLayoutManager(context)
        view.local_list.adapter = mAdapter
        return view
    }

    override fun onItemClickListener(view: View, position: Int, clickedText: String) {
        val action =
            WeatherLocalListFragmentDirections.actionWeatherLocalListFragmentToWeatherFragment(
                position
            )
        findNavController().navigate(action)
    }
}