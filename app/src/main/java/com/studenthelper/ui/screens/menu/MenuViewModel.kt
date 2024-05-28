package com.studenthelper.ui.screens.menu

import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.menu.GetMenuDataUseCase
import com.studenthelper.ui.model.user.UserUiModel
import com.studenthelper.ui.model.user.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    getMenuDataUseCase: GetMenuDataUseCase
) : BaseViewModel() {

    val isLoadingFlow: StateFlow<Boolean>
        get() = _isLoadingFlow.asStateFlow()
    val userFlow: StateFlow<UserUiModel?>
        get() = _userFlow.asStateFlow()

    private val _isLoadingFlow = MutableStateFlow(false)
    private val _userFlow = MutableStateFlow<UserUiModel?>(null)

    init {
        _isLoadingFlow.value = true
        getMenuDataUseCase(
            scope = this,
            onFailure = {
                _isLoadingFlow.value = false
            }
        ) { data ->
            _isLoadingFlow.value = false
            _userFlow.value = data.user.toUiModel()
        }
    }

}