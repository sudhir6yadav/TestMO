package com.sd.testmo.data.repository

import com.sd.testmo.data.db.ItemDao
import com.sd.testmo.data.remote.ItemRemoteDataSource
import com.sd.testmo.utils.performGetOperation
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemDao
) {

    fun getItems() = performGetOperation(
        databaseQuery = { localDataSource.getAllItems() },
        networkCall = { remoteDataSource.getItems() },
        saveCallResult = { localDataSource.insertAll(it.items) }
    )

    fun getItems(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getItems(id) },
    )
}