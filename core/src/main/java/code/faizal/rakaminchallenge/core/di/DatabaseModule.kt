package code.faizal.rakaminchallenge.core.di

import android.content.Context
import androidx.room.Room
import code.faizal.rakaminchallenge.core.data.datasource.local.room.NewsDao
import code.faizal.rakaminchallenge.core.data.datasource.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase = SQLiteDatabase.getBytes("adung".toCharArray())
    private val factory = SupportFactory(passphrase)


    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context : Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "db_room_news")
            .fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    @Singleton
    fun newsDao(movieDatabase : NewsDatabase): NewsDao {
        return movieDatabase.newsDao()
    }

}