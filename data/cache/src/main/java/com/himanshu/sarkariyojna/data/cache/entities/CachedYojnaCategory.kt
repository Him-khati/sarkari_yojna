package com.himanshu.sarkariyojna.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = CachedYojnaCategory.TABLE_NAME)
data class CachedYojnaCategory(

    @PrimaryKey()
    @ColumnInfo(name = COLUMN_ID)
    var id: String = "",

    @ColumnInfo(name = COLUMN_NAME)
    val name: String = "",

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String = "",

    @ColumnInfo(name = COLUMN_UPDATED_ON)
    val updatedOn: LocalDateTime = LocalDateTime.now(),
    ) {

    companion object {
        @Ignore
        const val TABLE_NAME: String = "yojna_categories"

        @Ignore
        const val COLUMN_ID: String = "id"

        @Ignore
        const val COLUMN_NAME: String = "name"

        @Ignore
        const val COLUMN_DESCRIPTION: String = "description"

        @Ignore
        const val COLUMN_UPDATED_ON: String = "updated_on"
    }


}