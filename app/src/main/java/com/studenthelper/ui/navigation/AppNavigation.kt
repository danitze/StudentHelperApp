package com.studenthelper.ui.navigation

enum class Screen(val route: String) {
    LOGIN(LOGIN.name),
    CURRICULUM(CURRICULUM.name),
    UNIVERSITY_CLASS(UNIVERSITY_CLASS.name)
}

sealed class NavigationItem(val route: String) {
    data object Login : NavigationItem(Screen.LOGIN.route)
    data object Curriculum : NavigationItem(Screen.CURRICULUM.route)
    data object UniversityClass : NavigationItem(Screen.UNIVERSITY_CLASS.route)
}