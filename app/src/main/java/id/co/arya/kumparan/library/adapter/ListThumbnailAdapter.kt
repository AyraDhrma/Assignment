package id.co.arya.kumparan.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.co.arya.kumparan.data.model.PhotosModel
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.databinding.RvItemsSubAlbumsBinding

class ListThumbnailAdapter(private val albumsId: Int, private val photosModel: PhotosModel) :
    RecyclerView.Adapter<ListThumbnailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsSubAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    lateinit var selectedPost: SelectedPost

    fun onSelectedPost(selectedPost: SelectedPost) {
        this.selectedPost = selectedPost
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemsSubAlbumsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                if (photosModel[position].albumId == albumsId) {
                    Glide.with(holder.itemView.context).load(photosModel[position].thumbnailUrl)
                        .transform(CenterInside(), RoundedCorners(24))
                        .into(thumbnailAlbums);
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return photosModel.size
    }

    interface SelectedPost {
        fun selectedPost(postDetailModel: PostDetailModel, position: Int)
    }

}
