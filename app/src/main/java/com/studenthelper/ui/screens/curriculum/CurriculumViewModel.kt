package com.studenthelper.ui.screens.curriculum

import com.studenthelper.base.presentation.BaseViewModel
import com.studenthelper.domain.usecase.universityclass.GetUniversityClassesUseCase
import com.studenthelper.ui.model.universityclass.UniversityClassUiModel
import com.studenthelper.ui.model.universityclass.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CurriculumViewModel @Inject constructor(
    private val getUniversityClassesUseCase: GetUniversityClassesUseCase
) : BaseViewModel() {

    val dateFlow: StateFlow<LocalDateTime>
        get() = _dateFlow.asStateFlow()
    val isLoadingFlow: StateFlow<Boolean>
        get() = _isLoadingFlow.asStateFlow()
    val universityClassesFlow: StateFlow<List<UniversityClassUiModel>>
        get() = _universityClassesFlow.asStateFlow()
    val noClassesFlow: StateFlow<Boolean>
        get() = _noClassesFlow.stateIn(this, SharingStarted.Lazily, true)

    private val _dateFlow = MutableStateFlow(
        java.time.LocalDateTime.now().run {
            val daysToAdd = when (dayOfWeek) {
                DayOfWeek.SATURDAY -> 2L
                DayOfWeek.SUNDAY -> 1L
                else -> 0L
            }
            plusDays(daysToAdd)
        }.toKotlinLocalDateTime()
    )
    private val _isLoadingFlow = MutableStateFlow(false)
    private val _universityClassesFlow = MutableStateFlow(listOf<UniversityClassUiModel>())
    private val _noClassesFlow = _universityClassesFlow.map { it.isEmpty() }

    private var loadClassesJob: Job? = null

    init {
        _dateFlow.onEach { date ->
            loadClasses(
                fromDate = java.time.LocalDateTime.of(
                    date.date.toJavaLocalDate(),
                    LocalTime.MIDNIGHT
                ).toKotlinLocalDateTime(),
                toDate = java.time.LocalDateTime.of(
                    date.date.toJavaLocalDate().plusDays(1),
                    LocalTime.MIDNIGHT
                ).minusNanos(1).toKotlinLocalDateTime()
            )
        }.launchIn(this)
    }

    fun onNextDayClick() {
        _dateFlow.update {
            val daysToAdd = if (it.dayOfWeek == DayOfWeek.FRIDAY) {
                3L
            } else {
                1L
            }
            it.toJavaLocalDateTime().plusDays(daysToAdd).toKotlinLocalDateTime()
        }
    }

    fun onPrevDayClick() {
        _dateFlow.update {
            val daysToSubtract = if (it.dayOfWeek == DayOfWeek.MONDAY) {
                3L
            } else {
                1L
            }
            it.toJavaLocalDateTime().minusDays(daysToSubtract).toKotlinLocalDateTime()
        }
    }

    private fun loadClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ) {
        loadClassesJob?.cancel()
        _isLoadingFlow.value = true
        loadClassesJob = getUniversityClassesUseCase(
            scope = this,
            data = GetUniversityClassesUseCase.GetUniversityClassesData(
                fromDate = fromDate,
                toDate = toDate
            ),
            onFailure = {
                _isLoadingFlow.value = false
            }
        ) { universityClasses ->
            _isLoadingFlow.value = false
            _universityClassesFlow.value = universityClasses.map { it.toUiModel() }
        }
    }

}