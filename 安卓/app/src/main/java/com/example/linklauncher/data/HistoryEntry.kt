package com.example.linklauncher.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
    val userAgent: String,
    val visitedAt: Long = System.currentTimeMillis()
)


