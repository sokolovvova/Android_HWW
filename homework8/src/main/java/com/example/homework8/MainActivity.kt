package com.example.homework8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework8.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    var mainFragment = MainFragment()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, mainFragment)
                .commit()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.title.observe(this, Observer {
            supportActionBar?.title = it
        })
        viewModel.fragment.observe(this, Observer {
            supportFragmentManager.beginTransaction().replace(R.id.container, it).addToBackStack(null).commit()
        })
    }
}
