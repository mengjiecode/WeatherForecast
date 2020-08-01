package com.mengjie.weatherforecast.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mengjie.weatherforecast.R
import com.mengjie.weatherforecast.databinding.ActivityWeatherForecastBinding
import com.mengjie.weatherforecast.utils.WeatherUtils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_weather_forecast.*
import com.mengjie.weatherforecast.ui.WeatherItemAdapter as WeatherItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherForecastActivity : AppCompatActivity() {

    private val viewModel: WeatherForecastViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: ActivityWeatherForecastBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        hideActionBar()
        bindUiBehavior()
        checkNetworkConnection()
    }

    private fun hideActionBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        actionBar?.hide()
    }

    private fun bindUiBehavior() {
        viewModel.items.observe(this, Observer {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = WeatherItemAdapter(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            showAlertDialog(
                title = "Message Alert",
                message = "$it\n\nPlease try again later"
            )
        })
    }

    private fun checkNetworkConnection() {
        if (isNetworkAvailable(this)) {
            loadData()
        } else {
            showAlertDialog(
                title = "Network required",
                message = "Network is not available\n\nPlease enable your Internet to continue"
            )
        }
    }

    private fun loadData() {
        viewModel.displayForecastWeather()
    }

    private fun showAlertDialog(title: String?, message: String?) {
        val alertDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setIcon(resources.getDrawable(android.R.drawable.ic_dialog_alert, theme))
            setPositiveButton(android.R.string.yes, onPositiveButtonClickListener)
            setNegativeButton(android.R.string.no, null)
            setCancelable(false)
        }
        alertDialog = builder.create()
        alertDialog.setTitle(title ?: "")
        alertDialog.setMessage(message ?: "")
        alertDialog.show()
    }

    private val onPositiveButtonClickListener = { _: DialogInterface, _: Int ->
        checkNetworkConnection()
    }

}
