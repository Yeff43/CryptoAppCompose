package com.yeffry.cryptoapp.di

import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSource
import es.yeffry.cryptoappcompose.datasource.coin.AssetsRemoteDataSource
import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceImpl
import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.remote.AssetsRemoteRemoteDataSourceImpl
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import es.yeffry.cryptoappcompose.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideAssetsRemoteDataSource(
        api: AssetsApi,
        networkManager: NetworkManager
    ): AssetsRemoteDataSource =
        AssetsRemoteRemoteDataSourceImpl(api, networkManager)


    @Provides
    fun provideAssetsLocalDataSource(
        api: AssetsDao
    ): AssetsLocalDataSource = AssetsLocalDataSourceImpl(api)
}