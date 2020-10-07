package com.example.homework8

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8.repository.FutureWeatherDataModel
import kotlinx.android.synthetic.main.rv_item.view.*

class RVAdapter : RecyclerView.Adapter<RVAdapter.WeatherViewHolder>() {
    private var list = emptyList<FutureWeatherDataModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(inflater, parent)
    }

    fun setList(newList: List<FutureWeatherDataModel>) {
        list = newList
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item: FutureWeatherDataModel = list[position]
        holder.bind(item)
    }

    class WeatherViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.rv_item, parent, false)) {
        private var tempView: TextView? = null
        private var timeView: TextView? = null
        private var statusView: TextView? = null
        private var iconView: ImageView? = null

        init {
            tempView = itemView.tempView
            timeView = itemView.timeView
            statusView = itemView.statusView
            iconView = itemView.iconView
        }

        fun bind(weather: FutureWeatherDataModel) {
            tempView?.text = weather.temp
            timeView?.text = weather.time
            statusView?.text = weather.description
            iconView?.setImageResource(itemView.resources.getIdentifier(weather.icon, "drawable", BuildConfig.APPLICATION_ID))
        }
    }
}