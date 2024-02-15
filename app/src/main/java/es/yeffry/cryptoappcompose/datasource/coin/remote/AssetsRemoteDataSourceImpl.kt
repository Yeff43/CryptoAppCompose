package es.yeffry.cryptoappcompose.datasource.coin.remote

import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto
import es.yeffry.cryptoappcompose.network.NetworkManager
import javax.inject.Inject

class AssetsRemoteDataSourceImpl @Inject constructor(
    private val assetsRemoteApi: AssetsApi,
    private val network: NetworkManager
) :
    AssetsDataSourceInterface {
    override suspend fun getAssets(): List<AssetDto> {
        val result = network.load { assetsRemoteApi.getAssets() }
        return result.getOrThrow() ?: listOf()
    }
}