package com.zafir.multimediaapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.zafir.multimediaapp.Weather.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

//const val BASE_URL = "http://dataservice.accuweather.com/"
var message = ""
class MainActivity : FragmentActivity(), NewsSwitcherFragment.ToolbarListener {
    //private val BASE_URL = "http://dataservice.accuweather.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getBT = findViewById<Button>(R.id.getDataBT)

        getBT.setOnClickListener {
            getWeather()
            getFirstNews()
            getSecondNews()
            if(message.isNotEmpty()){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onButtonClick(position: Int, text: String) {
        val textFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView2) as NewsDisplayFragment

        textFragment.changeTextProperties(position, text)
    }

    private fun getSecondNews() {

    }

    private fun getFirstNews() {

    }

    @SuppressLint("SetTextI18n")
    private fun getWeather() {
        val temp = findViewById<TextView>(R.id.tempTXT)
        val desc = findViewById<TextView>(R.id.descriptionTXT)
        //val apiKey = "d4cLebhw8Kn1QuBWwEeQGgy06rJYTdGz"
        //val locationKey = "305605" // This key represents the location for which you want weather data

        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getData()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()?.get(0)
                        weatherResponse?.let{
                            temp.text = it.Temperature.Metric.Value.toString() + " °C"
                            desc.text = it.WeatherText
                        }
                    } else {
                        message = "Error: ${response.code()}"
                    }
                } catch (e: HttpException) {
                    message = "Exception ${e.message}"
                } catch (e: Throwable) {
                    message = "Ooops: Something else went wrong"
                }
            }
        }
    }
}