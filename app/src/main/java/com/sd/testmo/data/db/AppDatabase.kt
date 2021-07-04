package com.sd.testmo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sd.testmo.data.entities.Item
import com.sd.testmo.utils.NestedDataTypeConverter

@Database(entities = [Item::class], version = 6, exportSchema = false)
@TypeConverters(NestedDataTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "items")
                .fallbackToDestructiveMigration()
                .build()
    }

}