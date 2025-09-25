package com.example.wirriesassignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wirriesassignment2.ui.theme.WirriesAssignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WirriesAssignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Patrick Wirries",
                        modifier = Modifier.padding(innerPadding),
                        id = "1366308",
                        context = this
                    )
                }
            }

        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, id: String, context: Context) {
    val context = LocalContext.current

    Surface(color = Color.Cyan) {
        Column(modifier = modifier.padding(24.dp)) {
            Text(
                text = "Hi, my name is $name!\nMy ID is $id"
            )
            Button(onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Go to Second Activity, Explicitly")
            }
            Button(onClick = {
                val intent = Intent("com.example.wirriesassignment2.VIEW_SECOND")
                context.startActivity(intent)
            }) {
                Text("Go to Second Activity Implicitly")
            }
        }
    }
}

