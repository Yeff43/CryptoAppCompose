package es.yeffry.cryptoappcompose.datasource.coin.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo

@Dao
interface AssetsDao {
    @Query("SELECT * FROM assets ")
    fun getAssets(): List<CoinDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCoins(assets: List<CoinDbo>)
}