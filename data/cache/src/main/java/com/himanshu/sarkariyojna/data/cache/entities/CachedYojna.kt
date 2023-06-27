package com.himanshu.sarkariyojna.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = CachedYojna.TABLE_NAME)
data class CachedYojna(

    @PrimaryKey()
    @ColumnInfo(name = COLUMN_ID)
    var id: String = "",

    @ColumnInfo(name = COLUMN_NAME)
    val name: String = "",

    @ColumnInfo(name = COLUMN_BOOK_MARKED)
    val bookmarked: Boolean = false,

    @ColumnInfo(name = COLUMN_LAST_OPENED_ON)
    val lastOpenedOn: LocalDateTime? = null,

    @ColumnInfo(name = COLUMN_UPDATED_ON)
    val updatedOn: LocalDateTime = LocalDateTime.now(),
) {

    companion object {
        @Ignore
        const val TABLE_NAME: String = "yojna_list"

        @Ignore
        const val COLUMN_ID: String = "id"

        @Ignore
        const val COLUMN_NAME: String = "name"

        @Ignore
        const val COLUMN_BOOK_MARKED: String = "bookmarked"

        @Ignore
        const val COLUMN_LAST_OPENED_ON: String = "last_opened_on"

        @Ignore
        const val COLUMN_UPDATED_ON: String = "updated_on"
    }


}