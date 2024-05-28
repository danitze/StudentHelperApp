package com.studenthelper.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.library_ui.R
import com.studenthelper.library_ui.components.Loader
import com.studenthelper.ui.model.user.UserUiRole

@Preview(showBackground = true)
@Composable
fun MenuScreen(
    onClose: () -> Unit = {},
    viewModel: MenuViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoadingFlow.collectAsStateWithLifecycle()
    val user by viewModel.userFlow.collectAsStateWithLifecycle()
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
                            .defaultMinSize(
                                minHeight = 56.dp
                            )
                    ) {

                        IconButton(
                            onClick = { onClose() },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_close
                                ),
                                contentDescription = "Close"
                            )
                        }
                    }
                }
            }
        ) { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                val (
                    profileIconRef,
                    nameRef,
                    groupRef,
                    logoutButtonRef
                ) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(profileIconRef) {
                            top.linkTo(parent.top, margin = 32.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(all = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "Profile image",
                        tint = Color.White
                    )
                }

                Text(
                    text = user?.fullName ?: "",
                    modifier = Modifier.constrainAs(nameRef) {
                        top.linkTo(profileIconRef.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                    textAlign = TextAlign.Center
                )

                Text(
                    text = when {
                        user?.group != null -> user?.group!!.name
                        user?.role == UserUiRole.TEACHER -> "Викладач"
                        else -> ""
                    },
                    modifier = Modifier.constrainAs(groupRef) {
                        top.linkTo(nameRef.bottom, margin = 8.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                        visibility = when {
                            user?.group != null -> Visibility.Visible
                            user?.role == UserUiRole.TEACHER -> Visibility.Visible
                            else -> Visibility.Gone
                        }
                    },
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(logoutButtonRef) {
                        top.linkTo(groupRef.bottom, margin = 8.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                ) {
                    Text(text = "Вийти")
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