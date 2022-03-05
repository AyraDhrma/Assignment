package id.co.arya.kumparan.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostCommentsModel : ArrayList<PostCommentsModel.PostCommentsModelItem>(), Serializable {
    data class PostCommentsModelItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("postId")
        val postId: Int
    ) : Serializable
}