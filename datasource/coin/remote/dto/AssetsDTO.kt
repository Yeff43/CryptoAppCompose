package es.yeffry.cryptoappcompose.datasource.coin.remote.dto

import com.google.gson.annotations.SerializedName

data class AssetsDTO(
    @SerializedName("data")
    val coinsDto: List<CoinDto> = listOf()
)