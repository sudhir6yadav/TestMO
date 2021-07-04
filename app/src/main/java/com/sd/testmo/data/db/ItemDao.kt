package com.sd.testmo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sd.testmo.data.entities.Item


@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getAllItems() : LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItems(id: Int): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

}
