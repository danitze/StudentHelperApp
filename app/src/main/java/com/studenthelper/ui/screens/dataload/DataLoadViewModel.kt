package com.studenthelper.ui.screens.dataload

import android.util.Log
import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.dataload.DataLoadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DataLoadViewModel @Inject constructor(
    dataLoadUseCase: DataLoadUseCase
) : BaseViewModel() {

    val navigateToCurriculumEventFlow: StateFlow<Unit?>
        get() = _navigateToCurriculumEventFlow.asStateFlow()

    private val _navigateToCurriculumEventFlow = MutableStateFlow<Unit?>(null)

    init {
        dataLoadUseCase(
            scope = this,
            onFailure = {
                Log.e("MyTag", "Error data load", it)
            }
        ) {
            _navigateToCurriculumEventFlow.value = Unit
        }
    }

    fun navigateToCurriculumEventConsumed() {
        _navigateToCurriculumEventFlow.value = null
    }

}