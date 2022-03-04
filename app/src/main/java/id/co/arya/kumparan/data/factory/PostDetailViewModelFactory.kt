package id.co.arya.kumparan.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.kumparan.data.repository.MainRepository
import id.co.arya.kumparan.data.repository.PostDetailRepository
import id.co.arya.kumparan.data.viewmodel.MainViewModel
import id.co.arya.kumparan.data.viewmodel.PostDetailViewModel

class PostDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
            return PostDetailViewModel(
                postDetailRepository = PostDetailRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}