package com.sd.testmo.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.sd.testmo.data.db.AppDatabase
import com.sd.testmo.data.di.AppModule
import com.sd.testmo.data.remote.ItemService
import kotlinx.coroutines.runBlocking

class SyncWork(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    override fun doWork(): Result {
        val appContext = applicationContext
        return try {
            runBlocking {
                AppModule.provideRetrofit(Gson()).create(ItemService::class.java).getAllRepo().let { AppDatabase.getDatabase(appContext).itemDao().insertAll(it.body()!!.items) }
            }
            Result.success()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }
    }
}