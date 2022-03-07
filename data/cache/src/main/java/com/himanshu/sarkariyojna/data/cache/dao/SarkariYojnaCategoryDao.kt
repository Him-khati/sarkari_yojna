package com.himanshu.sarkariyojna.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojnaCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface SarkariYojnaCategoryDao {

    @Query("SELECT * FROM ${CachedYojnaCategory.TABLE_NAME}")
    fun getYojnaCategories(): Flow<List<CachedYojnaCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cachedProfile: List<CachedYojnaCategory>)
}