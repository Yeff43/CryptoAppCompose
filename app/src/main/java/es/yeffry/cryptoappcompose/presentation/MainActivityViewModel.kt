package es.yeffry.cryptoappcompose.presentation

import androidx.lifecycle.ViewModel
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.entities.CustomException
import es.yeffry.cryptoappcompose.domain.usecase.assets.CoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val coinA: Coin = Coin(),
    val coinB: Coin = Coin(),
    val quantityCoinA: String = "0.0",
    val quantityCoinB: String = "0.0",
    val rate: String = "Holding for selection",
    val isGetAvailable: Boolean = false,
    val errorMessage: String = "",
    val coins: List<Coin> = listOf()
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val coinsUseCase: CoinsUseCase) :
    ViewModel() {
    private var scope = CoroutineScope(Dispatchers.IO)
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        scope.launch {
            try {
                val res = coinsUseCase.getCoins()
                res.takeIf { it.isNotEmpty() }?.let {
                    _uiState.update { uiState ->
                        uiState.copy(
                            coins = res,
                            coinA = res[0],
                            coinB = res[1],
                        )
                    }
                }
            } catch (e: CustomException) {
                _uiState.update { it.copy(errorMessage = e.message.toString()) }
            }
        }
    }

    fun loadCalculatedRate(row: Int) {
        if (_uiState.value.coins.isNotEmpty()) {
            val rateA =
                uiState.value.coinA.priceUsd.toBigDecimal() / uiState.value.coinB.priceUsd.toBigDecimal()
            val rateB =
                uiState.value.coinB.priceUsd.toBigDecimal() / uiState.value.coinA.priceUsd.toBigDecimal()
            val rateString =
                "1 ${uiState.value.coinA.symbol} = $rateA ${uiState.value.coinB.symbol}"

            when (row) {
                0 -> {
                    _uiState.update {
                        it.copy(
                            rate = rateString,
                            quantityCoinB = ((rateA * _uiState.value.quantityCoinA
                                .toBigDecimal()).toString())
                        )
                    }
                }

                1 -> {
                    _uiState.update {
                        it.copy(
                            rate = rateString,
                            quantityCoinA = ((rateB * _uiState.value.quantityCoinB
                                .toBigDecimal()).toString())
                        )
                    }
                }
            }
        }
    }

    fun saveCoin(row: Int, coin: Coin) {
        when (row) {
            0 -> {
                _uiState.update { it.copy(coinA = coin) }
            }

            1 -> {
                _uiState.update { it.copy(coinB = coin) }
            }
        }
    }

    fun saveQuantity(row: Int, quantity: String) {
        var newQuantity = quantity

        newQuantity.takeIf { it.matches(Regex("^[1-9]\\d*([.,]\\d+)\$")) }?.let {
            if (newQuantity.contains(",")) {
                newQuantity = newQuantity.replace(",", ".")
            }
            when (row) {
                0 -> {
                    _uiState.update { it.copy(quantityCoinA = newQuantity) }
                }

                1 -> {
                    _uiState.update { it.copy(quantityCoinB = newQuantity) }
                }
            }
        }
    }
}