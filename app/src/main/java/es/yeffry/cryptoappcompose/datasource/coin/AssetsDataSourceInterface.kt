package es.yeffry.cryptoappcompose.datasource.coin

import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto

interface AssetsDataSourceInterface {

    suspend fun getAssets(): List<AssetDto>
}