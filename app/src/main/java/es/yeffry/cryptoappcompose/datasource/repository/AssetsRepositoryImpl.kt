package es.yeffry.cryptoappcompose.datasource.repository

import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import es.yeffry.cryptoappcompose.domain.repository.MapperToDomain
import es.yeffry.cryptoappcompose.domain.repository.MapperToLocal
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val assetsRemoteDataSource: AssetsDataSourceInterface,
    private val assetsLocalDataSource: AssetsLocalDataSourceInterface,
    private val assetsMockDatasource: AssetsDataSourceInterface
) : CoinsRepository {

    private val mapperCoinDto = object : MapperToDomain<AssetDto, Coin> {
        override fun AssetDto.toDomain(): Coin {
            return Coin(coinId, symbol, name, priceUsd)
        }
    }

    private val mapperCoinDbo = object : MapperToDomain<CoinDbo, Coin> {
        override fun CoinDbo.toDomain(): Coin {
            return Coin(coinId, symbol, name, priceUsd)
        }
    }

    private val mapperCoinToLocal = object : MapperToLocal<Coin, CoinDbo> {
        override fun Coin.toLocal(): CoinDbo {
            return CoinDbo(coinId, symbol, name, priceUsd)
        }
    }

    override suspend fun getCoins(): List<Coin> {
        with(mapperCoinDto) {
            val localResponse = assetsLocalDataSource.getAssets()
            return localResponse?.takeIf { it.isNotEmpty() }?.let {
                with(mapperCoinDbo) {
                    it.map { it.toDomain() }
                }
            } ?: run {
                with(mapperCoinToLocal) {
                    val remoteResponse =
                        assetsRemoteDataSource.getAssets()
                            .map { it.toDomain() }
                    assetsLocalDataSource.saveAssets(remoteResponse.map { it.toLocal() })
                    remoteResponse
                }
            }
        }
    }
}