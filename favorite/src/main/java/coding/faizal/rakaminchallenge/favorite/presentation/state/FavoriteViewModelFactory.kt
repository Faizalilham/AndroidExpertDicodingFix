package coding.faizal.rakaminchallenge.favorite.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(private val newsUseCase: NewsUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(newsUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}