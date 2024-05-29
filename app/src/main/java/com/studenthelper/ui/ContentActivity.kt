package com.studenthelper.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.ui.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AppNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = rememberNavController(),
                        startDestination = intent.getStringExtra(EXTRA_KEY_START_DESTINATION)!!
                    )
                }
            }
        }
    }

    companion object {
        private const val EXTRA_KEY_START_DESTINATION = "EXTRA_KEY_START_DESTINATION"

        fun getStartIntent(
            context: Context,
            startDestination: String
        ): Intent = Intent(context, ContentActivity::class.java).apply {
            putExtra(EXTRA_KEY_START_DESTINATION, startDestination)
        }
    }
}