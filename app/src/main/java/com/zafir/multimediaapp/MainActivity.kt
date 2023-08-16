package com.zafir.multimediaapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zafir.multimediaapp.Weather.RetrofitFactory
import com.zafir.multimediaapp.Currency.Retrofit
import com.zafir.multimediaapp.Fragment.NewsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

var message = ""
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeather()
        getCurrency()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsFragment())
                .commit()
        }

        val getBT = findViewById<Button>(R.id.getDataBT)

        getBT.setOnClickListener {

            getFirstNews()
            getSecondNews()
            if(message.isNotEmpty()){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getCurrency() {
        val curr = findViewById<TextView>(R.id.currencyTV)

        val services = Retrofit.createRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = services.getCurrency()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val currencyResponse = response.body()
                        currencyResponse?.let{
                            curr.text = "${it.old_currency} : ${it.old_amount}\n${it.new_currency} : ${it.new_amount}"
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