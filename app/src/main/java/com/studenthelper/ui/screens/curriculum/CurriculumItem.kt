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
import com.studenthelper.library_ui.extension.clickableNoIndication

@Preview(showBackground = true)
@Composable
fun CurriculumItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val isOnline by remember {
        mutableStateOf(true)
    }
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
            text = "Основи програмування",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "8:40 - 9:12",
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        Text(
            text = "Викладач: Андрєєв Андрій Андрійович",
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        AnimatedContent(
            targetState = isOnline,
            label = "Lesson place content",
            modifier = Modifier
                .padding(top = 8.dp)
        ) { online ->
            if (online) {
                Text(
                    text = "Онлайн",
                    color = Color.White,
                )
            } else {
                Text(
                    text = "ауд. 202",
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