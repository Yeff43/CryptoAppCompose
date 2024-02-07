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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.yeffry.cryptoappcompose.R
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
    var query by remember { mutableStateOf("") }
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
                uiState.coinASymbol,
                uiState.coinBSymbol
            )
            CoinRow(uiState.coinNames, 0, viewModel, uiState)
            Image(
                painter = painterResource(id = R.drawable.ic_exchange),
                contentDescription = "Image arrow exchange",
                modifier = Modifier
                    .size(58.dp)
                    .padding(top = 18.dp)
                    .clickable(enabled = true) {}
            )
            CoinRow(uiState.coinNames, 1, viewModel, uiState)
            Button(modifier = Modifier.padding(top = 16.dp), onClick = { }) {
                Text(text = "GET")
            }
        }
    }
}

@Composable
fun CoinRow(
    coins: List<String>,
    initIndex: Int,
    viewModel: MainActivityViewModel,
    uiState: MainUiState
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        DropDownMenuCoin(coins, initIndex, viewModel, uiState.coinAQuantity, uiState.coinBQuantity)
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
    coins: List<String>,
    initIndex: Int,
    viewModel: MainActivityViewModel,
    quantityCoinA: Double,
    quantityCoinB: Double
) {
    coins.takeIf { it.isNotEmpty() }?.let {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(coins[initIndex]) }

        Row {
            Box(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier.width(120.dp)
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        coins.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedText = item
                                    expanded = false
                                    when (initIndex) {
                                        0 -> viewModel.saveCoinASymbol(selectedText)
                                        1 -> viewModel.saveCoinBSymbol(selectedText)
                                    }
                                }
                            )
                        }
                    }
                }
            }
            val init =  when (initIndex) {
                0 -> quantityCoinA.toString()
                1 -> quantityCoinB.toString()
                else -> {
                    0.0
                }
            }
            var value by remember {
                mutableStateOf(init)
            }
            TextField(
                value = value.toString(),
                onValueChange = {
                    value = it
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
    }
}