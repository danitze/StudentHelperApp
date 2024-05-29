package com.studenthelper.ui.screens.universityclass

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studenthelper.library_ui.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteMeetingLinkBottomSheet(
    onLinkDelete: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
) {

    var isForSeries by remember {
        mutableStateOf(false)
    }

    AppTheme {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = bottomSheetState,
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isForSeries,
                    onCheckedChange = { isForSeries = it }
                )

                Text(
                    text = "Для всіх занять з серії",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                onClick = { onLinkDelete(isForSeries) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Видалити посилання")
            }
        }
    }
}