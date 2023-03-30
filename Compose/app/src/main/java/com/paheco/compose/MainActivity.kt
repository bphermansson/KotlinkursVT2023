package com.paheco.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paheco.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Greeting("Patrik", "Hermansson")
                        Text(text = "Mera text")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, lastname: String) {
    var fancymessage by remember {mutableStateOf("Hej")}

    Column(modifier = Modifier.background(Color.LightGray).padding(24.dp)) {
        Text(text = "Hello $name $lastname!")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Tjena $lastname!")

        Text("hepp", Modifier.padding(28.dp), color = Color.Red)

        Text(fancymessage)
        Fancylist()

        Button(onClick = {
            fancymessage = "Banan"
        }) {
            Text("Klicka här!!!")
        }
    }

}

@Composable
fun Fancylist(){


    ///var fruits by remember { mutableStateListOf("j", "q") }

    var fancymessage by remember {mutableStateOf("Hej")}

    var fruits = mutableListOf<String>("A", "B", "C")

    Column {
        Button(onClick = {
            fruits.add("Kiwi")
         }) {
            Text("Lägg till")
        }
        LazyColumn{
            items(fruits.toList()){
                Text(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Column() {
            Greeting("Patrik", "Hermansson")
            Text(text = "Mera text")
            Fancylist()
        }    
    }
}