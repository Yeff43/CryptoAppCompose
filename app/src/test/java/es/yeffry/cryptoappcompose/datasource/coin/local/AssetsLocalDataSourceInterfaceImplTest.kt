package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class AssetsLocalDataSourceInterfaceImplTest {

    @Mock
    lateinit var assetsLocalApi: AssetsDao

    @Mock
    lateinit var assetsLocalDataSourceInterface: AssetsLocalDataSourceInterface

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        assetsLocalDataSourceInterface = AssetsLocalDataSourceImpl(assetsLocalApi)
    }

    @Test
    fun `getAssets from local should return listOf CoinDbo`() {
        runBlocking {
            `when`(assetsLocalApi.getAssets()).thenReturn(listOf())
            val expected = listOf<CoinDbo>()
            val result = assetsLocalApi.getAssets()
            assertEquals(expected, result)
        }
    }

    @Test
    fun `getAssets from local is in use`() {
        runBlocking {
            assetsLocalDataSourceInterface.getAssets()
            verify(assetsLocalApi).getAssets()
        }
    }

    @Test
    fun `saveAssets should storage the coins on the local db`() {
        runBlocking {
            val expected = listOf<CoinDbo>()
            assetsLocalDataSourceInterface.saveAssets(expected)
            verify(assetsLocalApi).saveCoins(expected)
        }
    }
}
