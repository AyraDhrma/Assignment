package id.co.arya.kumparan.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.arya.kumparan.R
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.databinding.RvItemsSubAlbumsBinding


class ListThumbnailAdapter(private val listPhotos: ArrayList<LocalPhotosModel>) :
    RecyclerView.Adapter<ListThumbnailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsSubAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    lateinit var selectedPhotos: SelectedPhotos

    fun onSelectedPost(selectedPhotos: SelectedPhotos) {
        this.selectedPhotos = selectedPhotos
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
                Picasso.get()
                    .load(listPhotos[position].thumbnailUrl)
                    .placeholder(R.drawable.thumbnail)
                    .error(R.drawable.thumbnail)
                    .into(thumbnailAlbums)
            }
            itemView.setOnClickListener {
                selectedPhotos.selectedPhotos(
                    LocalPhotosModel(
                        listPhotos[position].id,
                        listPhotos[position].albumId,
                        listPhotos[position].thumbnailUrl,
                        listPhotos[position].title,
                        listPhotos[position].url
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    interface SelectedPhotos {
        fun selectedPhotos(photos: LocalPhotosModel)
    }

}
