package id.co.arya.kumparan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import id.co.arya.kumparan.R
import id.co.arya.kumparan.api.StatusApi
import id.co.arya.kumparan.data.factory.MainViewModelFactory
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.data.viewmodel.MainViewModel
import id.co.arya.kumparan.databinding.ActivityMainBinding
import id.co.arya.kumparan.utils.hideView
import id.co.arya.kumparan.utils.showToast
import id.co.arya.kumparan.utils.showView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var listUser: UserModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModelFactory = MainViewModelFactory()
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        fetchUser()
        fetchPost()
    }

    fun fetchUser() {
        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.listUserApi()
                .observe(this@MainActivity) { result ->
                    when (result.statusApi) {
                        StatusApi.LOADING -> {
                            binding.progressHome.showView()
                        }
                        StatusApi.SUCCESS -> {
                            binding.progressHome.hideView()
                            result.data?.let {
                                listUser = it
                            }
                        }
                        StatusApi.ERROR -> {
                            binding.progressHome.hideView()
                            showToast(
                                resources.getString(R.string.check_internet_connection),
                                this@MainActivity
                            )
                        }
                    }
                }
        }
    }

    fun fetchPost() {
        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.listPostApi()
                .observe(this@MainActivity) { result ->
                    when (result.statusApi) {
                        StatusApi.LOADING -> {
                            binding.progressHome.showView()
                        }
                        StatusApi.SUCCESS -> {
                            binding.progressHome.hideView()
                            result.data?.let {
                                mainViewModel.setupToPostRecyclerView(
                                    binding,
                                    it,
                                    this@MainActivity,
                                    listUser
                                )
                            }
                        }
                        StatusApi.ERROR -> {
                            binding.progressHome.hideView()
                            showToast(
                                resources.getString(R.string.check_internet_connection),
                                this@MainActivity
                            )
                        }
                    }
                }
        }
    }

}