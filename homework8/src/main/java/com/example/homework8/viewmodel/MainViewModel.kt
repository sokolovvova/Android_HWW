package com.example.homework8.viewmodel


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework8.repository.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val prefProv = PreferenceProvider(getApplication())
    private val currentWeatherRepository: CurrentWeatherRepository = CurrentWeatherRepository()
    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title
    private val _fragment = MutableLiveData<Fragment>()
    val fragment: LiveData<Fragment>
        get() = _fragment
    private val _setting = MutableLiveData<Boolean>()
    val setting: LiveData<Boolean>
        get() = _setting
    private val _currentWeather = MutableLiveData<CurrentWeatherDataModel>()
    val currentWeather: LiveData<CurrentWeatherDataModel>
        get() = _currentWeather
    private val _futureWeather = MutableLiveData<List<FutureWeatherDataModel>>()
    val futureWeather: LiveData<List<FutureWeatherDataModel>>
        get() = _futureWeather

    fun updateActionBarTitle(title: String) = _title.postValue(title)
    fun changeFragment(fragment: Fragment) = _fragment.postValue(fragment)
    fun changeSetting(isCelsius: Boolean) {
        prefProv.saveDegreeType(isCelsius)
        _setting.postValue(isCelsius)
    }

    fun updateSetting() {
        _setting.postValue(prefProv.getDegreeType())
    }

    fun updateCurrentWeather() {
        val units = if (prefProv.getDegreeType()) "metric" else "standard"
        currentWeatherRepository.refreshCurrentData(object : OnCurrentDataReadyCallback {
            override fun onDataReady(currentWeatherData: CurrentWeatherDataModel) {
                _currentWeather.postValue(currentWeatherData)
            }
        }, units)
    }

    fun updateFutureWeather() {
        val units = if (prefProv.getDegreeType()) "metric" else "standard"
        currentWeatherRepository.refreshFutureData(object : OnFutureDataReadyCallback {
            override fun onDataReady(futureWeatherData: List<FutureWeatherDataModel>) {
                _futureWeather.postValue(futureWeatherData)
            }
        }, units)
    }
}