package id.co.arya.kumparan.data.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.kumparan.data.repository.MainRepository
import id.co.arya.kumparan.data.repository.UserRepository
import id.co.arya.kumparan.data.viewmodel.MainViewModel
import id.co.arya.kumparan.data.viewmodel.UserViewModel

class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}