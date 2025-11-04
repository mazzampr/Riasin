package com.frontend.riasin

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.frontend.riasin.ui.theme.RiasinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        setContent {
            RiasinTheme {
                Scaffold(
                    containerColor = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    RiasinApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
