package com.studenthelper.ui.screens.universityclass

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studenthelper.library_ui.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMeetingLinkBottomSheet(
    onHomeTaskAdd: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
) {

    var homeTask by remember {
        mutableStateOf("")
    }

    AppTheme {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = bottomSheetState,
            modifier = modifier
        ) {
            OutlinedTextField(
                value = homeTask,
                onValueChange = { homeTask = it },
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "Домашнє завдання")
                }
            )

            Button(
                onClick = { onHomeTaskAdd(homeTask) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Додати домашнє завдання")
            }
        }
    }
}