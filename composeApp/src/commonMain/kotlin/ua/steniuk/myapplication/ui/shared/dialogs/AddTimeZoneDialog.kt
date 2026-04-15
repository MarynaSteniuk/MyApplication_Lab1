package ua.steniuk.myapplication.ui.shared.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.add
import myapplication.composeapp.generated.resources.cancel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ua.steniuk.myapplication.TimeZoneHelper
import ua.steniuk.myapplication.TimeZoneHelperImpl

@Composable
expect fun AddTimeDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
internal fun AddTimeZoneDialog(
    onAdd: (List<String>) -> Unit,
    onDismiss: () -> Unit
) {
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()

    val timeZoneStrings = remember { timezoneHelper.getTimeZoneStrings().toList() }
    val selectedStates = remember { SnapshotStateMap<Int, Boolean>() }
    val listState = rememberLazyListState()
    val searchValue = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    AddTimeDialogWrapper(onDismiss) {
        Surface(
            border = BorderStroke(width = 1.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                    value = searchValue.value,
                    singleLine = true,
                    label = { Text("Search Timezone") },
                    onValueChange = {
                        searchValue.value = it
                        if (it.isNotEmpty()) {
                            val index = searchZones(it, timeZoneStrings)
                            if (index != -1) {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index)
                                }
                            }
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { searchValue.value = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear")
                        }
                    }
                )

                DisposableEffect(Unit) {
                    focusRequester.requestFocus()
                    onDispose { }
                }

                Spacer(modifier = Modifier.size(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().height(250.dp),
                    state = listState
                ) {
                    itemsIndexed(timeZoneStrings) { i, timezone ->
                        val isSelected = isSelected(selectedStates, i)
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .toggleable(
                                    value = isSelected,
                                    onValueChange = { selectedStates[i] = it }
                                ),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surface
                        ) {
                            Text(
                                text = timezone,
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Row(modifier = Modifier.align(Alignment.End)) {
                    TextButton(onClick = onDismiss) {
                        Text(text = stringResource(Res.string.cancel))
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(
                        onClick = {
                            onAdd(getTimezones(selectedStates, timeZoneStrings))
                        }
                    ) {
                        Text(text = stringResource(Res.string.add))
                    }
                }
            }
        }
    }
}

internal fun searchZones(searchString: String, timeZoneStrings: List<String>): Int {
    var timezone = timeZoneStrings.firstOrNull { it.startsWith(searchString, ignoreCase = true) }
    if (timezone == null) {
        timezone = timeZoneStrings.firstOrNull { it.contains(searchString, ignoreCase = true) }
    }
    return if (timezone != null) timeZoneStrings.indexOf(timezone) else -1
}

internal fun getTimezones(selectedStates: Map<Int, Boolean>, timeZoneStrings: List<String>): List<String> {
    return selectedStates.filter { it.value }.map { timeZoneStrings[it.key] }
}

internal fun isSelected(selectedStates: Map<Int, Boolean>, index: Int): Boolean {
    return selectedStates[index] == true
}