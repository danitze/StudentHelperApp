package com.studenthelper.ui.screens.dataload

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.ui.navigation.NavigationItem
import kotlinx.coroutines.launch

@Preview
@Composable
fun DataLoadScreen(
    navController: NavController = rememberNavController(),
    viewModel: DataLoadViewModel = hiltViewModel()
) {
    val navigateToCurriculumEvent by viewModel.navigateToCurriculumEventFlow.collectAsStateWithLifecycle()
    if (navigateToCurriculumEvent != null) {
        LaunchedEffect(Unit) {
            launch {
                navController.navigate(NavigationItem.Curriculum.route) {
                    popUpTo(NavigationItem.DataLoad.route) {
                        inclusive = true
                    }
                }
                viewModel.navigateToCurriculumEventConsumed()
            }
        }
    }
    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}