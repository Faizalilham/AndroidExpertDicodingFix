package code.faizal.rakaminchallenge.core.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.faizal.rakaminchallenge.core.databinding.ListNewsItemBinding
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.utils.Util
import com.bumptech.glide.Glide

class NewsAdapter(
    private val data : List<News>,
    private val listener : OnClick
)  : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding : ListNewsItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       return NewsViewHolder(ListNewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.apply {
            val x = data[position]
            tittle.text = x.description
            date.text =  Util.setDateTimeHourAgo(x.publishedAt)
            whoPost.text = x.author
            card.setOnClickListener {
                listener.onDetail(x)
            }
            if(x.urlToImage != null){
               Glide
                   .with(root)
                   .load(x.urlToImage)
                   .centerCrop()
                   .into(img)
            }
        }
    }

    override fun getItemCount(): Int =  data.size

    interface  OnClick{
        fun onDetail(news : News)
    }
}