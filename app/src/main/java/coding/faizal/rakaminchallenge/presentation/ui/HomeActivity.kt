package coding.faizal.rakaminchallenge.presentation.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import code.faizal.rakaminchallenge.core.data.Resource
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.ui.NewsAdapter
import code.faizal.rakaminchallenge.core.ui.TopHeadlineAdapter
import coding.faizal.rakaminchallenge.databinding.ActivityHomeBinding
import coding.faizal.rakaminchallenge.presentation.state.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    private var loading = true
    var pastVisibleItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    private lateinit var topHeadlineAdapter : TopHeadlineAdapter
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collectData()
        moveFavorite()

    }

    private fun collectData(){
        homeViewModel.getAllNews().observe(this){
            newsAction(it){ news ->
                allNews(news)

            }
        }

        homeViewModel.getAllHeadline().observe(this){
            newsAction(it){ news ->
                headline(news)
            }
        }
    }

    private fun newsAction(resource : Resource<List<News>>,action : (List<News>?) -> Unit){
        when(resource){
            is Resource.Success -> {
                action(resource.data)
            }
            is Resource.Error -> {
                Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
            }

            is Resource.Loading -> {

            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun headline(data : List<News>?){
        topHeadlineAdapter = TopHeadlineAdapter(data ?: emptyList(),object : NewsAdapter.OnClick{
            override fun onDetail(news: News) {
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java).also{
                    it.putExtra(EXTRA_DATA,news)
                })
            }
        })

        val mLayoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
        binding.rvHeadline.apply {
            adapter = topHeadlineAdapter
            layoutManager = mLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        visibleItemCount = mLayoutManager.childCount
                        totalItemCount = mLayoutManager.itemCount
                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()

                        if (loading) {
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                loading = false

                                loading = true
                            }
                        }
                    }
                }
            })

        }
        topHeadlineAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun allNews(data : List<News>?){
        newsAdapter = NewsAdapter(data ?: emptyList(),object : NewsAdapter.OnClick{
            override fun onDetail(news: News) {
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java).also{
                    it.putExtra(EXTRA_DATA,news)
                })
            }
        })
        val mLayoutManager = LinearLayoutManager(this@HomeActivity)
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = mLayoutManager

        }
        newsAdapter.notifyDataSetChanged()
    }

    private fun moveFavorite(){
        binding.favorite.setOnClickListener {
            val uri = Uri.parse("androidfavorite://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
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