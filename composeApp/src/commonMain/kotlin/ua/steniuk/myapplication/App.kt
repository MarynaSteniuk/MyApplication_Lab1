package ua.steniuk.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.audiowide_regular
import myapplication.composeapp.generated.resources.compose_multiplatform
import co.touchlab.kermit.Logger
import org.jetbrains.compose.resources.Font
import ua.steniuk.myapplication.ua.steniuk.myapplication.ui.theme.AppTheme
import ua.steniuk.myapplication.ui.shared_mobile.main.MainScreen

@Composable
@Preview
fun App() {
    MaterialTheme {

        MainScreen()
    }
}