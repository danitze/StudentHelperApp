package com.studenthelper.ui.screens.curriculum

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.studenthelper.base.common.date.DateTimeFormat
import com.studenthelper.library_ui.extension.clickableNoIndication
import com.studenthelper.ui.model.universityclass.UniversityClassUiModel
import com.studenthelper.ui.model.user.UserUiModel
import com.studenthelper.ui.model.user.UserUiRole
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CurriculumItem(
    state: CurriculumItemState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .clickableNoIndication { onClick() }
            .padding(all = 16.dp)
    ) {
        Text(
            text = state.universityClass.disciplineName,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        val formattedStartTime = state.universityClass.startDate
            .toJavaLocalDateTime()
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))

        val formattedEndTime = state.universityClass.startDate
            .toJavaLocalDateTime()
            .plusMinutes(90)
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))
        Text(
            text = "$formattedStartTime - $formattedEndTime",
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        Text(
            text = if (state.user.role == UserUiRole.STUDENT) {
                "Викладач: ${state.universityClass.lecturer.fullName}"
            } else {
                "Групи: ${state.universityClass.universityGroups.map { it.name }.joinToString()}"
            },
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        AnimatedContent(
            targetState = state,
            label = "Lesson place content",
            modifier = Modifier
                .padding(top = 8.dp)
        ) { itemState ->
            when {
                itemState.universityClass.isOnline -> Text(
                    text = "Онлайн",
                    color = Color.White,
                )

                itemState.universityClass.place != null -> Text(
                    text = itemState.universityClass.place,
                    color = Color.White,
                )
            }
        }

        AnimatedVisibility(
            visible = !state.universityClass.homeTask.isNullOrBlank(),
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Домашнє завдання: ${state.universityClass.homeTask ?: ""}",
                color = Color.White,
            )
        }

    }
}

data class CurriculumItemState(
    val universityClass: UniversityClassUiModel,
    val user: UserUiModel
)