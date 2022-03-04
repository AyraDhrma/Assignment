package id.co.arya.kumparan.library.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.databinding.RvItemsPostBinding
import id.co.arya.kumparan.ui.post.DetailPostActivity

class ListPostAdapter(private val listPostModel: PostModel, private val listUser: UserModel) :
    RecyclerView.Adapter<ListPostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsPostBinding) : RecyclerView.ViewHolder(binding.root)

    lateinit var selectedPost: SelectedPost

    fun onSelectedPost(selectedPost: SelectedPost) {
        this.selectedPost = selectedPost
    }

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
        holder.apply {
            binding.apply {
                titlePostItem.text = listPostModel[position].title
                bodyPostItem.text = listPostModel[position].body

                listUser.map {
                    if (it.id == listPostModel[position].userId) {
                        val username = it.name
                        val companyName = it.company.name
                        sectionPost.setOnClickListener {
                            selectedPost.selectedPost(
                                PostDetailModel(
                                    listPostModel[position].id.toString(),
                                    listPostModel[position].userId.toString(),
                                    username,
                                    listPostModel[position].title,
                                    listPostModel[position].body
                                ), position
                            )
                        }
                        usernamePostItem.text = username
                        userCompanyPostItem.text = companyName
                        return
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listPostModel.size
    }

    interface SelectedPost {
        fun selectedPost(postDetailModel: PostDetailModel, position: Int)
    }

}
