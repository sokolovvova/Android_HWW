package com.example.homework8

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.homework8.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        viewModel.updateActionBarTitle("Settings")
        viewModel.updateSetting()
        switcher.setOnCheckedChangeListener { el, _ ->
            viewModel.changeSetting(el.isChecked)
        }
        viewModel.setting.observe(viewLifecycleOwner, Observer {
            switcher.isChecked = it
        })
    }

    override fun onStop() {
        viewModel.updateCurrentWeather()
        viewModel.updateFutureWeather()
        super.onStop()
    }
}