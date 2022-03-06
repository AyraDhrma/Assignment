package id.co.arya.kumparan.data.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.kumparan.data.repository.MainRepository
import id.co.arya.kumparan.data.viewmodel.MainViewModel

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}