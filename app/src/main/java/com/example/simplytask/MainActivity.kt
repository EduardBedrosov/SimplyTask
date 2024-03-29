package com.example.simplytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.simplytask.sections.homescreen.AppScreen
import com.example.simplytask.ui.theme.SimplyTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SimplyTaskTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.onPrimary),
//                    color = MaterialTheme.colorScheme.background
                    color = MaterialTheme.colorScheme.onPrimary

                ) {
                    AppScreen()
                }

            }
        }
    }
}

