package coding.faizal.rakaminchallenge.favorite.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.ui.NewsAdapter
import coding.faizal.rakaminchallenge.di.FavoriteModuleDependencies
import coding.faizal.rakaminchallenge.favorite.databinding.ActivityFavoriteBinding
import coding.faizal.rakaminchallenge.favorite.di.DaggerFavoriteComponent
import coding.faizal.rakaminchallenge.favorite.presentation.state.FavoriteViewModel
import coding.faizal.rakaminchallenge.favorite.presentation.state.FavoriteViewModelFactory
import coding.faizal.rakaminchallenge.presentation.ui.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter : NewsAdapter

    @Inject
    lateinit var favoriteViewModelFactory : FavoriteViewModelFactory

    private val favoriteViewModel by viewModels<FavoriteViewModel>{
        favoriteViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerFavoriteComponent.builder().context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(applicationContext,
                    FavoriteModuleDependencies::class.java))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showMyFavorite()

    }

    private fun setRecycler(data : List<News>?){
        newsAdapter = NewsAdapter(data ?: emptyList(),object : NewsAdapter.OnClick{
            override fun onDetail(news: News) {
                startActivity(Intent(this@FavoriteActivity, DetailActivity::class.java).also{
                    it.putExtra(EXTRA_DATA,news)
                })
            }
        })


        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun showMyFavorite(){
        favoriteViewModel.getAllFavorite.observe(this){
            setRecycler(it)
            Log.d("FAVORITE","$it")
        }
    }

    companion object{
        const val EXTRA_DATA = "news"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}