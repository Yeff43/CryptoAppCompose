@file:OptIn(ExperimentalMaterial3Api::class)

package es.yeffry.cryptoappcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.yeffry.cryptoappcompose.R
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.presentation.components.CustomBottomBar
import es.yeffry.cryptoappcompose.presentation.components.Header
import es.yeffry.cryptoappcompose.ui.theme.CryptoAppComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CryptoApp(hiltViewModel<MainActivityViewModel>())
                }
            }
        }
    }
}

@Composable
fun CryptoApp(viewModel: MainActivityViewModel) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            Header(
                title = "Crypto App"
            )
        },
        bottomBar = { CustomBottomBar() },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(it)
        ) {
            it.calculateTopPadding()

            ExchangeResume(
                uiState.coinA.symbol,
                uiState.coinB.symbol
            )
            CoinRow(uiState.coinA, uiState.quantityCoinA, viewModel, 0)
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_exchange),
                    contentDescription = "Image arrow exchange",
                    modifier = Modifier
                        .size(58.dp)
                        .padding(top = 18.dp)
                        .clickable(
                            enabled = true,
                            onClick = ({
                                viewModel.swapCoins(
                                    uiState.coinA,
                                    uiState.coinB,
                                    uiState.quantityCoinA,
                                    uiState.quantityCoinB,
                                    0
                                )
                            })
                        )
                        .align(Alignment.CenterVertically)
                )
            }
            CoinRow(uiState.coinB, uiState.quantityCoinB, viewModel, 1)
            Row {
                Text(
                    text = uiState.rate,
                    maxLines = 1,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp, 0.dp),
                    color = Color.White
                )
            }
            Button(modifier = Modifier.padding(top = 16.dp), onClick = { }) {
                Text(text = "GET")
            }
        }
    }
}

@Composable
fun CoinRow(
    coin: Coin,
    quantity: String,
    viewModel: MainActivityViewModel,
    row: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        DropDownMenuCoin(viewModel, coin, row, quantity)
    }
}

@Composable
fun ExchangeResume(coinA: String, coinB: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            shape = CircleShape, modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .padding(48.dp, 140.dp, 48.dp, 0.dp)
        ) {
            Text(
                "Exchange",
                modifier = Modifier
                    .padding(8.dp, 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = coinA,
                        modifier = Modifier.padding(0.dp, 0.dp),
                        fontSize = 22.sp
                    )
                }

                Column(modifier = Modifier.padding(8.dp, 0.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "Image arrow right",
                        modifier = Modifier.size(36.dp)
                    )
                }

                Column {
                    Text(
                        text = coinB,
                        modifier = Modifier.padding(0.dp, 0.dp),
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DropDownMenuCoin(
    viewModel: MainActivityViewModel,
    coin: Coin,
    row: Int,
    quantity: String
) {
    val selectedText by lazy { mutableStateOf(coin) }
    val expanded = remember { mutableStateOf(false) }
    val coins by lazy { mutableStateOf(viewModel.uiState.value.coins) }

    Row {
        Box(
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = {
                    expanded.value = it
                },
                modifier = Modifier.width(120.dp)
            ) {
                TextField(
                    value = selectedText.value.symbol,
                    onValueChange = { symbol: String ->
                        viewModel.loadCalculatedRate(row)
                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    coins.value.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item.symbol) },
                            onClick = {
                                selectedText.value = item
                                expanded.value = false
                                viewModel.saveCoin(row, item)
                            }
                        )
                    }
                }
            }
        }

        val text by lazy { mutableStateOf(quantity) }
        var finalText = text
        TextField(
            value = text.value,
            onValueChange = {
                finalText.value = it
                viewModel.saveQuantity(row, finalText.value)
                viewModel.loadCalculatedRate(row)
            },
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}