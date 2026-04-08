package ua.steniuk.myapplication.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.buttons
import myapplication.composeapp.generated.resources.checkboxes
import org.jetbrains.compose.resources.stringResource
import myapplication.composeapp.generated.resources.datepicker
import myapplication.composeapp.generated.resources.dialogs
import myapplication.composeapp.generated.resources.divider
import myapplication.composeapp.generated.resources.progress
import myapplication.composeapp.generated.resources.radio_title
import myapplication.composeapp.generated.resources.switch_title
import myapplication.composeapp.generated.resources.timepicker_title

@Composable
fun MainScreen(
    onCheckboxesClicked: () -> Unit,
    onButtonsClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onDatePickerClicked: () -> Unit,
    onDialogsClicked: () -> Unit,
    onDividerClicked: () -> Unit,
    onProgressClicked: () -> Unit,
    onRadioClicked: () -> Unit,
    onSwitchClicked: () -> Unit,
    onTimePickerClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onButtonsClicked() }
        ) {
            Text(stringResource(Res.string.buttons))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onCheckboxesClicked()
            }
        ) {
            Text(stringResource(Res.string.checkboxes))
        }
        Button(
            onClick = onChipsClicked,
            modifier = Modifier.fillMaxWidth())
        {
            Text("Chips")
        }
        Button(onClick = onDatePickerClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.datepicker))
        }
        Button(onClick = onDialogsClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.dialogs))
        }
        Button(onClick = onDividerClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.divider))
        }
        Button(onClick = onProgressClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.progress))
        }
        Button(
            onClick = onRadioClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.radio_title))
        }
        Button(onClick = onSwitchClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.switch_title))
        }
        Button(onClick = onTimePickerClicked, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(Res.string.timepicker_title))
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        onButtonsClicked = {},
        onCheckboxesClicked = {},
        onChipsClicked = {},
        onDatePickerClicked = {},
        onDialogsClicked = {},
        onDividerClicked = {},
        onProgressClicked = {},
        onRadioClicked = {},
        onSwitchClicked = {},
        onTimePickerClicked = {}
    )
}