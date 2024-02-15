package es.yeffry.cryptoappcompose.domain.entities

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id")
    val coinId: String = "",
    val symbol: String = "",
    val name: String = "",
    val priceUsd: String = "",
    val image: String? = "",
)