package id.co.arya.kumparan.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.co.arya.kumparan.data.model.*
import id.co.arya.kumparan.databinding.RvItemsMainAlbumsBinding

class ListAlbumsAdapter(
    private val albumsModel: AlbumsModel
) :
    RecyclerView.Adapter<ListAlbumsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsMainAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    lateinit var listPhotosModel: PhotosModel

    fun setListPhotos(photosModel: PhotosModel) {
        this.listPhotosModel = photosModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemsMainAlbumsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                titleAlbumsItem.text = albumsModel[position].title
//                val adapter = ListThumbnailAdapter(albumsModel[position].id, photosModel)
//                listThumbnailRecyclerView.hasFixedSize()
//                listThumbnailRecyclerView.layoutManager = LinearLayoutManager(
//                    holder.itemView.context,
//                    LinearLayoutManager.HORIZONTAL,
//                    false
//                )
//                listThumbnailRecyclerView.adapter = adapter
            }
        }
    }

    override fun getItemCount(): Int {
        return albumsModel.size
    }

}
