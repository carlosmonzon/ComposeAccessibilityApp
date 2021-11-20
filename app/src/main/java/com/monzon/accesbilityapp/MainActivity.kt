package com.monzon.accesbilityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccesbilityAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AccesbilityAppTheme {
        Greeting("Android")
    }
}

@Composable
fun MyTopAppBar(title: String) {

    val isAccessibilityOn = remember { mutableStateOf(false) }
    TopAppBar(
        elevation = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            Switch(checked = isAccessibilityOn.value, onCheckedChange = {
                isAccessibilityOn.value = it
            })

//            IconToggleButton(checked = liked.value, onCheckedChange = {
//                liked.value = it
//            }) {
//                val tint by animateColorAsState(
//                    if (liked.value) Color(0xFF7BB661)
//                    else Color.LightGray
//                )
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    tint = tint
//                )
//            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    AccesbilityAppTheme {
        MyTopAppBar(title = "Accessibility demo")
    }
}