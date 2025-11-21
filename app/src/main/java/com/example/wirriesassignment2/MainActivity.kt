package com.example.wirriesassignment2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wirriesassignment2.ui.theme.WirriesAssignment2Theme

class MainActivity : ComponentActivity() {

    private val MY_PERMISSION = "com.example.wirriesassignment2.MSE412"
    private val REQUEST_CODE = 101
    private var hasPermission = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //Check if the app has permission to view the 2nd activity
        hasPermission = ContextCompat.checkSelfPermission(this, MY_PERMISSION) ==
                PackageManager.PERMISSION_GRANTED

        // If not granted, request it
        if (!hasPermission) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(MY_PERMISSION),
                REQUEST_CODE
            )
        }

        enableEdgeToEdge()
        setContent {
            WirriesAssignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Patrick Wirries",
                        modifier = Modifier.padding(innerPadding),
                        id = "1366308",
                    )
                }
            }

        }


    }

    //Response function to the user clicking a result of the permission prompt
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        //Switch the value of hasPermission flag if possible
        if (requestCode == REQUEST_CODE) {
            hasPermission = grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED

            //Helpful debugging tool
            if (hasPermission) {
                Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier, id: String) {
        val context = LocalContext.current

        Surface(color = Color.Cyan) {
            Column(modifier = modifier.padding(24.dp)) {
                Text(
                    text = "Hi, my name is $name!\nMy ID is $id"
                )

                //Button 1
                Button(onClick = {
                    if (hasPermission) {
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    }
                    else{
                        Toast.makeText(context, "Permission Required", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Start Activity Explicitly")
                }

                //Button 2
                Button(onClick = {
                    if (hasPermission){
                    val intent = Intent("com.example.wirriesassignment2.VIEW_SECOND")
                    context.startActivity(intent)}
                    else{
                        Toast.makeText(context, "Permission Required", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Start Activity Implicitly")
                }

                //Button 3 -> Camera activity
                Button(onClick = {
                    val intent = Intent(context, ThirdActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "View Image Activity")
                }
            }
        }
    }
}

