package code.faizal.rakaminchallenge.core.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "author")
    val author : String,

    @ColumnInfo(name = "tittle")
    val title :String,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "url")
    val url : String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage :String,

    @ColumnInfo(name = "publish")
    val publishedAt : String,

    @ColumnInfo(name = "content")
    val content : String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false
)

