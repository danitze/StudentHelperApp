package com.studenthelper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.studenthelper.ui.screens.curriculum.CurriculumScreen
import com.studenthelper.ui.screens.dataload.DataLoadScreen
import com.studenthelper.ui.screens.login.LoginScreen
import com.studenthelper.ui.screens.universityclass.UniversityClassScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.DataLoad.route) {
            DataLoadScreen(navController)
        }
        composable(NavigationItem.Curriculum.route) {
            CurriculumScreen(navController)
        }
        composable(NavigationItem.UniversityClass.route) {
            UniversityClassScreen(navController)
        }
    }
}