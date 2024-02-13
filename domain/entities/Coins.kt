package es.yeffry.cryptoappcompose.domain.entities

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo

data class Coins(
    val coins: List<CoinDbo>
)