package ua.steniuk.myapplication.ui.screens.timepickers

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import myapplication.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val state = rememberTimePickerState(is24Hour = true)
    var selectedTimeText by remember { mutableStateOf("Not selected") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "${stringResource(Res.string.selected_time)} $selectedTimeText")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { showDialog = true }) {
            Text(stringResource(Res.string.open_time_picker))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedTimeText = "${state.hour}:${state.minute.toString().padStart(2, '0')}"
                        showDialog = false
                    }) { Text(stringResource(Res.string.confirm)) }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(stringResource(Res.string.dismiss)) }
                },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TimeInput(state = state)
                    }
                }
            )
        }
    }
}