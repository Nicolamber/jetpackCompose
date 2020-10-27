package com.example.myfirstcompose

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.myfirstcompose.ui.MyFirstComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp {
                //greeting(name = "Nico!")
                myScreenContent()
            }
        }
    }
}

@Composable
fun myApp(content: @Composable () -> Unit) {
    MyFirstComposeTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun greeting(name: String) {
    // con el style podes modificar el tema a nivel local en la funcion, sin ir a tocar el Theme.kt
    Text(
            text = "Hello $name",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.h3
    )
}

// declarative UI
@Composable
fun myScreenContent(names: List<String> = listOf("Nico!", "Mercado Libre", "Digital Accounts")) {
    //Agregamos el remember para mantener los estados y con el mutableStateOf creas el compose del estado
    val counterState = remember { mutableStateOf(0) }

    //con estos Fill lo que hago es llenar toda la pantalla y volverla inflexible
    Column(modifier = Modifier.fillMaxHeight()) {
        //Si uso el weight solo por ejemplo, hago que la columna ocupe solo que necesite.
        Column(modifier = Modifier.weight(1f)) {
            for (name in names) {
                greeting(name = name)
                Divider(color = Color.Black)
            }
            Divider(color = Color.Transparent, thickness = 32.dp)
        }
        counter(
                count = counterState.value,
                updateCount = { newCount -> counterState.value = newCount }
        )
        Divider(color = Color.Transparent, thickness = 8.dp)
    }
}

//State
@Composable
/*Mandando los parametros lo que generamos es un "state hosting" que es una manera interna que tenemos
de controlar el estado por la funcion que esta llamando
Esto nos da como beneficio evitar que se dupliquen estados, reusar composables y hacerlo testeables*/
fun counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
            onClick = { updateCount(count + 1) },
            backgroundColor = if (count > 7) Color.Green else Color.White
    ) {
        Text(text = "Click número: $count")
    }
}


@Preview("Text preview")
@Composable
fun DefaultPreview() {
    myApp {
        //greeting(name = "Nico!")
        myScreenContent()
    }
}