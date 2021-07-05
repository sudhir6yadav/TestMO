package com.sd.testmo.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.sd.testmo.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            Log.d("performGetOperation", "performGetOperation: "+responseStatus.data)
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> performGetOperation(databaseQuery: () -> LiveData<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
    }