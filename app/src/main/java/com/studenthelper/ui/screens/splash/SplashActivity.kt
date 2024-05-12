package com.studenthelper.ui.screens.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.studenthelper.ui.ContentActivity
import com.studenthelper.ui.navigation.NavigationItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
        initListeners()
    }

    private fun initListeners() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigateToStartDestinationEventFlow.onEach { event ->
                    if (event != null) {
                        val intent = ContentActivity.getStartIntent(
                            this@SplashActivity,
                            event.route
                        )
                        startActivity(intent)
                        viewModel.navigateToStartDestinationEventConsumed()
                        finish()
                    }
                }.launchIn(this)
            }
        }
    }

}