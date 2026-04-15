package ua.steniuk.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.*
import myapplication.composeapp.generated.resources.* // Імпортуємо всі ресурси
import org.jetbrains.compose.resources.stringResource
import ua.steniuk.myapplication.ui.shared_mobile.main.MainScreen
import ua.steniuk.myapplication.ua.steniuk.myapplication.ui.theme.AppTheme

data class WindowInfo(val windowName: String, val windowState: WindowState)

fun main() = application {
    var initialized by remember { mutableStateOf(false) }
    var windowCount by remember { mutableStateOf(1) }
    val windowList = remember { SnapshotStateList<WindowInfo>() }

    // Викладач використовує назву з ресурсів
    val baseAppName = stringResource(Res.string.app_name)

    if (!initialized) {
        windowList.add(WindowInfo(baseAppName, rememberWindowState()))
        initialized = true
    }

    windowList.forEachIndexed { i, windowInfo ->
        Window(
            onCloseRequest = {
                windowList.removeAt(i)
                if (windowList.isEmpty()) exitApplication()
            },
            state = windowInfo.windowState,
            title = windowInfo.windowName
        ) {
            MenuBar {
                // Замінюємо "File" на ресурс
                Menu(stringResource(Res.string.file), mnemonic = 'F') {
                    val nextWindowState = rememberWindowState()
                    val windowsName = stringResource(Res.string.app_name, windowCount)

                    // Замінюємо "New Window" на ресурс
                    Item(stringResource(Res.string.new_window), onClick = {
                        windowCount++
                        windowList.add(WindowInfo(windowsName, nextWindowState))
                    }, shortcut = KeyShortcut(Key.N, ctrl = true))

                    // Замінюємо "Close Window" на ресурс
                    Item(stringResource(Res.string.close), onClick = {
                        windowList.removeAt(i)
                        if (windowList.isEmpty()) exitApplication()
                    }, shortcut = KeyShortcut(Key.W, ctrl = true))

                    Separator()

                    // Замінюємо "Exit" на ресурс
                    Item(stringResource(Res.string.exit), onClick = {
                        windowList.clear()
                        exitApplication()
                    }, shortcut = KeyShortcut(Key.Q, ctrl = true))
                }

                Menu("Edit", mnemonic = 'E') {
                    Item("Cut", onClick = {  }, shortcut = KeyShortcut(Key.X, ctrl = true))
                    Item("Copy", onClick = { }, shortcut = KeyShortcut(Key.C, ctrl = true))
                    Item("Paste", onClick = {  }, shortcut = KeyShortcut(Key.V, ctrl = true))
                }
            }

            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(
                        isDark = false,
                        onThemeToggle = {},
                        actionBarFun = { index, themeIcon -> }
                    )
                }
            }
        }
    }
}