package coding.faizal.rakaminchallenge.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import code.faizal.rakaminchallenge.core.domain.model.News
import coding.faizal.rakaminchallenge.R
import coding.faizal.rakaminchallenge.databinding.ActivityDetailBinding
import coding.faizal.rakaminchallenge.presentation.state.DetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        val news = intent.getParcelableExtra<News>(EXTRA_DATA)
        showDetailNews(news)
    }

    private fun showDetailNews(news: News?) {
        if(news != null){
            binding.apply {
                newsTittle.text = news.title
                newsContent.text = news.content
                newsDescription.text = news.description
                newsAuthor.text = news.author
                newsPublish.text = news.publishedAt
                Glide.with(root).load(news.urlToImage).into(newsImage)

                var isFavorite = news.isFavorite
                setStatusFavorite(isFavorite)
                fabFavorite.setOnClickListener {
                    isFavorite = !isFavorite

                    detailViewModel.setFavorite(news,isFavorite)
                    setStatusFavorite(isFavorite)

                    if(isFavorite) Toast.makeText(this@DetailActivity, "Success add to favorite", Toast.LENGTH_SHORT).show() else Toast.makeText(this@DetailActivity, "Success remove to favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_filled))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_outlined))
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