package com.himanshu.sarkariyojna.data.cache

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.himanshu.sarkariyojna.data.cache.dao.SarkariYojnaCategoryDao
import com.himanshu.sarkariyojna.data.cache.dao.YojnaDao
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojna
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojnaCategory
import com.himanshu.sarkariyojna.data.cache.typeConverters.LocalDateTimeTypeConverter

@Database(
    entities = [
        CachedYojnaCategory::class,
        CachedYojna::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    LocalDateTimeTypeConverter::class
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "sarkari_yojna.db"

        fun createDatabase(
            applicationContext: Context
        ): LocalDatabase {
            return Room.databaseBuilder(
                applicationContext,
                LocalDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

    //Room Daos
    abstract fun getSarkariYojnaCategoriesDao(): SarkariYojnaCategoryDao

    abstract fun getYojnaDao(): YojnaDao
}