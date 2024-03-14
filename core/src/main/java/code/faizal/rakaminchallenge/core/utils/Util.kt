package code.faizal.rakaminchallenge.core.utils


import android.annotation.SuppressLint
import code.faizal.rakaminchallenge.core.data.datasource.local.entity.NewsEntity
import code.faizal.rakaminchallenge.core.data.datasource.remote.response.NewsResponse
import code.faizal.rakaminchallenge.core.domain.model.News
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun mapFromResponseToEntities(input : List<NewsResponse>):List<NewsEntity>{
        val list = mutableListOf<NewsEntity>()
        input.map{ new ->
            new.apply {
                val news = NewsEntity(

                    author = new.author ?: "",
                    title = new.title,
                    description = new.description ?: "",
                    url = new.url,
                    urlToImage = new.urlToImage ?: "",
                    publishedAt =  new.publishedAt,
                    content = new.content ?: "",
                )
                list.add(news)
            }
        }
        return list
    }

    fun mapFromEntityToDomain(input : List<NewsEntity>):List<News>{
        val list = mutableListOf<News>()
        input.map{ new ->
            new.apply {
                val news = News(
                    new.id,
                    new.author,
                    new.title,
                    new.description,
                    new.url,
                    new.urlToImage,
                    new.publishedAt,
                    new.content,
                    new.isFavorite
                )
                list.add(news)
            }
        }
        return list
    }

    fun mapFromDomainToEntity(new : News,isFavorite : Boolean):NewsEntity{
        return NewsEntity(
            new.id,
            new.author ?: "",
            new.title,
            new.description ?: "",
            new.url,
            new.urlToImage ?: "",
            new.publishedAt,
            new.content ?: "",
            isFavorite
        )
    }

    fun setDateTimeHourAgo(dateTime: String?): String? {
        val prettyTime = PrettyTime(Locale.getDefault())
        var isTime: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = dateTime?.let { simpleDateFormat.parse(it) }
            isTime = prettyTime.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isTime
    }

    @SuppressLint("SimpleDateFormat")
    fun setDateFormat(dateNews: String?): String? {
        val isDate: String?
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy - HH:mm:ss", Locale(getCountry()))
        isDate = try {
            val date = dateNews?.let { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(it) }
            date?.let { dateFormat.format(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            dateNews
        }
        return isDate
    }

    fun getCountry(): String {
        val locale = Locale.getDefault()
        val strCountry = locale.country
        return strCountry.lowercase(Locale.ROOT)
    }
}