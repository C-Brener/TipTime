package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

//        O spacedBy serve para adicionar um espaçamento fixo entre os filhos dentro do Column
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField()

    }
}

@Composable
fun EditNumberField() {
    var amountInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = amountInput,
        onValueChange = {
            amountInput = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number) ,
        label = { Text(stringResource(id = R.string.cost_of_service)) }
    )
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}

// A composição acontece apenas uma vez dentro do nosso app que é na criação do mesmo, desta forma
// quando queremos que nossa UI atualize o valor em tempo de execução é utilizando dos estados para fazer essa
// manipulação, isso por quê o estado trabalha junto com a recomposição, avisando quando composable teve seu valor
// alterado fazendo com que o mesmo atualize a UI

// O compose fica observando cada composable que recebe uma variavel de estado e quando esse valor é alterado
// O próprio compose faz a recomposição.
// Quando a recomposição acontece, propriedades que não persistem seu valor serão reinicializadas, dessa forma
// além do estado é necessário fazer com que o estado persista seu valor e só seja alterado quando tiver interação do Usuario
// a função remember trabalha em conjunto com o estado para atualizar e persistir os dados que foram salvos, para
//que no processo de recomposição o valor do estado não retorne ao valor inicial.

//O remember utiliza-se do by para delegar o getter e setter da varíavel para o remember

