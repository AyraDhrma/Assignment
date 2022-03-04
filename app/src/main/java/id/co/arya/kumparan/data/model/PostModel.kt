package id.co.arya.kumparan.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostModel : ArrayList<PostModel.PostModelItem>(), Serializable {
    data class PostModelItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    ) : Serializable
}