package es.yeffry.cryptoappcompose.datasource.repository

import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.local.AssetsLocalDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo
import es.yeffry.cryptoappcompose.datasource.coin.mockDataSource.AssetsMockDataSourceImpl
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto
import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository
import es.yeffry.cryptoappcompose.domain.repository.MapperToDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AssetsRepositoryImplTest {

    @Mock
    lateinit var mockDataSource: AssetsDataSourceInterface

    @Mock
    lateinit var remoteDataSource: AssetsDataSourceInterface

    @Mock
    lateinit var localDataSource: AssetsLocalDataSourceInterface

    @Mock
    private lateinit var mapperToDomain: MapperToDomain<AssetDto, Coin>

    private lateinit var assetsRepository: CoinsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockDataSource = AssetsMockDataSourceImpl()
        assetsRepository = AssetsRepositoryImpl(remoteDataSource, localDataSource, mockDataSource)
    }

    @Test
    fun `getCoins should return non-null list from remote`() {
        runBlocking {
            val localResponse = listOf<CoinDbo>()
            val remoteResponse = listOf<AssetDto>()

            `when`(localDataSource.getAssets()).thenReturn(localResponse)
            `when`(remoteDataSource.getAssets()).thenReturn(remoteResponse)

            val result = assetsRepository.getCoins()

            with(mapperToDomain) {
                assertEquals(remoteResponse.map { it.toDomain() }, result)
            }
            verify(remoteDataSource).getAssets()
            verify(localDataSource).saveAssets(anyList())
        }
    }

    @Test
    fun `getCoins should return non-null list from local`() {
        runBlocking {
            val expected = listOf(
                CoinDbo(
                    "bitcoin",
                    "BTC",
                    "Bitcoin",
                    "6929.8217756835584756"
                )
            )

            `when`(localDataSource.getAssets()).thenReturn(expected)
            val result = localDataSource.getAssets()
            assertEquals(expected, result)
            verify(localDataSource).getAssets()
        }
    }
}