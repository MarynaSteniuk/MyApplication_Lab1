package ua.steniuk.myapplication.ui.shared.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.done
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
fun MeetingDialog(
    hours: SnapshotStateList<Int>,
    onDismiss: () -> Unit
) {

    MeetingDialogWrapper(onDismiss = onDismiss) {
        Surface(
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val listState = rememberLazyListState()

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Meeting Times",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentPadding = PaddingValues(16.dp),
                    state = listState,
                ) {
                    items(hours) { hour ->
                        Surface(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = hour.toString(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = onDismiss
                ) {
                    Text(text = stringResource(Res.string.done))
                }
            }
        }
    }
}