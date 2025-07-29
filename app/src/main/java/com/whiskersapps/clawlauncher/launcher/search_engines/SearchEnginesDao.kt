package com.whiskersapps.clawlauncher.launcher.search_engines

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.whiskersapps.clawlauncher.shared.model.SearchEngine
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchEnginesDao {
    @Query("SELECT * FROM searchengine")
    fun getAll(): Flow<List<SearchEngine>>

    @Query("UPDATE searchengine SET 'default' = :default WHERE id = :id")
    suspend fun setDefault(default: Boolean, id: Int)

    @Insert
    suspend fun insertMultiple(searchEngines: List<SearchEngine>)

    @Insert
    suspend fun insert(searchEngine: SearchEngine)

    @Update
    suspend fun update(searchEngine: SearchEngine)

    @Delete
    suspend fun delete(searchEngine: SearchEngine)
}