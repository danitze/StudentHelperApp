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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studenthelper.base.common.date.DateTimeFormat
import com.studenthelper.library_ui.extension.clickableNoIndication
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
fun CurriculumItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    state: CurriculumItemState = CurriculumItemState()
) {
    val homeTask by remember {
        mutableStateOf<String?>("Сторінки 22-24, вправи 201-206")
    }
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
            text = state.className,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        val formattedStartTime = state.classStartTime
            .toJavaLocalDateTime()
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))

        val formattedEndTime = state.classEndTime
            .toJavaLocalDateTime()
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))
        Text(
            text = "$formattedStartTime - $formattedEndTime",
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        Text(
            text = "Викладач: ${state.lecturerLastName} ${state.lecturerFirstName}",
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
                itemState.isOnline -> Text(
                    text = "Онлайн",
                    color = Color.White,
                )
                itemState.place != null -> Text(
                    text = itemState.place,
                    color = Color.White,
                )
            }
        }

        AnimatedVisibility(
            visible = !homeTask.isNullOrBlank(),
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Домашнє завдання: ${homeTask ?: ""}",
                color = Color.White,
            )
        }

    }
}

data class CurriculumItemState(
    val className: String = "Назва дисципліни",
    val classStartTime: LocalDateTime = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
    val classEndTime: LocalDateTime = classStartTime.toJavaLocalDateTime().plusMinutes(90)
        .toKotlinLocalDateTime(),
    val lecturerFirstName: String = "Ім'я",
    val lecturerLastName: String = "Прізвище",
    val isOnline: Boolean = true,
    val place: String? = null
)