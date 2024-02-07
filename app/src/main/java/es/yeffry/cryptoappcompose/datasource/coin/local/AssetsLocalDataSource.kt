package es.yeffry.cryptoappcompose.datasource.coin.local

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDBO
import es.yeffry.cryptoappcompose.domain.entities.CoinsList

interface AssetsLocalDataSource {

    suspend fun getAssets(): CoinsList

    suspend fun saveAssets(assetsList: List<CoinDBO>)

}