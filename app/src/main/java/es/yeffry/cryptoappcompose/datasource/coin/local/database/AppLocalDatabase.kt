package es.yeffry.cryptoappcompose.datasource.coin.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.yeffry.cryptoappcompose.datasource.coin.local.dao.AssetsDao
import es.yeffry.cryptoappcompose.datasource.coin.local.dbo.CoinDbo

@Database(entities = [CoinDbo::class], version = 1, exportSchema = false)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun assetsDao(): AssetsDao

    companion object {
        private const val DATABASE_NAME = "crypto-database"

        @Volatile
        private var instance: AppLocalDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppLocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}