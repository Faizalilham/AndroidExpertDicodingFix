@file:Suppress("unused")

package coding.faizal.rakaminchallenge.di

import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import code.faizal.rakaminchallenge.core.domain.usecase.impl.NewsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    @Singleton
    abstract fun provideNewsMRepository(newsUseCase : NewsImpl): NewsUseCase
}