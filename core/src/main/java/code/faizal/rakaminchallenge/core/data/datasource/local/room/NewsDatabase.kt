package code.faizal.rakaminchallenge.core.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import code.faizal.rakaminchallenge.core.data.datasource.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 3,
    exportSchema = false
)
abstract  class NewsDatabase : RoomDatabase() {

    abstract fun newsDao() : NewsDao
}