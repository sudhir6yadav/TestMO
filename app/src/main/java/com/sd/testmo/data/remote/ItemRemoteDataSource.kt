package com.sd.testmo.data.remote

import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(
    private val itemService: ItemService
): BaseDataSource() {
    suspend fun getItems() = getResult { itemService.getAllRepo() }
}