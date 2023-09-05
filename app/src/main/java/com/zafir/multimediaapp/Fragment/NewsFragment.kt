package com.zafir.multimediaapp.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zafir.multimediaapp.Fragment.NewsAdapter
import com.zafir.multimediaapp.MainActivity
import com.zafir.multimediaapp.News.Article
import com.zafir.multimediaapp.News.NewsData
import com.zafir.multimediaapp.News.Retro
import com.zafir.multimediaapp.R
import com.zafir.multimediaapp.Weather.RetrofitFactory
import com.zafir.multimediaapp.databinding.FragmentNewsBinding
import com.zafir.multimediaapp.message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var dataList: NewsData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = Retro.createRetro()
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getNews()
            //response.body()?.articles?.clear()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        newsResponse?.let{

                            val adapter = NewsAdapter(it.articles)
                            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            binding.recyclerView.adapter = adapter
//                            if (adapter == null){
                                //it.articles.clear()
//                            }
//                            if (binding.recyclerView.adapter != null)
//                            {
//                                it.articles.clear()
//                                Log.i(TAG, "adapter is null")
//                            }
//                            dataList.articles.clear()
//                            binding.recyclerView.adapter = null
                            Log.i(TAG, "Hello World")

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

            //response.body()?.articles?.clear()
        }
    }
}
