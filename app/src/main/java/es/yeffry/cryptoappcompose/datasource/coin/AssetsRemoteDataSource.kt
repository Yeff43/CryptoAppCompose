package es.yeffry.cryptoappcompose.datasource.coin

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDBO
import es.yeffry.cryptoappcompose.domain.entities.Coin

interface AssetsRemoteDataSource {

    suspend fun getAssets(): List<Coin>

    suspend fun saveAssets(assetsList: List<CoinDBO>)

}