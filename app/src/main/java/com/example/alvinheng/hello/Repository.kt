package com.example.alvinheng.hello

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class Repository(private val itemDao: ItemDao) {
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    @WorkerThread
    suspend fun update(vararg item: Item) {
        itemDao.update(*item)
    }

    @WorkerThread
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }
}