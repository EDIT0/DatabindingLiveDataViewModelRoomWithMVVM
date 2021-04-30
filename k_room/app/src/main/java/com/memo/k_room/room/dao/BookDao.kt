package com.memo.k_room.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.memo.k_room.room.entity.BookEntity

@Dao
interface BookDao {

    @Insert
    fun insert(bookEntity: BookEntity)

    /*@Update
    fun update(bookEntity: BookEntity)*/

    @Query("UPDATE BookEntity SET bookName = :newBookName WHERE bookOrder = :bookOrder")
    fun updateBook(newBookName: String, bookOrder: Int)

    @Delete
    fun delete(bookEntity: BookEntity)

    @Query("DELETE FROM BookEntity")
    fun deleteAllBooks()

    @Query("SELECT * FROM BookEntity WHERE memberIdInBook = :memberIdInBook")
    fun getAllBooks(memberIdInBook: String): LiveData<List<BookEntity>>
}