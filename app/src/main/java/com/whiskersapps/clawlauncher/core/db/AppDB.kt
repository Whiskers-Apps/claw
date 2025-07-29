package com.whiskersapps.clawlauncher.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.whiskersapps.clawlauncher.bookmarks.db.BookmarksDao
import com.whiskersapps.clawlauncher.launcher.search_engines.SearchEnginesDao
import com.whiskersapps.clawlauncher.shared.model.Bookmark
import com.whiskersapps.clawlauncher.shared.model.Group
import com.whiskersapps.clawlauncher.shared.model.SearchEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SearchEngine::class, Bookmark::class, Group::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun bookmarksDao(): BookmarksDao
    abstract fun searchEnginesDao(): SearchEnginesDao
}

fun getDB(context: Context): AppDB {
    return Room.databaseBuilder(context, AppDB::class.java, "db")
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val db = Room.databaseBuilder(context, AppDB::class.java, "db").build()
                    db.searchEnginesDao().insertMultiple(
                        listOf(
                            SearchEngine(
                                name = "Google",
                                query = "https://www.google.com/search?q=%s"
                            ),
                            SearchEngine(
                                name = "DuckDuckGo",
                                query = "https://duckduckgo.com/?q=%s"
                            ),
                            SearchEngine(
                                name = "Brave",
                                query = "https://search.brave.com/search?q=%s"
                            ),
                            SearchEngine(
                                name = "StartPage",
                                query = "https://www.startpage.com/sp/search?query=%s"
                            ),
                            SearchEngine(name = "Qwant", query = "https://www.qwant.com/?q=%s"),
                            SearchEngine(
                                name = "Ecosia",
                                query = "https://www.ecosia.org/search?q=%s",
                            ),
                        )
                    )
                }
            }
        }).build()
}