package ua.steniuk.myapplication

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ua.steniuk.myapplication.ui.shared_mobile.main.MainScreen
import ua.steniuk.myapplication.ua.steniuk.myapplication.ui.theme.AppTheme
import kotlinx.browser.document
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule


@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        AppTheme {
            MainScreen(
                isDark = false,
                onThemeToggle = { },
                actionBarFun = { index, themeIcon ->
                }
            )
        }
    }
}
