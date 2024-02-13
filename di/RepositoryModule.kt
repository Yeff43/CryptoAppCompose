package com.yeffry.cryptoapp.di

import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSource
import es.yeffry.cryptoappcompose.datasource.coin.AssetsRemoteDataSource
import es.yeffry.cryptoappcompose.datasource.repository.AssetsRepositoryImpl
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAssetsRepository(assetsRemoteDataSource: AssetsRemoteDataSource, assetsLocalDataSource: AssetsLocalDataSource): CoinsRepository =
        AssetsRepositoryImpl(assetsRemoteDataSource, assetsLocalDataSource)
}