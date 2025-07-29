package com.whiskersapps.clawlauncher.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey
    val id: Int,
    val name: String,
)