package com.zafir.multimediaapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zafir.multimediaapp.Fragment.NewsAdapter
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
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        newsResponse?.let{

                            val adapter = NewsAdapter(it.articles)
                            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

                            binding.recyclerView.adapter = adapter
                            //it.articles.clear()

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
