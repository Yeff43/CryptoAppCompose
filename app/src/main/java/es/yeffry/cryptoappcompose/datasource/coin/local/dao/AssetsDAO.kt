package es.yeffry.cryptoappcompose.datasource.coin.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeffry.cryptoapp.data.datasource.coin.local.dbo.CoinDBO

@Dao
interface AssetsDAO {
    @Query("SELECT * FROM assets ")
    fun getAllAssets(): List<CoinDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(assets: List<CoinDBO>)
}