package com.studenthelper.ui.navigation

enum class Screen(val route: String) {
    LOGIN(LOGIN.name),
    DATA_LOAD(DATA_LOAD.name),
    CURRICULUM(CURRICULUM.name),
    UNIVERSITY_CLASS("${UNIVERSITY_CLASS.name}/{$CLASS_ID}")
}

sealed class NavigationItem(val route: String) {
    data object Login : NavigationItem(Screen.LOGIN.route)
    data object DataLoad : NavigationItem(Screen.DATA_LOAD.route)
    data object Curriculum : NavigationItem(Screen.CURRICULUM.route)
    data object UniversityClass : NavigationItem(Screen.UNIVERSITY_CLASS.route)
}

const val CLASS_ID = "CLASS_ID"