package coding.faizal.rakaminchallenge.favorite.presentation.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase

class FavoriteViewModel(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    private val _favorites: MutableLiveData<List<News>> = MutableLiveData()
    val getAllFavorite: LiveData<List<News>> = _favorites

    init {
        getAllFavorite()
    }

    private fun getAllFavorite() {
        newsUseCase.favorites().asLiveData().observeForever {
            _favorites.postValue(it)
        }
    }

}