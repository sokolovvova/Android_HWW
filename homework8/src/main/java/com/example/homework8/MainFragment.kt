package com.example.homework8

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private val myAdapter = RVAdapter()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        viewModel.updateActionBarTitle("Weather")
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            cityTextView.text = it.cityName
            descriptionTextView.text = it.weatherDescription
            tempTextView.text = it.temp
            iconImageView.setImageResource(this.resources.getIdentifier(it.weatherIcon, "drawable", activity?.packageName))
        })
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.futureWeather.observe(viewLifecycleOwner, Observer {
            myAdapter.setList(it)
            myAdapter.notifyDataSetChanged()
        })
        viewModel.updateCurrentWeather()
        viewModel.updateFutureWeather()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.changeFragment(SettingFragment())
        return super.onOptionsItemSelected(item)
    }
}