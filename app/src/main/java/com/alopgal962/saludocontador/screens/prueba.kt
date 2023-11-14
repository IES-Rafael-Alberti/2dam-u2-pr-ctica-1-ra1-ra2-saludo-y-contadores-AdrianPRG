package com.alopgal962.saludocontador.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    menuprincipal2(textorecibe = "", pantallainicial =false, 0 )
}

@Composable
@ExperimentalMaterial3Api
        /**
         * @param textorecibe es el texto que se mostrará en el texto abajo de introducir nombre
         * @param pantallainicial servira como un booleano, para que cuando se llame a menuprincipal desde menudialog empieze desde su pantalla inicial, de introducir nombre
         * @param accept es el parametro que recibira como contador cada vez que se pulse aceptar y se llame a esta funcion
         */
fun menuprincipal2(textorecibe:String, pantallainicial:Boolean, accept:Int){
    /**
     * Para que los valores no se restablezcan y cada vez que se llame a la funcion guarden su antiguo valor
     * se crea una variable remembersaveable de cada parametro
     */
    var nombre by rememberSaveable { mutableStateOf(textorecibe) }
    var semuestradialogo by rememberSaveable { mutableStateOf(pantallainicial) }
    var aceptarpulsado by rememberSaveable { mutableStateOf(accept) }
    /*
    Para llamar a otra funcion composable no se puede hacer por click en el boton,entones se creo un el booleano anteriormente
    mencionado, y cuando la condicion de que sea true se cumpla, se llama a la funcion menudialog
     */
    if (semuestradialogo){
        menudialog2(aceptarpulsado,false)
    }
    else{
        Column(modifier = Modifier.fillMaxSize().background(color = Color.LightGray), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ) {
            Button(modifier = Modifier.border(8.dp, Color.Red), colors = ButtonDefaults.buttonColors(
                Color.Yellow) , onClick = { semuestradialogo=true }) {
                /*
                Si el contador de el boton aceptar es cero, no debe aparecer la cantidad en el texto de boton, si es mayor a cero
                si debe aparecer
                 */
                if (aceptarpulsado > 0){
                    Text(text = "INTRODUCIR NOMBRE A $aceptarpulsado ", color = Color.Black, fontFamily = FontFamily.SansSerif, fontStyle = FontStyle.Italic)

                }
                else
                    Text(text = "INTRODUCIR NOMBRE", color = Color.Black, fontFamily = FontFamily.SansSerif, fontStyle = FontStyle.Italic)
            }
            /*
            Si el texto esta vacio, no debe de aparecer hola, en cambio si contiene texto, debe aparecer
            hola y el nombre introducido
             */
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
        /**
         * @param contadorparametro es el contador que guarda el numero de veces que se pulsa el boton aceptar, al igual que en el anterior
         * se almacena en un remember, para que cadavez que se llama a la funcion, guarde el valor que tenia
         * @param empiezanuevo es un booleano que se recibe , y dependiendo de su estado, se ejecuta un trozo de codigo u otro.
         * Cuando es false se ejecuta el dialogo con los botones y cuando no , se llama a la funcion menuprincipal para volver a ella
         *
         */
fun menudialog2(contadorparametro:Int, empiezanuevo:Boolean){
    var texto by rememberSaveable {
        mutableStateOf("") }
    var introducido by rememberSaveable { mutableStateOf(empiezanuevo)}
    var contadoraceptar by rememberSaveable { mutableStateOf(contadorparametro) }

    if (introducido==false){
        Column(modifier = Modifier.background(color = Color.LightGray).fillMaxSize()) {
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
                /*
                Boton que cambia el booleano y suma 1 al contador de aceptar
                 */
                confirmButton = {
                    Button(onClick = { introducido=true
                    contadoraceptar+=1}, modifier = Modifier.padding(start = 20.dp)) {
                        Text(text = "ACEPTAR")
                    }
                },
                /*
                Boton para limpiar el texto que se haya introducido
                 */
                dismissButton = { Button(onClick = { texto="" }) {
                    Text(text = "LIMPIAR")
                } },
            )

        }
    }
    else
        menuprincipal2(textorecibe = texto, pantallainicial = false, contadoraceptar)
}