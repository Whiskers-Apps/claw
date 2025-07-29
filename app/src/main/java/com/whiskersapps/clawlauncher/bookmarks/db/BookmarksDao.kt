package com.whiskersapps.clawlauncher.bookmarks.db

import androidx.room.Dao
import androidx.room.Query
import com.whiskersapps.clawlauncher.shared.model.Bookmark

@Dao
interface BookmarksDao {

    @Query("SELECT * FROM bookmark")
    fun getAll(): List<Bookmark>
}