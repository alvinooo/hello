package com.example.alvinheng.hello

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

public class ItemViewModel(application: Application): AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: Repository
    val allItems: LiveData<List<Item>>

    init {
        val itemDao = AppDatabase.getAppDataBase(application, scope).itemDao()
        repository = Repository(itemDao)
        allItems = repository.allItems
    }

    fun appendItem(item: Item) = scope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun updateItem(vararg item: Item) = scope.launch(Dispatchers.IO) {
        repository.update(*item)
    }

    fun deleteItem(item: Item) = scope.launch(Dispatchers.IO) { this
        repository.delete(item)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}