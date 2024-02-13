package es.yeffry.cryptoappcompose.datasource.repository

import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSource
import es.yeffry.cryptoappcompose.datasource.coin.AssetsRemoteDataSource
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.CoinDto
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import es.yeffry.cryptoappcompose.domain.repository.MapperToDomain
import es.yeffry.cryptoappcompose.domain.repository.MapperToLocal
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val assetsRemoteDataSource: AssetsRemoteDataSource,
    private val assetsLocalDataSource: AssetsLocalDataSource
) : CoinsRepository {

    private val mapperCoinDto = object : MapperToDomain<CoinDto, Coin> {
        override fun CoinDto.toDomain(): Coin {
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
            val remoteResource = assetsRemoteDataSource.getAssets().map { it.toDomain() }

            return if (remoteResource.isEmpty()) {
                with(mapperCoinDbo) {
                    assetsLocalDataSource.getAssets().coins.map { it.toDomain() }
                }
            } else {
                with(mapperCoinToLocal) {
                    assetsLocalDataSource.saveAssets(remoteResource.map { it.toLocal() })
                    remoteResource
                }
            }
        }
    }
}