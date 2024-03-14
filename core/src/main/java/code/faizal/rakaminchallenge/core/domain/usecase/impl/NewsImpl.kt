package code.faizal.rakaminchallenge.core.domain.usecase.impl

import code.faizal.rakaminchallenge.core.data.Resource
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.domain.repository.INewsRepository
import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsImpl @Inject constructor(
    private val iNewsRepository: INewsRepository
): NewsUseCase {

    override fun getAllHeadLine(): Flow<Resource<List<News>>> = iNewsRepository.getAllHeadLine()

    override fun getAllNews(): Flow<Resource<List<News>>> = iNewsRepository.getAllNews()

    override fun favorites(): Flow<List<News>> {
        return iNewsRepository.favorites()
    }

    override fun setFavorite(news: News, isFavorite: Boolean) {
        return iNewsRepository.setFavorite(news,isFavorite)
    }
}