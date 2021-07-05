package com.sd.testmo.data.entities

import androidx.room.*


@Entity(tableName = "items")
data class Item(

    @PrimaryKey
    var id: Int = 0,

    var name: String? = null,
    var full_name: String? = null,

    var description: String? = null,
    var stargazers_count: Int = 0,
    var watchers_count: Int = 0,
    var language: String? = null,
    var forks_count: Int = 0,
    var watchers: Int = 0,
    @Embedded
    var owner: Owner? = null


)

public data class Owner (
        val avatar_url : String,
)