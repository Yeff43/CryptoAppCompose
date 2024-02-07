package es.yeffry.cryptoappcompose.datasource.coin.remote.dto

import com.google.gson.annotations.SerializedName
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.CoinDTO

data class AssetsDTO(
    @SerializedName("data")
    val coinsListDTO: List<CoinDTO> = listOf()
)