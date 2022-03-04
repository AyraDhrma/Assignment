package id.co.arya.kumparan.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.kumparan.data.repository.MainRepository
import id.co.arya.kumparan.data.viewmodel.MainViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                mainRepository = MainRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}