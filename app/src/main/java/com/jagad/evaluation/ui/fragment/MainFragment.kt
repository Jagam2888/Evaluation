package com.jagad.evaluation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import com.jagad.evaluation.R
import com.jagad.evaluation.util.Passparams
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jagad on 1/11/2022
 */

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txt_search).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_searchArticleFragment)
        }
        val bundle = Bundle()
        view.findViewById<TextView>(R.id.txt_most_viewed).setOnClickListener {
            bundle.putString(Passparams.NAVIGATE_FROM,Passparams.MOST_VIEWED)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_articleListFragment,bundle)
        }

        view.findViewById<TextView>(R.id.txt_most_emailed).setOnClickListener {
            bundle.putString(Passparams.NAVIGATE_FROM,Passparams.MOST_EMAILED)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_articleListFragment,bundle)
        }

        view.findViewById<TextView>(R.id.txt_most_shared).setOnClickListener {
            bundle.putString(Passparams.NAVIGATE_FROM,Passparams.MOST_SHARED)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_articleListFragment,bundle)
        }
    }

}