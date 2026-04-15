package ua.steniuk.myapplication.ui.shared_mobile.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ua.steniuk.myapplication.ui.shared.dialogs.AddTimeZoneDialog
import ua.steniuk.myapplication.ui.shared.types.EmptyComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDark: Boolean,
    onThemeToggle: () -> Unit,
    actionBarFun: @Composable (Int, @Composable () -> Unit) -> Unit
) {
    val showAddDialog = remember { mutableStateOf(false) }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            actionBarFun(selectedIndex.intValue) {
                IconButton(onClick = onThemeToggle) {
                    Icon(
                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = "Toggle Theme"
                    )
                }
            }
        },
        floatingActionButton = {
            if (selectedIndex.intValue == 0) {
                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    shape = FloatingActionButtonDefaults.largeShape,
                    onClick = { showAddDialog.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Timezone"
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
                    NavigationBarItem(
                        label = {
                            Text(bottomNavigationItem.route, style = MaterialTheme.typography.bodyMedium)
                        },
                        icon = {
                            Icon(
                                bottomNavigationItem.icon,
                                contentDescription = bottomNavigationItem.iconContentDescription
                            )
                        },
                        selected = selectedIndex.intValue == index,
                        onClick = { selectedIndex.intValue = index }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (showAddDialog.value) {
                AddTimeZoneDialog(
                    onAdd = { newTimezones ->
                        showAddDialog.value = false
                        for (zone in newTimezones) {
                            if (!currentTimezoneStrings.contains(zone)) {
                                currentTimezoneStrings.add(zone)
                            }
                        }
                    },
                    onDismiss = { showAddDialog.value = false },
                )
            }

            when (selectedIndex.intValue) {
                0 -> TimeZonesPage(currentTimezoneStrings)
                1 -> FindMeetingPage(currentTimezoneStrings)
            }
        }
    }
}

private val bottomNavigationItems = listOf(
    BottomItem(Page.TimeZonesPage.title, Icons.Filled.Home, "Timezones"),
    BottomItem(Page.FindTimePage.title, Icons.Filled.Place, "Find Time")
)

private data class BottomItem(val route: String, val icon: ImageVector, val iconContentDescription: String)

private sealed class Page(val title: String) {
    data object TimeZonesPage : Page("Timezones")
    data object FindTimePage : Page("Find Time")
}