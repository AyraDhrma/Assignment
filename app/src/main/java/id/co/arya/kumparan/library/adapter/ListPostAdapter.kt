package id.co.arya.kumparan.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.databinding.RvItemsPostBinding

class ListPostAdapter(private val listPostModel: PostModel) :
    RecyclerView.Adapter<ListPostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemsPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            titlePostItem.text = listPostModel[position].title
            bodyPostItem.text = listPostModel[position].body
            usernamePostItem.text = listPostModel[position].userId.toString()
            userCompanyPostItem.text = listPostModel[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return listPostModel.size
    }

}
