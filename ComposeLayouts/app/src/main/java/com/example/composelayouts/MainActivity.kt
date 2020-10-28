package com.example.composelayouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composelayouts.ui.ComposeLayoutsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    setUpView()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    /*
     Mientras la imagen es leída, vamos a poner un placeholder, ponemos el surface como circleShape
     y con eso logramos un loading.

    El orden de los modificadores es importante si van concatenados en un argumento solo.
    Ejemplo: hacemos clickeable la imagen, se puede ver que como configuramos un padding, cuando
    hacemos click este se resalta

    con Clip agregamos un estilo redondeado a las esquinas del item
    con background cambiamos el fondo
    * */
    Row(modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(16.dp)
    ) {

        Surface(
                modifier = Modifier.preferredSize(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }
        Column {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            Text("3 minutes ago", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun setUpView() {
    /*
    Scaffold le permite implementar una interfaz de usuario con la estructura de diseño básica
    de Material Design. Te da Slots para los componentes: TopAppBar, BottomAppBar,
    FloatingActionButton y Drawer.
    Con el Scaffold te aseguras de colocar esos compoenentes y que funcionen juntos de manera correcta
     */
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite)
                    }
                }
            )
        }
    ) {  innerPadding ->
        /*
        Podemos ver que no solo se puede pasar el inner padding por defecto sino que tambien le podes
        meter uno customizado
         */
        PhotographerCard(Modifier.padding(innerPadding).padding(8.dp))
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    ComposeLayoutsTheme {
        setUpView()
    }
}
