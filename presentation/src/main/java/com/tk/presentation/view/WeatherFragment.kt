package com.tk.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.tk.cleanarchtest.R
import com.tk.presentation.model.WeatherModel
import com.tk.presentation.presenter.WeatherFragmentPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment(), WeatherDisplayView {

    @Inject
    lateinit var mPresenter: WeatherFragmentPresenter

    private val args: WeatherFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather_title.text = getString(
            R.string.weather_title,
            resources.getStringArray(R.array.japan_ui)[args.position]
        )
        mPresenter.setLocalName(resources.getStringArray(R.array.japan)[args.position])
        mPresenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.resume()
    }

    override fun renderWeatherDisplay(weatherModel: WeatherModel) {
        weather_temperature_min.text = getString(R.string.weather_min, weatherModel.tempMin)
        weather_temperature_max.text = getString(R.string.weather_max, weatherModel.tempMax)
        weather_text.text = weatherModel.weather
        weather_icon.load(weatherModel.iconUrl) {
            placeholder(R.drawable.loading)
        }
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showErrorMessage() {
        weather_title.text = getString(R.string.error)
    }
}