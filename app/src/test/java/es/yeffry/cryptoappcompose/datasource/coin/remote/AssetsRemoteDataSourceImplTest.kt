package es.yeffry.cryptoappcompose.datasource.coin.remote

import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.remote.api.AssetsApi
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto
import es.yeffry.cryptoappcompose.network.NetworkManager
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


class AssetsRemoteDataSourceImplTest {

    @Mock
    lateinit var networkManager: NetworkManager

    @Mock
    lateinit var mockedRemoteAssetsService: AssetsApi

    private lateinit var assetsRemoteDataSource: AssetsDataSourceInterface

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        assetsRemoteDataSource = AssetsRemoteDataSourceImpl(
            network = networkManager,
            assetsRemoteApi = mockedRemoteAssetsService
        )
    }

    @Test
    fun `getAssets Should Return List Of Coins When Response Give A 200 Success`() {
        runBlocking {
            `when`(mockedRemoteAssetsService.getAssets()).thenReturn(Response.success(listOf()))
            val expected = listOf<AssetDto>()
            val result = assetsRemoteDataSource.getAssets()
            assertEquals(expected, result)
        }
    }

    @Test
    fun `getAssets should return empty list when api return null`() =
        runBlocking {
            `when`(mockedRemoteAssetsService.getAssets()).thenReturn(Response.success(null))
            val expected = listOf<AssetDto>()
            val result = assetsRemoteDataSource.getAssets()
            assertEquals(expected, result)
        }

    @Test
    fun `getAssets should return empty list when api return code between 400 and 417`() {
        runBlocking {
            `when`(mockedRemoteAssetsService.getAssets()).thenReturn(
                Response.error(
                    404,
                    ResponseBody.create(null, "")
                )
            )
            val expected = listOf<AssetDto>()
            val result = assetsRemoteDataSource.getAssets()
            assertEquals(expected, result)
        }
    }

    @Test
    fun `getAssets should return emptyList when api response code is from 500 to 505`() {
        runBlocking {
            `when`(mockedRemoteAssetsService.getAssets()).thenReturn(
                Response.error(
                    503,
                    ResponseBody.create(null, "")
                )
            )
            val expected = listOf<AssetDto>()
            val result = assetsRemoteDataSource.getAssets()
            assertEquals(expected, result)
        }
    }

    @Test
    fun `getAssets in datasource should return listOf AssetDto`() {
        runBlocking {
            val expected = listOf<AssetDto>()
            val result = assetsRemoteDataSource.getAssets()
            `when`(assetsRemoteDataSource.getAssets()).thenReturn(expected)
            assertEquals(expected, result)
        }
    }
}