
import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("postLink")
    val postLink: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)