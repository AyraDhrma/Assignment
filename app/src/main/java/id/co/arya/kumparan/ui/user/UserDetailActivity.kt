package id.co.arya.kumparan.ui.user

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.R
import id.co.arya.kumparan.api.StatusApi
import id.co.arya.kumparan.data.factory.UserViewModelFactory
import id.co.arya.kumparan.data.model.AlbumsModel
import id.co.arya.kumparan.data.model.PhotosModel
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.data.viewmodel.UserViewModel
import id.co.arya.kumparan.databinding.ActivityUserDetailBinding
import id.co.arya.kumparan.library.adapter.ListAlbumsAdapter
import id.co.arya.kumparan.utils.StringUtils
import id.co.arya.kumparan.utils.hideView
import id.co.arya.kumparan.utils.showToast
import id.co.arya.kumparan.utils.showView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private var userId: String = ""
    private lateinit var viewModel: UserViewModel
    private lateinit var factory: UserViewModelFactory
    private lateinit var photosModel: PhotosModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObject()

        fetchUser()
        fetchPhotos()
        fetchAlbums()

        events()
    }

    private fun initObject() {
        factory = UserViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        userId = intent.getStringExtra(StringUtils.INTENT_DETAIL_DATA).toString()
    }

    private fun fetchUser() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.detailUserApi(userId).observe(this@UserDetailActivity) { result ->
                when (result.statusApi) {
                    StatusApi.LOADING -> {
                        binding.progressDetailUser.showView()
                    }
                    StatusApi.SUCCESS -> {
                        binding.progressDetailUser.hideView()
                        result.data?.let {
                            if (it.id.toString() != "") {
                                setupToView(it)
                            }
                        }
                    }
                    StatusApi.ERROR -> {
                        binding.progressDetailUser.hideView()
                        showToast(
                            resources.getString(R.string.check_internet_connection),
                            this@UserDetailActivity
                        )
                    }
                }
            }
        }
    }

    private fun fetchPhotos() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listAlbumsPhotosApi().observe(this@UserDetailActivity) { result ->
                when (result.statusApi) {
                    StatusApi.LOADING -> {
                        binding.progressDetailUser.showView()
                    }
                    StatusApi.SUCCESS -> {
                        binding.progressDetailUser.hideView()
                        result.data?.let {
                            photosModel = it
                        }
                    }
                    StatusApi.ERROR -> {
                        binding.progressDetailUser.hideView()
                        showToast(
                            resources.getString(R.string.check_internet_connection),
                            this@UserDetailActivity
                        )
                    }
                }
            }
        }
    }

    private fun fetchAlbums() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listAlbumsUserApi(userId).observe(this@UserDetailActivity) { result ->
                when (result.statusApi) {
                    StatusApi.LOADING -> {
                        binding.progressDetailUser.showView()
                    }
                    StatusApi.SUCCESS -> {
                        binding.progressDetailUser.hideView()
                        result.data?.let {
                            setupToAlbumsRecyclerView(it)
                        }
                    }
                    StatusApi.ERROR -> {
                        binding.progressDetailUser.hideView()
                        showToast(
                            resources.getString(R.string.check_internet_connection),
                            this@UserDetailActivity
                        )
                    }
                }
            }
        }
    }

    private fun setupToAlbumsRecyclerView(albumsModel: AlbumsModel) {
        val adapter = ListAlbumsAdapter(albumsModel)
        //adapter.setListPhotos(photosModel)
        binding.apply {
            listAlbumsRecyclerView.hasFixedSize()
            listAlbumsRecyclerView.layoutManager = LinearLayoutManager(this@UserDetailActivity)
            listAlbumsRecyclerView.adapter = adapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupToView(userData: UserModel.UserModelItem) {
        binding.apply {
            usernameDetailUser.text = "Name : ${userData.name}"
            emailDetailUser.text = userData.email
            addressDetailUser.text =
                "${userData.address.street}, ${userData.address.suite}, ${userData.address.city}, ${userData.address.zipcode}"
            companyDetailUser.text = "Company ${userData.company.name}"
        }
    }

    private fun events() {
        binding.apply {
            closeDetailUser.setOnClickListener {
                onBackPressed()
            }
        }
    }

}