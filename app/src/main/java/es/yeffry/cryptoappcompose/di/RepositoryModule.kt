package es.yeffry.cryptoappcompose.di

import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.repository.AssetsRepositoryImpl
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.yeffry.cryptoappcompose.di.Mock

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAssetsRepository(
        assetsRemoteDataSource: AssetsDataSourceInterface,
        assetsLocalDataSourceInterface: AssetsLocalDataSourceInterface,
        @Mock assetsMockDatasource: AssetsDataSourceInterface
    ): CoinsRepository =
        AssetsRepositoryImpl(assetsRemoteDataSource, assetsLocalDataSourceInterface, assetsMockDatasource)
}