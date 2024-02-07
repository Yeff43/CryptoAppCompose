package es.yeffry.cryptoappcompose.datasource.repository

import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSource
import es.yeffry.cryptoappcompose.datasource.coin.AssetsRemoteDataSource
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.entities.toLocal
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val assetsRemoteDataSource: AssetsRemoteDataSource,
    private val assetsLocalDataSource: AssetsLocalDataSource
) : CoinsRepository {
    override suspend fun getCoinsList(): List<Coin> {
        val remoteResult = assetsRemoteDataSource.getAssets()
        return if (remoteResult.isEmpty()){
            assetsLocalDataSource.getAssets().coinsList
        }else {
            assetsLocalDataSource.saveAssets(remoteResult.map { it.toLocal() })
            remoteResult
        }
    }
}