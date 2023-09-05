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
import com.zafir.multimediaapp.News.Article
import com.zafir.multimediaapp.News.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

var message = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeather()
        getCurrency()
        //getFirstNews()

        val firstNews = findViewById<Button>(R.id.firstNewsBT)
        val secondNews = findViewById<Button>(R.id.secondNewsBT)

        firstNews.setOnClickListener {
            getFirstNews()
        }

        secondNews.setOnClickListener {
            val fragmentToRemove = supportFragmentManager.findFragmentByTag("NewsFragmentTag")
            fragmentToRemove?.let {
                getSecondNews(it as NewsFragment)
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
                        currencyResponse?.let {
                            curr.text =
                                "${it.old_currency} : ${it.old_amount}\n${it.new_currency} : ${it.new_amount}"
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

    private fun getFirstNews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, NewsFragment(), "NewsFragmentTag")
            .commit()
    }

    private fun getSecondNews(fragment: NewsFragment) {
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()

//        val x = findViewById<TextView>(R.id.descriptionTextView)
//        val y = findViewById<TextView>(R.id.titleTextView)
//        x.text = ""
//        y.text = ""



    }


    @SuppressLint("SetTextI18n")
    private fun getWeather() {
        val temp = findViewById<TextView>(R.id.tempTXT)
        val desc = findViewById<TextView>(R.id.descriptionTXT)

        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getData()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()?.get(0)
                        weatherResponse?.let {
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