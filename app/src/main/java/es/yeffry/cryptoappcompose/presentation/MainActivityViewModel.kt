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
    val coinASymbol: String = "",
    val coinBSymbol: String = "",
    val coinAQuantity: Double = 0.0,
    val coinBQuantity: Double = 0.0,
    val rate: String = "Holding for selection",
    val isGetAvailable: Boolean = false,
    val errorMessage: String = "",
    val coinNames: List<String> = listOf()
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val coinsUseCase: CoinsUseCase) :
    ViewModel() {
    private var scope = CoroutineScope(Dispatchers.IO)
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState
    private var coins: List<Coin> = listOf()
    private var currentRate: Double = 0.0

    init {
        scope.launch {
            try {
                coins = coinsUseCase.getCoins()
                _uiState.update { uiState ->
                    uiState.copy(
                        coinNames = coins.map { it.symbol },
                        coinASymbol = coins[0].symbol,
                        coinBSymbol = coins[1].symbol
                    )
                }
            } catch (e: CustomException) {
                _uiState.update { it.copy(errorMessage = e.message.toString()) }
            }
        }
    }

    fun saveCoinASymbol(symbol: String) {
        _uiState.update { it.copy(coinASymbol = symbol) }
    }

    fun saveCoinBSymbol(symbol: String) {
        _uiState.update { it.copy(coinBSymbol = symbol) }
    }

    fun saveCoinAQuantity(quantity: String) {
        quantity.toDouble().takeIf { !it.isNaN() }?.let {
            _uiState.update { it.copy(coinAQuantity = quantity.toDouble()) }
        }
    }

    fun saveCoinBQuantity(quantity: String) {
        quantity.toDouble().takeIf { !it.isNaN() }?.let {
            _uiState.update { it.copy(coinBQuantity = quantity.toDouble()) }
        }
    }
}