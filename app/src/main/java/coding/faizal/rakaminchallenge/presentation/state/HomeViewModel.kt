package coding.faizal.rakaminchallenge.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import code.faizal.rakaminchallenge.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCase: NewsUseCase
): ViewModel() {

//    private val _headlineData = MutableStateFlow<List<News>>(emptyList())
//    val headlineData: StateFlow<List<News>> get() = _headlineData
//
//
//    fun getAllHeadlineData() {
//        viewModelScope.launch {
//            newsUseCase.getAllHeadLine().collect { resource ->
//                _headlineData.value = resource.data!!
//            }
//        }
//    }

    fun getAllNews() = newsUseCase.getAllNews().asLiveData()

    fun getAllHeadline() = newsUseCase.getAllHeadLine().asLiveData()
}