package com.paheco.morecompose

import Fruit
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import com.paheco.morecompose.ui.theme.MoreComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoreComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Maincomp()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Maincomp(){
    /*
    Column(modifier = Modifier
      .background(Color.Red)
    )

     */
    var errormessage = remember {
        mutableStateOf("")
    }
    var addfruitname = remember {
        mutableStateOf("")
    }
    var addfruitamount = remember {
        mutableStateOf(0)
    }
    var allfruit = remember {
        mutableStateListOf<Fruit>()
    }
    Column() {
        if(errormessage.value != ""){
            Text(errormessage.value)
        }
        Row() {
            TextField(value = addfruitname.value, onValueChange = {
                addfruitname.value = it
            }, modifier = Modifier.width(100.dp))
            TextField(value = addfruitname.value.toString(), onValueChange = {
                addfruitamount.value = it.toInt()
            }, modifier = Modifier.width(100.dp))
            Button(onClick = {
                if(addfruitamount.value.toString().toIntOrNull() == null) {
                    errormessage.value = "Inte en siffra"
                }
                else {
                    var afruit = Fruit()
                    afruit.fruitname = addfruitname.value
                    afruit.fruitamount = addfruitamount.value
                    afruit.fruitok = true
                    allfruit.add(afruit)
                }
            }) {
                Text("Lägg till")
            }
        }
        LazyColumn {
            items(allfruit) {
                Fruitrow(it)
            }
        }
    }

        //Text("Textfält")
        //Text("Lista med saker")
    }



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoreComposeTheme {
        Greeting("Android")
    }
}

 */