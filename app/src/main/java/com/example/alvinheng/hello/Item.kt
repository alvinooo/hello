package com.example.alvinheng.hello

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.content.Context
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}

@Dao
interface ItemDao {
    @Insert
    fun insert(vararg items: Item)

    @Query("SELECT * FROM Item")
    fun getAllItems(): LiveData<List<Item>>

    // TODO: single get?

    @Update
    fun update(vararg items: Item)

    @Delete
    fun delete(item: Item)
}

@Entity
class Item(
    @PrimaryKey @ColumnInfo(name = "name") var name: String
    // TODO: list of items
) {
    override fun toString(): String {
        return name
    }
}

