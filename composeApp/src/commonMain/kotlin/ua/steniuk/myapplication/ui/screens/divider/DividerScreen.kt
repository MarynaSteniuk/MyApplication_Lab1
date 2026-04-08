package ua.steniuk.myapplication.ui.screens.divider

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Horizontal dividers:", style = MaterialTheme.typography.titleMedium)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("1st item of the list")
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
            Text("2nd item of the list")
        }

        Text("Vertical divider:", style = MaterialTheme.typography.titleMedium)

        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Left")
            VerticalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text("Right")
        }
    }
}