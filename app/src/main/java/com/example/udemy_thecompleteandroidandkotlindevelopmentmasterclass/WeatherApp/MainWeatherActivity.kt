package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.WeatherApp

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.WeatherApp.model.WeatherResponse
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.WeatherApp.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainWeatherActivity : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_weather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!isLocationEnabled()) {
            Toast.makeText(
                this@MainWeatherActivity,
                "The location is not enabled",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        } else {
            requestPermissions()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            showRequestDialog()
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            requestPermissions()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        }
    }

    private fun showRequestDialog() {
        AlertDialog.Builder(this).setPositiveButton("Go to settings") { _, _ ->
            try {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }.setNegativeButton("Close") { dialog, _ ->
            dialog.cancel()
        }.setTitle("Location permission denied")
            .setMessage("This permission is needed for accessing the location. It can be enabled from the settings.")
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 100 && grantResults.size > 0) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "The permission is not granted", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun requestLocationData() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        mFusedLocationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    Toast.makeText(
                        this@MainWeatherActivity,
                        "latitude: ${locationResult.lastLocation?.latitude}, longitude ${locationResult.lastLocation?.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                    getLocationWeatherDetails(
                        locationResult.lastLocation?.latitude,
                        locationResult.lastLocation?.longitude
                    )
                }

            }, Looper.myLooper()
        )
    }

    private fun getLocationWeatherDetails(latitude: Double?, longitude: Double?) {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val serviceAPI = retrofit.create(WeatherServiceAPI::class.java)
            val call = serviceAPI.getWeatherDetails(
                latitude!!, longitude!!, Constants.APP_ID,
                Constants.METRIC_UNIT
            )
            call.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        val weather = response.body()
                        Toast.makeText(
                            this@MainWeatherActivity,
                            "Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        for (i in weather?.weather?.indices!!) {
                            findViewById<TextView>(R.id.text_view_sunset).text =
                                convertTime(weather.sys.sunset.toLong())
                            findViewById<TextView>(R.id.text_view_sunrise).text =
                                convertTime(weather.sys.sunrise.toLong())
                            findViewById<TextView>(R.id.text_view_status).text =
                                weather.weather[i].description
                            findViewById<TextView>(R.id.text_view_address).text = weather.name
                            findViewById<TextView>(R.id.text_view_address).text = weather.name
                            findViewById<TextView>(R.id.text_view_temp_max).text =
                                weather.main.temp_max.toString() + " max"
                            findViewById<TextView>(R.id.text_view_temp_min).text =
                                weather.main.temp_max.toString() + " min"
                            findViewById<TextView>(R.id.text_view_temp).text =
                                weather.main.temp.toString() + "°C"
                            findViewById<TextView>(R.id.text_view_humidity).text =
                                weather.main.humidity.toString()
                            findViewById<TextView>(R.id.text_view_pressure).text =
                                weather.main.pressure.toString()
                            findViewById<TextView>(R.id.text_view_wind).text =
                                weather.wind.speed.toString()
                        }
                    } else {
                        Toast.makeText(
                            this@MainWeatherActivity,
                            "Error occurred",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

                override fun onFailure(
                    call: Call<WeatherResponse?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }
            })
        } else {
            Toast.makeText(this, "there's no internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun convertTime(time: Long): String {
        val date = Date(time * 1000L)
        val timeFormatted = SimpleDateFormat("HH:mm", Locale.UK)
        timeFormatted.timeZone = TimeZone.getDefault()
        return timeFormatted.format(date)
    }
}
