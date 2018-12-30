package com.example.alvinheng.hello

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import kotlinx.coroutines.*

class Repository(private val itemDao: ItemDao) {
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }
}