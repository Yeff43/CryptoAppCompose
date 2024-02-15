package es.yeffry.cryptoappcompose.domain.repository

import es.yeffry.cryptoappcompose.domain.entities.Coin

interface CoinsRepository {
    suspend fun getCoins(): List<Coin>?
}