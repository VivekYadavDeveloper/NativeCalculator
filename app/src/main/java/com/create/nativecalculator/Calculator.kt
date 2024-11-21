package com.create.nativecalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview(showBackground = true, showSystemUi = true, apiLevel = 35, device = "id:pixel_7")

fun DemoPreview(modifier: Modifier = Modifier) {

    val CalculatorViewModel = CalculatorViewModel()
    Calculator(viewModel = CalculatorViewModel)
}

val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "x",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "DEL", "0", ".", "="
)

@Composable

fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()
    Box(modifier = Modifier.padding(15.dp)) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                equationText.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                resultText.value ?: "",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                modifier = Modifier.size(400.dp),
                columns = GridCells.Fixed(4),
            ) {
                items(buttonList) {
                    CalculatorButton(btn = it, onCLick = { viewModel.onButtonClick(it) })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onCLick: () -> Unit) {
    Box(
        modifier = Modifier.padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            onClick = onCLick,
            shape = CircleShape,
            containerColor = getColor(btn)
        ) {
            Text(btn, style = TextStyle(fontSize = 20.sp));
        }

    }

}

fun getColor(btn: String): Color {
    if (btn == "C" || btn == "DEL")
        return Color(0xFFF44336)
    if (btn == "(" || btn == ")")
        return Color(0xFFC2C2C2)
    if (btn == "+" || btn == "-" || btn == "x" || btn == "/")
        return Color(0xFFFF9800)
    return Color(0xFF4CAF50)

}