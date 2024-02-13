package es.yeffry.cryptoappcompose.datasource.coin.remote.api

import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetsDTO
import retrofit2.Response
import retrofit2.http.GET

interface AssetsApi {

    @GET("v2/assets")
    suspend fun getAssets(): Response<AssetsDTO>

}