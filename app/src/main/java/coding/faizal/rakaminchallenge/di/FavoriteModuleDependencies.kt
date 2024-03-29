package coding.faizal.rakaminchallenge.di

import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun newsUseCase(): NewsUseCase
}