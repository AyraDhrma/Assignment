package id.co.arya.kumparan.data.model


import com.google.gson.annotations.SerializedName

class PhotosModel : ArrayList<PhotosModel.PhotosModelItem>() {
    data class PhotosModelItem(
        @SerializedName("albumId")
        val albumId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )
}