package com.studenthelper.ui.screens.curriculum

import com.studenthelper.base.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class CurriculumViewModel @Inject constructor() : BaseViewModel() {

    val dateFlow: StateFlow<LocalDateTime>
        get() = _dateFlow.asStateFlow()

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

}