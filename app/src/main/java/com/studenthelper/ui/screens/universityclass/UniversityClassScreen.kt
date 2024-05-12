package com.studenthelper.ui.screens.universityclass

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.library_ui.R

@Preview(showBackground = true)
@Composable
fun UniversityClassScreen(
    navController: NavController = rememberNavController()
) {
    val isOnline by remember {
        mutableStateOf(true)
    }
    val homeTask by remember {
        mutableStateOf<String?>("Сторінки 22-24, вправи 201-206")
    }
    AppTheme {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(
                                minHeight = 56.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_arrow_left
                                ),
                                contentDescription = "Arrow back"
                            )
                        }

                        Text(
                            text = "Основи програмування",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(1f)
                        )
                    }

                    HorizontalDivider()
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    text = "8:40 - 9:12",
                    modifier = Modifier
                        .padding(top = 8.dp),
                )

                Text(
                    text = "Викладач: Андрєєв Андрій Андрійович",
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
                        )
                    } else {
                        Text(
                            text = "ауд. 202",
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
                    )
                }
            }
        }
    }
}