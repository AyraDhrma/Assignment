package id.co.arya.kumparan.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.kumparan.data.repository.HomeRepository
import id.co.arya.kumparan.data.viewmodel.HomeViewModel

class HomeViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                homeRepository = HomeRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}