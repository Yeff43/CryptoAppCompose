package es.yeffry.cryptoappcompose.datasource.coin.local

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.domain.entities.Coins

interface AssetsLocalDataSource {

    suspend fun getAssets(): Coins

    suspend fun saveAssets(assets: List<CoinDbo>)

}