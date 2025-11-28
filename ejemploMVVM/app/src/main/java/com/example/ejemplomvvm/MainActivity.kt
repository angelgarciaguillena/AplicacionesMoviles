package com.example.ejemplomvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejemplomvvm.ui.theme.EjemploMVVMTheme
import com.example.ejemplomvvm.ui.theme.UserListScreen
import kotlin.getValue
import com.example.ejemplomvvm.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    // ViewModel compartido para toda la actividad
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EjemploMVVMTheme {
                UserListScreen(userViewModel=userViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploMVVMTheme {
        UserListScreen()
    }
}