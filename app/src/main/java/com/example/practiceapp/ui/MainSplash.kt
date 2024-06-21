package com.example.practiceapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceapp.R
import com.example.practiceapp.data.SharedPreferencesHelper
import com.example.practiceapp.ui.ui.ContactListActivity
import com.example.practiceapp.ui.ui.LoginActivity
import com.example.practiceapp.ui.ui.theme.PracticeAppTheme
import com.example.practiceapp.ui.ui.theme.Purple80
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainSplash : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeAppTheme {
                SplashScreen { isLoggedIn ->
                    val intent = if(isLoggedIn) {
                        Intent(this, ContactListActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }

                    } else {
                        Intent(this, LoginActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    @Composable
    fun SplashScreen(onTimeout: (Boolean) -> Unit) {
        val scope = rememberCoroutineScope()
        var isLoggedIn by remember { mutableStateOf(false) }
        val prefs = SharedPreferencesHelper(LocalContext.current)

        LaunchedEffect(Unit) {
            scope.launch {
                prefs.isLoggedIn.collectLatest {
                    isLoggedIn = it
                    delay(2000) // Thời gian hiển thị splash screen (3 giây)
                    onTimeout(isLoggedIn)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple80),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(text = "Hoang Van Giang", fontSize = 24.sp, fontWeight = Bold)
                Text(text = "CT050413", fontSize = 24.sp, fontWeight = Bold)
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview() {
        PracticeAppTheme {
            SplashScreen {}
        }
    }
}

