package com.alopgal962.saludocontador.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@ExperimentalMaterial3Api
class prueba: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMenu()
        }
    }
}
@ExperimentalMaterial3Api
@Composable
@Preview
fun PreviewMenu(){
    menuprincipal2(textorecibe = "", volver =false, 0 )
}

@Composable
@ExperimentalMaterial3Api
fun menuprincipal2(textorecibe:String,volver:Boolean, accept:Int){
    var nombre by rememberSaveable { mutableStateOf(textorecibe) }
    var semuestradialogo by rememberSaveable { mutableStateOf(volver) }
    var aceptarpulsado by rememberSaveable { mutableStateOf(accept) }
    if (semuestradialogo){
        menudialog2(aceptarpulsado,false)
    }
    else{
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ) {
            Button(modifier = Modifier.border(8.dp, Color.Red), colors = ButtonDefaults.buttonColors(
                Color.Yellow) , onClick = { semuestradialogo=true }) {
                if (aceptarpulsado > 0){
                    Text(text = "INTRODUCIR NOMBRE A $aceptarpulsado ", color = Color.Black, fontFamily = FontFamily.SansSerif, fontStyle = FontStyle.Italic)

                }
                else
                    Text(text = "INTRODUCIR NOMBRE", color = Color.Black, fontFamily = FontFamily.SansSerif, fontStyle = FontStyle.Italic)
            }
            if (nombre.isEmpty()){
                Text(modifier= Modifier
                    .padding(top = 35.dp)
                    .background(color = Color.DarkGray)
                    .border(2.dp, color = Color.Yellow)
                    .size(150.dp, 20.dp), textAlign = TextAlign.Center,text ="", color = Color.White, fontFamily = FontFamily.Monospace)
            }
            else
                Text(modifier= Modifier
                    .padding(top = 35.dp)
                    .background(color = Color.DarkGray)
                    .border(2.dp, color = Color.Yellow)
                    .size(150.dp, 20.dp), textAlign = TextAlign.Center,text = "Hola, $nombre", color = Color.White, fontFamily = FontFamily.Monospace)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun menudialog2(contadorparametro:Int, empiezanuevo:Boolean){
    var texto by rememberSaveable {
        mutableStateOf("") }
    var introducido by rememberSaveable { mutableStateOf(empiezanuevo)}
    var contadoraceptar by rememberSaveable { mutableStateOf(contadorparametro) }

    if (introducido==false){
        Column {
            AlertDialog(
                modifier = Modifier.size(350.dp, 300.dp).padding(top = 15.dp, end = 20.dp),
                title = { Text(text = "Configuracion", modifier = Modifier.padding(start = 90.dp)) },
                onDismissRequest = { /*TODO*/ },
                text = {
                    TextField(
                        value = texto,
                        onValueChange = { texto = it },
                        label = { Text("INTRODUCE NOMBRE") }
                    )
                },
                confirmButton = {
                    Button(onClick = { introducido=true
                    contadoraceptar+=1}, modifier = Modifier.padding(start = 20.dp)) {
                        Text(text = "ACEPTAR")
                    }
                },
                dismissButton = { Button(onClick = { texto="" }) {
                    Text(text = "LIMPIAR")
                } },
            )

        }
    }
    else
        menuprincipal2(textorecibe = texto, volver = false, contadoraceptar)
}