package id.co.arya.kumparan.data.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.api.ResultApi
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.repository.HomeRepository
import id.co.arya.kumparan.databinding.FragmentHomeBinding
import id.co.arya.kumparan.library.adapter.ListPostAdapter
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    suspend fun listPostApi() = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = homeRepository.listPostApi()))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    fun setupToPostRecyclerView(binding: FragmentHomeBinding, list: PostModel, mContext: Context) {
        binding.apply {
            listPostRecyclerView.hasFixedSize()
            listPostRecyclerView.layoutManager = LinearLayoutManager(mContext)
            listPostRecyclerView.adapter = ListPostAdapter(list)
        }
    }

}