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
                    "ItemDatabase"
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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Update
    fun update(vararg items: Item)

    @Query("SELECT * FROM Item ORDER BY `index`")
    fun getAllItems(): LiveData<List<Item>>

    @Delete
    fun delete(item: Item)
}

@Entity
class Item(
        @PrimaryKey(autoGenerate = true) var id: Int? = null,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "index") var index: Int
    // TODO: list of items
) {
    override fun toString(): String
    {
        return name
    }
}

