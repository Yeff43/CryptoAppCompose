package com.yeffry.cryptoapp.data.datasource.coin.local.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.yeffry.cryptoappcompose.domain.entities.Coin

@Entity(tableName = "assets")
data class CoinDBO(
    @PrimaryKey() val coinId: String,
    val symbol: String = "",
    val name: String = "",
    val priceUsd: String = ""
)

fun CoinDBO.toDomain(): Coin {
    return Coin(
        coinId,
        symbol,
        name,
        priceUsd
    )
}