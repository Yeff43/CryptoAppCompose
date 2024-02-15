package es.yeffry.cryptoappcompose.di

import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceImpl
import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.remote.AssetsRemoteDataSourceImpl
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import es.yeffry.cryptoappcompose.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.yeffry.cryptoappcompose.datasource.coin.mockDataSource.AssetsMockDataSourceImpl
import javax.inject.Qualifier

@Qualifier
annotation class Mock
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideAssetsRemoteDataSource(
        api: AssetsApi,
        networkManager: NetworkManager
    ): AssetsDataSourceInterface =
        AssetsRemoteDataSourceImpl(api, networkManager)


    @Provides
    fun provideAssetsLocalDataSource(
        api: AssetsDao
    ): AssetsLocalDataSourceInterface = AssetsLocalDataSourceImpl(api)

    @Mock
    @Provides
    fun provideAssetsMockDataSource(): AssetsDataSourceInterface = AssetsMockDataSourceImpl()
}