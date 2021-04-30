package com.memo.k_room.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "BookEntity",
    foreignKeys = [ForeignKey(entity = MemberEntity::class, parentColumns = ["memberId"], childColumns = ["memberIdInBook"], onDelete = ForeignKey.CASCADE)]
)
data class BookEntity(
    val bookName: String,
    val memberIdInBook: String
) {
    @PrimaryKey(autoGenerate = true)
    var bookOrder: Int = 0
}