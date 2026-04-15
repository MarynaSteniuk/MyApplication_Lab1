package ua.steniuk.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.PreviewLightDark
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.find_meeting
import myapplication.composeapp.generated.resources.world_clocks
import org.jetbrains.compose.resources.stringResource
import ua.steniuk.myapplication.ui.shared_mobile.main.MainScreen
import ua.steniuk.myapplication.ua.steniuk.myapplication.ui.theme.AppTheme // ТВОЯ ТЕМА

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }


            AppTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    isDark = isDarkTheme,
                    onThemeToggle = { isDarkTheme = !isDarkTheme }
                ) { index, themeIcon ->
                    TopAppBar(
                        title = {
                            Text(
                                text = if (index == 0) stringResource(Res.string.world_clocks)
                                else stringResource(Res.string.find_meeting)
                            )
                        },
                        actions = {
                            themeIcon()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun AppAndroidPreview() {
    MainScreen(isDark = false, onThemeToggle = {}) { index, themeIcon ->
        TopAppBar(title = { Text("Preview") }, actions = { themeIcon() })
    }
}