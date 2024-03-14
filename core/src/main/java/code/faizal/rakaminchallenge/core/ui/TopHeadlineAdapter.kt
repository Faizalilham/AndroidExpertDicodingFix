package code.faizal.rakaminchallenge.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.faizal.rakaminchallenge.core.databinding.HeadlineNewsItemBinding
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.utils.Util
import com.bumptech.glide.Glide

class TopHeadlineAdapter
    (
    private val data : List<News>,
    private val listener : NewsAdapter.OnClick
            ): RecyclerView.Adapter<TopHeadlineAdapter.TopHeadlineViewHolder>() {

    inner class TopHeadlineViewHolder(val binding : HeadlineNewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineViewHolder {
        return TopHeadlineViewHolder(HeadlineNewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TopHeadlineViewHolder, position: Int) {
       holder.binding.apply {
           val x = data[position]
           date.text = Util.setDateTimeHourAgo(x.publishedAt)
           whoPost.text = x.author
           card.setOnClickListener {
               listener.onDetail(x)
           }
           if(x.urlToImage != null){
               Glide
                   .with(root)
                   .load(x.url)
                   .centerCrop()
                   .into(img)
           }
       }
    }

    override fun getItemCount(): Int = data.size

    interface  OnClick{
        fun onDetail(news : News)
    }
}