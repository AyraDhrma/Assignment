package id.co.arya.kumparan.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.api.ResultApi
import id.co.arya.kumparan.data.model.PostCommentsModel
import id.co.arya.kumparan.data.repository.PostDetailRepository
import id.co.arya.kumparan.databinding.ActivityDetailPostBinding
import id.co.arya.kumparan.library.adapter.ListPostCommentsAdapter
import kotlinx.coroutines.Dispatchers

class PostDetailViewModel(
) : ViewModel() {

    private lateinit var postDetailRepository: PostDetailRepository

    init {
        postDetailRepository = PostDetailRepository()
    }

    suspend fun listPostCommentsApi(postId: String) = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = postDetailRepository.listPostCommentsApi(postId)))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

}