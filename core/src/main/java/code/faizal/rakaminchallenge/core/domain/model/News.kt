package code.faizal.rakaminchallenge.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    var id : Int,

    val author : String?,

    val title :String,

    val description : String?,

    val url : String,

    val urlToImage :String?,

    val publishedAt : String,

    val content : String?,

    val isFavorite : Boolean
):Parcelable



