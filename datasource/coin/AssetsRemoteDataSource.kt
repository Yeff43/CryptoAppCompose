package es.yeffry.cryptoappcompose.datasource.coin

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.CoinDto
import es.yeffry.cryptoappcompose.domain.entities.Coin

interface AssetsRemoteDataSource {

    suspend fun getAssets(): List<CoinDto>

    suspend fun saveAssets(assetsList: List<CoinDbo>)

}