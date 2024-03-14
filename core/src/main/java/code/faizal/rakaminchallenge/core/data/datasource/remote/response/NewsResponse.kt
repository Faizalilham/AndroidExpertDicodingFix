package code.faizal.rakaminchallenge.core.data.datasource.remote.response



data class NewsResponse(
    val source: SourceResponse?,
    val author : String?,
    val title :String,
    val description : String?,
    val url : String,
    val urlToImage :String?,
    val publishedAt : String,
    val content : String?,
)
