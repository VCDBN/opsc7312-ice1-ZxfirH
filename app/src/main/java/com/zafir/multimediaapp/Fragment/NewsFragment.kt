package com.zafir.multimediaapp.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zafir.multimediaapp.News.NewsData
import com.zafir.multimediaapp.News.Retro
import com.zafir.multimediaapp.databinding.FragmentNewsBinding
import com.zafir.multimediaapp.message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    //lateinit var newsChoice: String
    lateinit var response: Response<NewsData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsChoice = arguments?.getString("newsChoice")
        val service = Retro.createRetro()
        CoroutineScope(Dispatchers.IO).launch {
            response = service.getNews(newsChoice)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        newsResponse?.let{

                            val adapter = NewsAdapter(it.articles)
                            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            binding.recyclerView.adapter = adapter
//                            Log.i(TAG, "Hello World")

                        }
                    } else {
                        message = "Error: ${response.code()}"
                    }
                } catch (e: HttpException) {
                    message = "Exception ${e.message}"
                } catch (e: Throwable) {
                    message = "Oops: Something else went wrong"
                }
            }
        }
    }
}
