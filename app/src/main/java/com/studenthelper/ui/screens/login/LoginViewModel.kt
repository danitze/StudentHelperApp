package com.studenthelper.ui.screens.login

import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val loginEventFlow: StateFlow<Unit?>
        get() = _loginEventFlow.asStateFlow()
    val usernameFlow: StateFlow<String>
        get() = _usernameFlow.asStateFlow()
    val passwordFlow: StateFlow<String>
        get() = _passwordFlow.asStateFlow()

    private val _usernameFlow = MutableStateFlow("")
    private val _passwordFlow = MutableStateFlow("")
    private val _loginEventFlow = MutableStateFlow<Unit?>(null)

    fun login() {
        loginUseCase(
            scope = this,
            data = LoginUseCase.LoginData(
                username = _usernameFlow.value,
                password = _passwordFlow.value
            ),
            onFailure = {
            }
        ) {
            _loginEventFlow.value = Unit
        }
    }

    fun onUsernameChange(value: String) {
        _usernameFlow.value = value
    }

    fun onPasswordChange(value: String) {
        _passwordFlow.value = value
    }

    fun loginEventConsumed() {
        _loginEventFlow.value = null
    }

}