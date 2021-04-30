package com.memo.k_room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.memo.k_room.room.LibraryDatabase
import com.memo.k_room.room.dao.BookDao
import com.memo.k_room.room.entity.BookEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviseBookNameRepository(application: Application) {

    var libraryDatabase = LibraryDatabase.getInstance(application)

    var bookDao: BookDao = libraryDatabase?.bookDao()!!

    fun updateBook(newBookName: String, bookOrder: Int){
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.updateBook(newBookName, bookOrder)
        }
    }

}