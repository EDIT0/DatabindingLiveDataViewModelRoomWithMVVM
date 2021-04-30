package com.memo.k_room.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.memo.k_room.room.entity.MemberEntity

@Dao
interface MemberDao {

    @Insert
    fun insert(memberEntity: MemberEntity)

    @Update
    fun update(memberEntity: MemberEntity)

    @Delete
    fun delete(memberEntity: MemberEntity)

    @Query("DELETE FROM MemberEntity")
    fun deleteAllMembers()

    @Query("SELECT * FROM MemberEntity")
    fun getAllMembers(): LiveData<List<MemberEntity>>

}