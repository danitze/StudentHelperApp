package com.studenthelper.ui.screens.universityclass

import androidx.lifecycle.SavedStateHandle
import com.studenthelper.base.common.extensions.require
import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.universityclass.AddHomeTaskUseCase
import com.studenthelper.domain.usecase.universityclass.AddLinkToSeriesUseCase
import com.studenthelper.domain.usecase.universityclass.AddLinkUseCase
import com.studenthelper.domain.usecase.universityclass.DeleteHomeTaskUseCase
import com.studenthelper.domain.usecase.universityclass.DeleteLinkFromSeriesUseCase
import com.studenthelper.domain.usecase.universityclass.DeleteLinkUseCase
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
    private val addLinkUseCase: AddLinkUseCase,
    private val addLinkToSeriesUseCase: AddLinkToSeriesUseCase,
    private val deleteLinkUseCase: DeleteLinkUseCase,
    private val deleteLinkFromSeriesUseCase: DeleteLinkFromSeriesUseCase,
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

    fun addLink(link: String, isForSeries: Boolean) {
        val universityClass = _contentFlow.value?.universityClass ?: return
        if (isForSeries) {
            addLinkToSeriesUseCase(
                scope = this,
                data = AddLinkToSeriesUseCase.AddLinkData(
                    seriesId = universityClass.seriesId,
                    link = link
                ),
                onFailure = {
                }
            ) {
                _contentFlow.update { it?.copy(
                    universityClass = it.universityClass.copy(
                        link = link
                    )
                ) }
            }
        } else {
            addLinkUseCase(
                scope = this,
                data = AddLinkUseCase.AddLinkData(
                    id = universityClassId,
                    link = link
                ),
                onFailure = {
                }
            ) {
                _contentFlow.update { it?.copy(
                    universityClass = it.universityClass.copy(
                        link = link
                    )
                ) }
            }
        }
    }

    fun deleteLink(isForSeries: Boolean) {
        val universityClass = _contentFlow.value?.universityClass ?: return
        if (isForSeries) {
            deleteLinkFromSeriesUseCase(
                scope = this,
                data = universityClass.seriesId,
                onFailure = {
                }
            ) {
                _contentFlow.update { it?.copy(
                    universityClass = it.universityClass.copy(
                        link = null
                    )
                ) }
            }
        } else {
            deleteLinkUseCase(
                scope = this,
                data = universityClassId,
                onFailure = {
                }
            ) {
                _contentFlow.update { it?.copy(
                    universityClass = it.universityClass.copy(
                        link = null
                    )
                ) }
            }
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