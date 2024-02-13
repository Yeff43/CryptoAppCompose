package es.yeffry.cryptoappcompose.datasource.coin.remote.dto

import com.google.gson.annotations.SerializedName
import es.yeffry.cryptoappcompose.domain.entities.Coin

data class CoinDto(
    @SerializedName("id") val coinId: String,
    val rank: String? = null,
    val symbol: String = "",
    val name: String = "",
    val supply: String? = null,
    val maxSupply: String? = null,
    val marketCapUsd: String? = null,
    val volumeUsd24Hr: String? = null,
    val priceUsd: String = "",
    val changePercent24Hr: String? = null,
    val vwap24Hr: String? = null
)