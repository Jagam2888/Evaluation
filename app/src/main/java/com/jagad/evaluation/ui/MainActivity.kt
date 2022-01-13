package com.jagad.evaluation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jagad.evaluation.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jagad on 1/11/2022
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}