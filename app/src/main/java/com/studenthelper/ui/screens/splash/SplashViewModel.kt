package com.studenthelper.ui.screens.splash

import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.auth.GetIsLoggedInUseCase
import com.studenthelper.ui.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    getIsLoggedInUseCase: GetIsLoggedInUseCase
) : BaseViewModel() {

    val navigateToStartDestinationEventFlow: StateFlow<NavigateToStartDestinationEvent?>
        get() = _navigateToStartDestinationEventFlow.asStateFlow()

    private val _navigateToStartDestinationEventFlow =
        MutableStateFlow<NavigateToStartDestinationEvent?>(null)

    init {
        getIsLoggedInUseCase(
            scope = this,
            onFailure = {}
        ) { isLoggedIn ->
            val startDestination = if (isLoggedIn) {
                NavigationItem.Curriculum.route
            } else {
                NavigationItem.Login.route
            }
            _navigateToStartDestinationEventFlow.value =
                NavigateToStartDestinationEvent(startDestination)
        }
    }

    fun navigateToStartDestinationEventConsumed() {
        _navigateToStartDestinationEventFlow.value = null
    }

    data class NavigateToStartDestinationEvent(
        val route: String
    )
}