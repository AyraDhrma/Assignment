package id.co.arya.kumparan.library.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.co.arya.kumparan.R
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.databinding.RvItemsSubAlbumsBinding
import id.co.arya.kumparan.utils.StringUtils


class ListThumbnailAdapter(private val listPhotos: ArrayList<LocalPhotosModel>) :
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
                // Log.d(StringUtils.NAME, "onBindViewHolder: ${listPhotos[position].thumbnailUrl}.png")
                Glide.with(holder.itemView.context).load("${listPhotos[position].thumbnailUrl}.png")
                    .placeholder(R.drawable.thumbnail)
                    .transform(CenterInside(), RoundedCorners(24))
                    .into(thumbnailAlbums);

            }
        }
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    interface SelectedPost {
        fun selectedPost(postDetailModel: PostDetailModel, position: Int)
    }

}
