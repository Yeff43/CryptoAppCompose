package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDAO
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDBO
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.toDomain
import es.yeffry.cryptoappcompose.domain.entities.CoinsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetsLocalDataSourceImpl(private val assetsLocalApi: AssetsDAO) : AssetsLocalDataSource {
    override suspend fun getAssets(): CoinsList {

        return withContext(Dispatchers.IO) {
            CoinsList(assetsLocalApi.getAllAssets().map { it.toDomain() })
        }
    }

    override suspend fun saveAssets(assetsList: List<CoinDBO>) {
        assetsLocalApi.insert(assetsList)
    }
}