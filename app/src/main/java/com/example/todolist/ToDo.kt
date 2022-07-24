package com.example.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var task: String,
    var deadline: String
)
