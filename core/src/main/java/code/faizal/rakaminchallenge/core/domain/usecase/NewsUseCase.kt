package code.faizal.rakaminchallenge.core.domain.usecase

import code.faizal.rakaminchallenge.core.data.Resource
import code.faizal.rakaminchallenge.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getAllHeadLine() : Flow<Resource<List<News>>>

    fun getAllNews() : Flow<Resource<List<News>>>

    fun favorites(): Flow<List<News>>

    fun setFavorite(news : News, isFavorite : Boolean)

}