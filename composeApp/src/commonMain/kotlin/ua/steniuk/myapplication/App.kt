package ua.steniuk.myapplication

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import ua.steniuk.myapplication.ui.screens.AppNavigation
import ua.steniuk.myapplication.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}