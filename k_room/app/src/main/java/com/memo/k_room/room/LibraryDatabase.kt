package com.memo.k_room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.memo.k_room.room.dao.BookDao
import com.memo.k_room.room.dao.MemberDao
import com.memo.k_room.room.entity.BookEntity
import com.memo.k_room.room.entity.MemberEntity

@Database(entities = [MemberEntity::class, BookEntity::class], version = 1)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun bookDao(): BookDao

    companion object{
        var instance: LibraryDatabase? = null

        @Synchronized
        open fun getInstance(context: Context): LibraryDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase::class.java, "Library")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

    }
}