package code.faizal.rakaminchallenge.core.di

import code.faizal.rakaminchallenge.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNewsRepository(newsRepository: code.faizal.rakaminchallenge.core.data.NewsDataSource): INewsRepository
}