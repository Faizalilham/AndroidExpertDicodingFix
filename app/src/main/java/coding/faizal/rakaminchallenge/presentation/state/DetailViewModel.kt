package coding.faizal.rakaminchallenge.presentation.state

import androidx.lifecycle.ViewModel
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val  newsUseCase: NewsUseCase
): ViewModel() {

    fun setFavorite(news : News, isFavorite: Boolean){
        newsUseCase.setFavorite(news,isFavorite)
    }

}