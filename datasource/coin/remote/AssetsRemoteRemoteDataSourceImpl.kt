package es.yeffry.cryptoappcompose.datasource.coin.remote

import es.yeffry.cryptoappcompose.datasource.coin.AssetsRemoteDataSource
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.CoinDto
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.network.NetworkManager
import javax.inject.Inject

class AssetsRemoteRemoteDataSourceImpl @Inject constructor(
    private val assetsRemoteApi: AssetsApi,
    private val network: NetworkManager
) :
    AssetsRemoteDataSource {
    override suspend fun getAssets(): List<CoinDto> {
        val result = network.load { assetsRemoteApi.getAssets() }
        result.getOrNull()?.let { coinsDTO ->
            return coinsDTO.coinsDto
        }
        return listOf()
    }

    override suspend fun saveAssets(assetsList: List<CoinDbo>) {
    }
}