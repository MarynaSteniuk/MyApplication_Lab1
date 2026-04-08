package ua.steniuk.myapplication.ui.screens.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogsScreen() {
    var openAlertDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { openAlertDialog = true }) {
            Text("Show Alert Dialog")
        }

        if (openAlertDialog) {
            AlertDialog(
                icon = { Icon(Icons.Default.Info, contentDescription = "Example Icon") },
                title = { Text(text = "Action confirmation") },
                text = { Text(text = "This is an example of a dialog box. Are you sure you want to continue?") },
                onDismissRequest = { openAlertDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        println("Confirmed!")
                        openAlertDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openAlertDialog = false }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}