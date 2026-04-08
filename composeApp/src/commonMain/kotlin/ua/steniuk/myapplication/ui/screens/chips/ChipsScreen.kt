package ua.steniuk.myapplication.ui.screens.chips

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done

@Composable
fun ChipsScreen() {
    var isSelected by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Вибери фільтр:", modifier = Modifier.padding(bottom = 8.dp))

        FilterChip(
            selected = isSelected,
            onClick = { isSelected = !isSelected },
            label = { Text("Filter Chip (Натисни мене!)") },
            leadingIcon = if (isSelected) {
                { Icon(Icons.Filled.Done, contentDescription = null, modifier = Modifier.size(FilterChipDefaults.IconSize)) }
            } else null
        )
    }
}
