package com.memo.k_room.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MemberEntity")
data class MemberEntity(
    @PrimaryKey
    val memberId: String,
    val name: String,
    val info: String,
    val city: String
) {

}