package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo

interface AssetsLocalDataSourceInterface {

    suspend fun getAssets(): List<CoinDbo>?

    suspend fun saveAssets(assets: List<CoinDbo>)

}