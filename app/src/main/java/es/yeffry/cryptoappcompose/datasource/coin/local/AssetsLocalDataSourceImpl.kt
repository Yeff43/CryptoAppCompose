package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetsLocalDataSourceImpl(private val assetsLocalApi: AssetsDao) : AssetsLocalDataSourceInterface {

    override suspend fun getAssets(): List<CoinDbo>? {
        return withContext(Dispatchers.IO) {
            assetsLocalApi.getAssets()
        }
    }

    override suspend fun saveAssets(assets: List<CoinDbo>) {
        assetsLocalApi.saveCoins(assets)
    }
}