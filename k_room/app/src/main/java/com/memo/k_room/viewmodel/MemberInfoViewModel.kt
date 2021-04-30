package com.memo.k_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.memo.k_room.repository.MemberInfoRepository
import com.memo.k_room.room.entity.BookEntity

class MemberInfoViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "MemberInfoViewModel"

    var memberId = String()
    var name = String()
    var info = String()
    var city = String()

    var memberInfoRepository = MemberInfoRepository(application)
    val LiveBooks: LiveData<List<BookEntity>>
        get() = memberInfoRepository.getAllBooks(memberId)
    var Books = ArrayList<BookEntity>()

    var bookName = String()

    fun insertBook(){
        memberInfoRepository.insertBook(BookEntity(bookName, memberId))
    }

    fun deleteBook(bookEntity : BookEntity){
        memberInfoRepository.deleteBook(bookEntity)
    }

}