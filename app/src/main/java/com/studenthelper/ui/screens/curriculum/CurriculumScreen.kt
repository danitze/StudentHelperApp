package com.studenthelper.ui.screens.curriculum

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.studenthelper.base.common.date.DateTimeFormat
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.library_ui.components.Loader
import com.studenthelper.ui.navigation.CLASS_ID
import com.studenthelper.ui.navigation.NavigationItem
import com.studenthelper.ui.screens.menu.MenuScreen
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDateTime
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
fun CurriculumScreen(
    navController: NavController = rememberNavController(),
    viewModel: CurriculumViewModel = hiltViewModel()
) {
    val date by viewModel.dateFlow.collectAsStateWithLifecycle()
    var isMenuVisible by remember {
        mutableStateOf(false)
    }
    val universityClasses by viewModel.universityClassesFlow.collectAsStateWithLifecycle()
    val currentUser by viewModel.currentUserFlow.collectAsStateWithLifecycle()
    val noClasses by viewModel.noClassesFlow.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoadingFlow.collectAsStateWithLifecycle()
    val loggedOutEvent by viewModel.loggedOutEventFlow.collectAsStateWithLifecycle()
    loggedOutEvent?.let {
        LaunchedEffect(Unit) {
            launch {
                navController.navigate(NavigationItem.Login.route) {
                    popUpTo(NavigationItem.Curriculum.route) {
                        inclusive = true
                    }
                }
                viewModel.loggedOutEventConsumed()
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        launch {
            viewModel.refreshData()
        }
    }
    AppTheme {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        IconButton(
                            onClick = { isMenuVisible = true },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = com.studenthelper.library_ui.R.drawable.ic_menu
                                ),
                                contentDescription = "Menu"
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(
                                    minHeight = 56.dp
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(onClick = { viewModel.onPrevDayClick() }) {
                                Icon(
                                    painter = painterResource(
                                        id = com.studenthelper.library_ui.R.drawable.ic_arrow_back
                                    ),
                                    contentDescription = "Arrow back"
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(text = getDayOfWeekName(date.dayOfWeek))

                                Text(
                                    text = date.toJavaLocalDateTime().format(
                                        DateTimeFormatter.ofPattern(DateTimeFormat.DATE_SHORT)
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )

                            }

                            IconButton(onClick = { viewModel.onNextDayClick() }) {
                                Icon(
                                    painter = painterResource(
                                        id = com.studenthelper.library_ui.R.drawable.ic_arrow_forward
                                    ),
                                    contentDescription = "Arrow forward"
                                )
                            }
                        }
                    }

                    HorizontalDivider()
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .padding(padding)
            ) {
                if (noClasses) {
                    Text(
                        text = "Заняття відсутні",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }

                currentUser?.let { user ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(universityClasses) { universityClass ->
                            CurriculumItem(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                                    .fillMaxWidth(),
                                onClick = {
                                    navController.navigate(
                                        NavigationItem.UniversityClass.route.replace(
                                            oldValue = "{$CLASS_ID}",
                                            universityClass.id.toString()
                                        )
                                    )
                                },
                                state = CurriculumItemState(
                                    universityClass = universityClass,
                                    user = user
                                )
                            )
                        }
                    }
                }

                Loader(
                    modifier = Modifier
                        .align(Alignment.Center),
                    isVisible = isLoading
                )
            }
        }
    }

    AnimatedVisibility(
        visible = isMenuVisible,
        enter = slideInHorizontally(
            initialOffsetX = { -it }
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { -it }
        )
    ) {
        val onMenuCloseClick = remember {
            { isMenuVisible = false }
        }
        MenuScreen(
            onClose = onMenuCloseClick,
            onLogout = {
                onMenuCloseClick()
                viewModel.onLogout()
            }
        )
    }
}

private fun getDayOfWeekName(dayOfWeek: DayOfWeek): String = when(dayOfWeek) {
    DayOfWeek.MONDAY -> "Понеділок"
    DayOfWeek.TUESDAY -> "Вівторок"
    DayOfWeek.WEDNESDAY -> "Середа"
    DayOfWeek.THURSDAY -> "Четвер"
    DayOfWeek.FRIDAY -> "П'ятниця"
    DayOfWeek.SATURDAY -> "Субота"
    DayOfWeek.SUNDAY -> "Неділя"
}