package com.jagad.evaluation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.jagad.evaluation.R
import com.jagad.evaluation.util.Passparams
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jagad on 1/11/2022
 */

@AndroidEntryPoint
class SearchArticleFragment : Fragment(R.layout.fragment_search_article) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keyWordTxt = view.findViewById<EditText>(R.id.keyword)
        val bundle = Bundle()

        view.findViewById<Button>(R.id.btn_search).setOnClickListener {
            if (!keyWordTxt.text.isNullOrEmpty()) {
                bundle.putString(Passparams.NAVIGATE_FROM,Passparams.SEARCH)
                bundle.putString(Passparams.SEARCH_TXT,keyWordTxt.text.toString())
                Navigation.findNavController(view)
                    .navigate(R.id.action_searchArticleFragment_to_articleListFragment,bundle)
            }
        }
    }
}