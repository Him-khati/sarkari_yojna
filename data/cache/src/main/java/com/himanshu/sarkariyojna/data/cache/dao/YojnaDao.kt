package com.himanshu.sarkariyojna.data.cache.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojna
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojnaCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface YojnaDao {

    @Query("SELECT * FROM ${CachedYojna.TABLE_NAME}")
    fun getYojna(): Flow<List<CachedYojna>>

    @Upsert
    suspend fun insertOrUpdateYojna(
        cachedProfile: List<CachedYojna>
    )

    @Query("DELETE FROM ${CachedYojna.TABLE_NAME}")
    suspend fun deleteAllYojnas()
}