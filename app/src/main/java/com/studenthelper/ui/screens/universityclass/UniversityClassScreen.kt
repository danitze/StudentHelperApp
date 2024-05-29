package com.studenthelper.ui.screens.universityclass

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.studenthelper.base.common.date.DateTimeFormat
import com.studenthelper.base.common.extensions.openLink
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.library_ui.R
import com.studenthelper.library_ui.components.Loader
import com.studenthelper.ui.model.user.UserUiRole
import com.studenthelper.ui.screens.universityclass.model.UniversityClassContent
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UniversityClassScreen(
    navController: NavController = rememberNavController(),
    viewModel: UniversityClassViewModel = hiltViewModel()
) {
    val content by viewModel.contentFlow.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoadingFlow.collectAsStateWithLifecycle()
    val addHomeTaskBottomSheetState = rememberModalBottomSheetState()
    val addLinkBottomSheetState = rememberModalBottomSheetState()
    val deleteLinkBottomSheetState = rememberModalBottomSheetState()
    var isAddHomeTaskBottomSheetVisible by remember {
        mutableStateOf(false)
    }
    var isAddLinkBottomSheetVisible by remember {
        mutableStateOf(false)
    }
    var isDeleteLinkBottomSheetVisible by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
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
                    Content(
                        content = it,
                        onDeleteHomeTask = { viewModel.deleteHomeTask() },
                        onAddHomeTask = { isAddHomeTaskBottomSheetVisible = true },
                        onAddLinkClick = { isAddLinkBottomSheetVisible = true },
                        onDeleteLinkClick = { isDeleteLinkBottomSheetVisible = true }
                    )
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

        if (isAddHomeTaskBottomSheetVisible) {
            AddHomeTaskBottomSheet(
                onHomeTaskAdd = { homeTask ->
                    coroutineScope.launch {
                        addHomeTaskBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!addHomeTaskBottomSheetState.isVisible) {
                            isAddHomeTaskBottomSheetVisible = false
                        }
                    }
                    viewModel.addHomeTask(homeTask)
                },
                onDismiss = { isAddHomeTaskBottomSheetVisible = false },
                bottomSheetState = addHomeTaskBottomSheetState,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isAddLinkBottomSheetVisible) {
            AddMeetingLinkBottomSheet(
                onLinkAdd = { link, isForSeries ->
                    coroutineScope.launch {
                        addLinkBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!addLinkBottomSheetState.isVisible) {
                            isAddLinkBottomSheetVisible = false
                        }
                    }
                    viewModel.addLink(link, isForSeries)
                },
                onDismiss = { isAddLinkBottomSheetVisible = false },
                bottomSheetState = addLinkBottomSheetState,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isDeleteLinkBottomSheetVisible) {
            DeleteMeetingLinkBottomSheet(
                onLinkDelete = { isForSeries ->
                    coroutineScope.launch {
                        deleteLinkBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!deleteLinkBottomSheetState.isVisible) {
                            isDeleteLinkBottomSheetVisible = false
                        }
                    }
                    viewModel.deleteLink(isForSeries)
                },
                onDismiss = { isDeleteLinkBottomSheetVisible = false },
                bottomSheetState = deleteLinkBottomSheetState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ConstraintLayoutScope.Content(
    content: UniversityClassContent,
    onDeleteHomeTask: () -> Unit,
    onAddHomeTask: () -> Unit,
    onAddLinkClick: () -> Unit,
    onDeleteLinkClick: () -> Unit
) {
    val context = LocalContext.current
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
            .verticalScroll(rememberScrollState())
    ) {

        val formattedStartTime = content.universityClass.startDate
            .toJavaLocalDateTime()
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))

        val formattedEndTime = content.universityClass.startDate
            .toJavaLocalDateTime()
            .plusMinutes(90)
            .format(DateTimeFormatter.ofPattern(DateTimeFormat.HOURS_MINUTES))
        Text(
            text = "$formattedStartTime - $formattedEndTime",
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp),
        )

        AnimatedContent(targetState = content.user.role) { role ->
            when (role) {
                UserUiRole.TEACHER -> Text(
                    text = "Групи: ${
                        content.universityClass.universityGroups.map { it.name }.joinToString()
                    }",
                    modifier = Modifier
                        .padding(top = 8.dp),
                )

                else -> Text(
                    text = "Викладач: ${content.universityClass.lecturer.fullName}",
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
            }
        }

        AnimatedContent(
            targetState = content.universityClass.isOnline,
            label = "Lesson place content",
            modifier = Modifier
                .padding(top = 8.dp)
        ) { online ->
            if (online) {
                Column {
                    Text(
                        text = "Онлайн",
                    )
                    AnimatedContent(targetState = content.universityClass.link == null) { isLinkNotSet ->
                        if (isLinkNotSet) {
                            if (content.user.role == UserUiRole.TEACHER) {
                                Button(
                                    onClick = onAddLinkClick
                                ) {
                                    Text(text = "Додати посилання")
                                }
                            }
                        } else {
                            Column {
                                Text(text = content.universityClass.link ?: "")
                                if (content.user.role == UserUiRole.TEACHER) {
                                    Button(
                                        onClick = onDeleteLinkClick,
                                        colors = ButtonDefaults.buttonColors().copy(
                                            containerColor = MaterialTheme.colorScheme.errorContainer,
                                            contentColor = MaterialTheme.colorScheme.error
                                        )
                                    ) {
                                        Text(text = "Видалити посилання")
                                    }
                                }
                                Button(
                                    onClick = {
                                        context.openLink(content.universityClass.link ?: "")
                                    },
                                ) {
                                    Text(text = "Доєднатись!")
                                }
                            }
                        }
                    }
                }
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
                        Button(
                            onClick = onDeleteHomeTask,
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text(text = "Видалити домашнє завдання")
                        }
                    }
                }
            } else {
                if (content.user.role == UserUiRole.TEACHER) {
                    Button(onClick = onAddHomeTask) {
                        Text(text = "Додати домашнє завдання")
                    }
                }
            }
        }
    }
}