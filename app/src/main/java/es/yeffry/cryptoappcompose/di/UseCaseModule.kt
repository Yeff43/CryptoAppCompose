package com.yeffry.cryptoapp.di

import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import es.yeffry.cryptoappcompose.domain.usecase.assets.CoinsUseCase
import es.yeffry.cryptoappcompose.domain.usecase.assets.AssetsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideRemoteAssetsUseCase(assetsRepository: CoinsRepository): CoinsUseCase =
        AssetsUseCaseImpl(assetsRepository)
}