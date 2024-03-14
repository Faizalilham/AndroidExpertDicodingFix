package code.faizal.rakaminchallenge.core.data.datasource.remote.response

data class ListNewsResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : List<T>
)
