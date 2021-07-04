package com.sd.testmo.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sd.testmo.data.repository.ItemRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log


class SyncWork(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    override fun doWork(): Result {
        val appContext = applicationContext
        return try {
            Log.d("workmnagerBack", "doWork: worker thread")

           Toast.makeText(appContext,"after 15 min",Toast.LENGTH_LONG).show()
           //val items=  repository.getItems()
         //   Log.d("workmnagerBackitems", "doWork: "+items)

            Result.success()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }
    }
}