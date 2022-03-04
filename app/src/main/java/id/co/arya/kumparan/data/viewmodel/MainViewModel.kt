package id.co.arya.kumparan.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.api.ResultApi
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.data.repository.MainRepository
import id.co.arya.kumparan.databinding.ActivityMainBinding
import id.co.arya.kumparan.library.adapter.ListPostAdapter
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    suspend fun listPostApi() = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = mainRepository.listPostApi()))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    suspend fun listUserApi() = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = mainRepository.listUserApi()))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    fun setupToPostRecyclerView(
        binding: ActivityMainBinding,
        listPost: PostModel,
        mContext: Context,
        listUser: UserModel
    ) {
        binding.apply {
            listPostRecyclerView.hasFixedSize()
            listPostRecyclerView.layoutManager = LinearLayoutManager(mContext)
            listPostRecyclerView.adapter = ListPostAdapter(listPost, listUser)
        }
    }

}