package es.yeffry.cryptoappcompose.di

import android.app.Application
import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.local.database.AppLocalDatabase
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coincap.io/")
            .client(httpClient)
            .build()

    @Provides
    @Singleton
    fun provideRemoteAssetsApi(retrofit: Retrofit): AssetsApi =
        retrofit.create(AssetsApi::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppLocalDatabase =
       AppLocalDatabase.invoke(application)


    @Provides
    @Singleton
    fun provideAssetsDao(
        appLocalDatabase: AppLocalDatabase
    ): AssetsDao = appLocalDatabase.assetsDao()
}