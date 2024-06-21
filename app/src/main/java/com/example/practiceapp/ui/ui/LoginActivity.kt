package com.example.practiceapp.ui.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practiceapp.data.SharedPreferencesHelper
import com.example.practiceapp.ui.ui.ui.theme.PracticeAppTheme
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeAppTheme {
                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Login", color = Color.White) },
                            backgroundColor = MaterialTheme.colorScheme.primary
                        )
                    },
                    content = {
                        LoginScreenPreview()
                    }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginScreenPreview() {
        val scope = rememberCoroutineScope()
        val prefs = SharedPreferencesHelper(LocalContext.current)
        LoginScreen { email, password, rememberMe ->
            if (email == "admin" && password == "admin") {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()

                if (rememberMe) {
                    scope.launch {
                        prefs.setLoggedIn(true)
                    }
                }

                val intent = Intent(this, ContactListActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                Toast
                    .makeText(this, "Email hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: (String, String, Boolean) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(true) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Đăng nhập", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text(text = "Ghi nhớ đăng nhập")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onLoginClick(email, password, rememberMe) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Đăng nhập")
        }
    }
}


