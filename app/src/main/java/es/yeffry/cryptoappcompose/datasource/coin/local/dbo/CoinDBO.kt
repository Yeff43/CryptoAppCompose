package es.yeffry.cryptoappcompose.datasource.coin.local.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.yeffry.cryptoappcompose.domain.entities.Coin

@Entity(tableName = "assets")
data class CoinDbo(
    @PrimaryKey() val coinId: String,
    val symbol: String = "",
    val name: String = "",
    val priceUsd: String = ""
)