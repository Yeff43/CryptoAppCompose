package es.yeffry.cryptoappcompose.datasource.coin.local

import es.yeffry.cryptoappcompose.App
import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.local.database.AppLocalDatabase
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AppLocalDatabaseTest {

    @Mock
    lateinit var assetsDao: AssetsDao

    private lateinit var database: AppLocalDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val context = App.instance.baseContext
        database = AppLocalDatabase(context)
        assetsDao = database.assetsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `database should not be null`() {
        assertNotNull(database)
    }

    @Test
    fun `assetsDao should not be null`() {
        `when`(database.assetsDao()).thenReturn(assetsDao)
        assertNotNull(database.assetsDao())
    }
}