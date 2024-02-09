package es.yeffry.cryptoappcompose.domain.entities

import com.google.gson.annotations.SerializedName
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDBO

data class Coin(
    @SerializedName("id")
    val coinId: String = "",
    val symbol: String = "",
    val name: String = "",
    val priceUsd: String = "",
    val image: String = "",
)

fun Coin.toLocal(): CoinDBO {
    return CoinDBO(
        coinId,
        symbol,
        name,
        priceUsd
    )
}