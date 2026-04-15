package ua.steniuk.myapplication.ui.shared.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.meetings
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun MeetingDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable (() -> Unit)
) {
    DialogWindow(
        onCloseRequest = { onDismiss() },
        title = stringResource(Res.string.meetings),
        state = rememberDialogState(),
        content = {
            content()
        }
    )
}