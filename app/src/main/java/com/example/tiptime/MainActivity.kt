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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

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
    var amountInput by remember {
        mutableStateOf("")
    }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount = amount)

    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

//        O spacedBy serve para adicionar um espa??amento fixo entre os filhos dentro do Column
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(amountInput){
            amountInput = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


    }
}

@Composable
fun EditNumberField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(stringResource(id = R.string.cost_of_service)) }
    )
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0) =
    NumberFormat.getCurrencyInstance().format((tipPercent / 100) * amount)


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}

// A composi????o acontece apenas uma vez dentro do nosso app que ?? na cria????o do mesmo, desta forma
// quando queremos que nossa UI atualize o valor em tempo de execu????o ?? utilizando dos estados para fazer essa
// manipula????o, isso por qu?? o estado trabalha junto com a recomposi????o, avisando quando composable teve seu valor
// alterado fazendo com que o mesmo atualize a UI

// O compose fica observando cada composable que recebe uma variavel de estado e quando esse valor ?? alterado
// O pr??prio compose faz a recomposi????o.
// Quando a recomposi????o acontece, propriedades que n??o persistem seu valor ser??o reinicializadas, dessa forma
// al??m do estado ?? necess??rio fazer com que o estado persista seu valor e s?? seja alterado quando tiver intera????o do Usuario
// a fun????o remember trabalha em conjunto com o estado para atualizar e persistir os dados que foram salvos, para
//que no processo de recomposi????o o valor do estado n??o retorne ao valor inicial.

//O remember utiliza-se do by para delegar o getter e setter da var??avel para o remember

//Eleva????o de estado acontece sempre que ?? necess??rio compartilhar o estado com varias fun????es, ou criar um elemento sem estado
//fixo para ser reutilizav??l.
// Quando a eleva????o de estado acontece, geralmente ?? introduzidos dois par??mentros numa fun????o
    //value:T -> O valor atual a ser mostrado
    // onValueChange: (T) -> Unit, um lambda que ?? acionado quando o valor muda
    // para que dentro da fun????o que contra o estado possa ser alterado seu valor

//Formata????o posicional ?? quando utilizamos de forma din??mica a string atualizando o valor e uma posi????o dentro do seu conteudo

