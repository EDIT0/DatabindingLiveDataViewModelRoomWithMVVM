package com.memo.k_room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.memo.k_room.room.LibraryDatabase
import com.memo.k_room.room.dao.MemberDao
import com.memo.k_room.room.entity.MemberEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainRepository(application: Application) {

    var libraryDatabase = LibraryDatabase.getInstance(application)

    var memberDao: MemberDao = libraryDatabase?.memberDao()!!
    var LiveMembers: LiveData<List<MemberEntity>> = memberDao?.getAllMembers()

    fun insertMember(memberEntity: MemberEntity){
        CoroutineScope(IO).launch {
            memberDao.insert(memberEntity)
        }
    }

    fun deleteMember(memberEntity: MemberEntity){
        CoroutineScope(IO).launch {
            memberDao.delete(memberEntity)
        }
    }

}