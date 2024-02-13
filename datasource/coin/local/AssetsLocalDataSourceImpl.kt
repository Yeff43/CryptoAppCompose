package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.domain.entities.Coins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetsLocalDataSourceImpl(private val assetsLocalApi: AssetsDao) : AssetsLocalDataSource {

    override suspend fun getAssets(): Coins {
        return withContext(Dispatchers.IO) {
                Coins(assetsLocalApi.getAssets())
        }
    }

    override suspend fun saveAssets(assets: List<CoinDbo>) {
        assetsLocalApi.insert(assets)
    }
}