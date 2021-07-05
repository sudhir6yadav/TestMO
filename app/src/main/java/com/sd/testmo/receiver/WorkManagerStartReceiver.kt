package com.sd.testmo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sd.testmo.utils.SyncWork
import java.util.concurrent.TimeUnit


class WorkManagerStartReceiver : BroadcastReceiver() {
    var mWorkManager: WorkManager? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        val myWorkBuilder = PeriodicWorkRequest.Builder(
            SyncWork::class.java,
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
        val myWork = myWorkBuilder.build()
        mWorkManager = WorkManager.getInstance(context!!)
        mWorkManager!!.enqueue(myWork)
    }
}