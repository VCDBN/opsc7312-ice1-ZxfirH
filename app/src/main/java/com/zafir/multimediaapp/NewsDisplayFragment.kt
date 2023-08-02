package com.zafir.multimediaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsDisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.displaynews_fragment,
            container, false
        )
    }

    fun changeTextProperties(fontsize: Int, text: String)
    {
        val textV: TextView? = view?.findViewById(R.id.editText1)

        if (textV != null) {
            textV.textSize = fontsize.toFloat()
            textV.text = text
        }

    }

}