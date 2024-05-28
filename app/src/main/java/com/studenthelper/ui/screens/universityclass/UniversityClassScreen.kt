package com.studenthelper.ui.screens.universityclass

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.library_ui.R
import com.studenthelper.library_ui.components.Loader
import com.studenthelper.ui.model.user.UserUiRole
import com.studenthelper.ui.screens.universityclass.model.UniversityClassContent

@Preview(showBackground = true)
@Composable
fun UniversityClassScreen(
    navController: NavController = rememberNavController(),
    viewModel: UniversityClassViewModel = hiltViewModel()
) {
    val content by viewModel.contentFlow.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoadingFlow.collectAsStateWithLifecycle()
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
                            text = content?.universityClass?.disciplineName ?: "",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(1f)
                        )
                    }

                    HorizontalDivider()
                }
            }
        ) { padding ->

            ConstraintLayout(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                content?.let {
                    Content(content = it)
                }

                Loader(
                    modifier = Modifier.constrainAs(createRef()) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    isVisible = isLoading
                )
            }

        }
    }
}

@Composable
private fun ConstraintLayoutScope.Content(
    content: UniversityClassContent
) {
    Column(
        modifier = Modifier
            .constrainAs(createRef()) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = "8:40 - 10:10",
            modifier = Modifier
                .padding(top = 8.dp),
        )

        Text(
            text = "Викладач: Teacherov Teacher",
            modifier = Modifier
                .padding(top = 8.dp),
        )

        AnimatedContent(
            targetState = content.universityClass.isOnline,
            label = "Lesson place content",
            modifier = Modifier
                .padding(top = 8.dp)
        ) { online ->
            if (online) {
                Text(
                    text = "Онлайн",
                )
            } else {
                if (content.universityClass.place != null) {
                    Text(
                        text = content.universityClass.place,
                    )
                }
            }
        }

        AnimatedContent(
            targetState = content.universityClass.homeTask != null,
            modifier = Modifier
                .padding(top = 8.dp)
        ) { isHomeTaskPresent ->
            if (isHomeTaskPresent) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Домашнє завдання: ${content.universityClass.homeTask}",
                    )
                    if (content.user.role == UserUiRole.TEACHER) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Видалити домашнє завдання")
                        }
                    }
                }
            } else {
                if (content.user.role == UserUiRole.TEACHER) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Додати домашнє завдання")
                    }
                }
            }
        }
    }
}