package com.memo.k_room.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.memo.k_room.room.LibraryDatabase
import com.memo.k_room.room.dao.BookDao
import com.memo.k_room.room.entity.BookEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MemberInfoRepository(application: Application) {
    val TAG = "MemberInfoRepository"

    var libraryDatabase = LibraryDatabase.getInstance(application)

    var bookDao: BookDao = libraryDatabase?.bookDao()!!
    lateinit var LiveBooks: LiveData<List<BookEntity>>

    fun getAllBooks(memberId: String): LiveData<List<BookEntity>>{

        var getThread = Thread(Runnable {
            LiveBooks = bookDao.getAllBooks(memberId)
        })
        getThread.start()
        getThread.join()

        return LiveBooks
    }

    fun insertBook(bookEntity: BookEntity){
        CoroutineScope(IO).launch {
            bookDao.insert(bookEntity)
        }
    }

    fun deleteBook(bookEntity: BookEntity){
        CoroutineScope(IO).launch {
            bookDao.delete(bookEntity)
        }
    }

}