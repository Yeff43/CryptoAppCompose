package es.yeffry.cryptoappcompose.domain.usecase.assets

import es.yeffry.cryptoappcompose.domain.entities.Coin

interface CoinsUseCase {
    suspend fun getCoins(): List<Coin>

}