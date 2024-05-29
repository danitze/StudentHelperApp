package com.studenthelper.ui.screens.universityclass

import androidx.lifecycle.SavedStateHandle
import com.studenthelper.base.common.extensions.require
import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.universityclass.AddHomeTaskUseCase
import com.studenthelper.domain.usecase.universityclass.DeleteHomeTaskUseCase
import com.studenthelper.domain.usecase.universityclass.GetUniversityClassDataUseCase
import com.studenthelper.ui.model.universityclass.toUiModel
import com.studenthelper.ui.model.user.toUiModel
import com.studenthelper.ui.navigation.CLASS_ID
import com.studenthelper.ui.screens.universityclass.model.UniversityClassContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UniversityClassViewModel @Inject constructor(
    private val getUniversityClassDataUseCase: GetUniversityClassDataUseCase,
    private val addHomeTaskUseCase: AddHomeTaskUseCase,
    private val deleteHomeTaskUseCase: DeleteHomeTaskUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val contentFlow: StateFlow<UniversityClassContent?>
        get() = _contentFlow.asStateFlow()
    val isLoadingFlow: StateFlow<Boolean>
        get() = _isLoadingFlow.asStateFlow()

    private val _contentFlow = MutableStateFlow<UniversityClassContent?>(null)
    private val _isLoadingFlow = MutableStateFlow(false)

    private val universityClassId = savedStateHandle.require<Long>(CLASS_ID)

    init {
        loadData()
    }

    fun addHomeTask(homeTask: String) {
        if (homeTask.isBlank()) return
        addHomeTaskUseCase(
            scope = this,
            data = AddHomeTaskUseCase.AddHomeTaskData(
                id = universityClassId,
                homeTask = homeTask.trim()
            ),
            onFailure = {
                _isLoadingFlow.value = false
            }
        ) {
            _isLoadingFlow.value = false
            _contentFlow.update { it?.copy(
                universityClass = it.universityClass.copy(
                    homeTask = homeTask
                )
            ) }
        }
    }

    fun deleteHomeTask() {
        deleteHomeTaskUseCase(
            scope = this,
            data = universityClassId,
            onFailure = {
            }
        ) {
            _contentFlow.update { it?.copy(
                universityClass = it.universityClass.copy(
                    homeTask = null
                )
            ) }
        }
    }

    private fun loadData() {
        getUniversityClassDataUseCase(
            scope = this,
            data = universityClassId,
            onFailure = {
            }
        ) { data ->
            _contentFlow.value = UniversityClassContent(
                universityClass = data.universityClassDomainModel.toUiModel(),
                user = data.userDomainModel.toUiModel()
            )
        }
    }

}