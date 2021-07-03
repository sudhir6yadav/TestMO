package com.sd.testmo.data.entities


data class Repo(
    var total_count: Int = 0,
    var incomplete_results: Boolean = false,
    var items: List<Item>
)
