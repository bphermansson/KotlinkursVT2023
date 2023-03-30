package com.paheco.morecompose

import Fruit
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paheco.morecompose.ui.theme.MoreComposeTheme

@Composable
fun Fruitrow(fruit : Fruit){
    Row(modifier = Modifier.clickable {
        Log.i("Hi", "Click")
        //fruit.fruitok = false
    }) {
        Text(fruit.fruitname, modifier = Modifier.padding(5.dp))
        Column() {
            Text(fruit.fruitamount.toString())
            if(fruit.fruitok) {
                Text("Är ok")
            }
            else {
                Text("Inte ok")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FruitPreview() {

    var pfruit = Fruit()
    pfruit.fruitname = "Apelsin"
    pfruit.fruitamount = 8
    pfruit.fruitok = true

    MoreComposeTheme {
        Fruitrow(pfruit)
    }
}