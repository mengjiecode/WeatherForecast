package com.mengjie.weatherforecast.view

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mengjie.weatherforecast.viewmodel.MainViewModel
import com.mengjie.weatherforecast.R
import com.mengjie.weatherforecast.databinding.ActivityMainBinding
import com.mengjie.weatherforecast.model.WeatherItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import com.mengjie.weatherforecast.view.WeatherItemAdapter as WeatherItemAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        actionBar?.hide()

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        initDialog()

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.items.observe(this, Observer {list: List<WeatherItem> ->
            recyclerView.adapter = WeatherItemAdapter(list)
        })

        viewModel.errorMessage.observe(this, Observer {
            showErrorDialog(it)
        })

        checkNetworkConnection()

        binding.viewmodel = viewModel
    }

    private fun initDialog() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setIcon(resources.getDrawable(android.R.drawable.ic_dialog_alert, theme))
            setPositiveButton(android.R.string.yes, positiveButtonClick)
            setNegativeButton(android.R.string.no, null)
            setCancelable(false)
        }
        alertDialog = builder.create()
    }

    private fun showErrorDialog(message: String?) {
        if (message == null || message.isBlank()) {
            return
        }
        alertDialog.setTitle("Message Alert")
        alertDialog.setMessage("${message}\n\nPlease try again later")
        alertDialog.show()
    }

    private fun showNetworkErrorDialog() {
        alertDialog.setTitle("Network required")
        alertDialog.setMessage("Network is not available\n\nPlease enable your Internet to continue")
        alertDialog.show()
    }

    private val positiveButtonClick = { _: DialogInterface, _: Int ->
        checkNetworkConnection()
    }

    private fun requestData() {
        viewModel.displayForecastWeather()
    }

    private fun checkNetworkConnection() {
        if (isNetworkAvailable()) {
            requestData()
        } else {
            showNetworkErrorDialog()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

}
