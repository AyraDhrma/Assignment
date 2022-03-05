package id.co.arya.kumparan.data.model


import com.google.gson.annotations.SerializedName

class AlbumsModel : ArrayList<AlbumsModel.AlbumsModelItem>(){
    data class AlbumsModelItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}