package id.co.arya.kumparan.library.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.arya.kumparan.data.model.PostCommentsModel
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.databinding.RvItemsCommentsBinding
import id.co.arya.kumparan.databinding.RvItemsPostBinding
import id.co.arya.kumparan.ui.post.DetailPostActivity

class ListPostCommentsAdapter(private val data: PostCommentsModel) :
    RecyclerView.Adapter<ListPostCommentsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsCommentsBinding) :
        RecyclerView.ViewHolder(binding.root)

    lateinit var selectedPost: SelectedPost

    fun onSelectedPost(selectedPost: SelectedPost) {
        this.selectedPost = selectedPost
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemsCommentsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            usernameCommentsItem.text = data[position].name
            bodyCommentsItem.text = data[position].body
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface SelectedPost {
        fun selectedPost(postDetailModel: PostDetailModel, position: Int)
    }

}
