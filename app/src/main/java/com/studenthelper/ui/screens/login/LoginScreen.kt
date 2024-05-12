package com.studenthelper.ui.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.studenthelper.library_ui.AppTheme
import com.studenthelper.ui.navigation.NavigationItem
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val username by viewModel.usernameFlow.collectAsStateWithLifecycle()
    val password by viewModel.passwordFlow.collectAsStateWithLifecycle()
    val loginEvent by viewModel.loginEventFlow.collectAsStateWithLifecycle()
    if (loginEvent != null) {
        LaunchedEffect(key1 = Unit) {
            launch {
                navController.navigate(NavigationItem.Curriculum.route) {
                    popUpTo(NavigationItem.Login.route) {
                        inclusive = true
                    }
                }
                viewModel.loginEventConsumed()
            }
        }
    }
    AppTheme {
        Scaffold { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val (
                    inputUsernameRef,
                    inputPasswordRef,
                    buttonLoginRef
                ) = createRefs()

                OutlinedTextField(
                    value = username,
                    onValueChange = viewModel::onUsernameChange,
                    singleLine = true,
                    modifier = Modifier.constrainAs(inputUsernameRef) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top, margin = 32.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                    label = {
                        Text(text = "Ім'я користувача")
                    }
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange,
                    singleLine = true,
                    modifier = Modifier.constrainAs(inputPasswordRef) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(inputUsernameRef.bottom, margin = 24.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                    label = {
                        Text(text = "Пароль")
                    },
                )

                Button(
                    onClick = {
                        viewModel.login()
                    },
                    modifier = Modifier.constrainAs(buttonLoginRef) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(inputPasswordRef.bottom, margin = 24.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                ) {
                    Text(text = "Увійти")
                }

            }
        }
    }
}