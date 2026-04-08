package ua.steniuk.myapplication.ui.screens.progress

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ProgressScreen() {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(loading) {
        if (loading) {
            currentProgress = 0f
            while (currentProgress < 1f) {
                delay(50)
                currentProgress += 0.01f
            }
            loading = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Determinate:", style = MaterialTheme.typography.titleMedium)

        LinearProgressIndicator(
            progress = { currentProgress },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { loading = true }, enabled = !loading) {
            Text(if (loading) "Loading..." else "Start loading")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Text("Indeterminate:", style = MaterialTheme.typography.titleMedium)

        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}