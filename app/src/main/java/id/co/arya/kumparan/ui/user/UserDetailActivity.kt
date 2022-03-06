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
import id.co.arya.kumparan.data.model.LocalPhotosModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObject()

        lifecycleScope.launch(Dispatchers.Main) {
            fetchPhotos()
            fetchUser()
            fetchAlbums()
        }

        events()
    }

    private fun initObject() {
        factory = UserViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        userId = intent.getStringExtra(StringUtils.INTENT_DETAIL_DATA).toString()
    }

    private suspend fun fetchUser() {
        viewModel.detailUserApi(userId).observe(this@UserDetailActivity) { result ->
            when (result.statusApi) {
                StatusApi.LOADING -> {
                    binding.progressDetailUser.showView()
                }
                StatusApi.SUCCESS -> {
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

    private suspend fun fetchPhotos() {
        viewModel.listAlbumsPhotosApi().observe(this@UserDetailActivity) { result ->
            when (result.statusApi) {
                StatusApi.LOADING -> {
                    binding.progressAlbumsUser.showView()
                }
                StatusApi.SUCCESS -> {
                    result.data?.let {
                        it.map { list ->
                            viewModel.insertPhotos(
                                LocalPhotosModel(
                                    list.id,
                                    list.albumId,
                                    list.thumbnailUrl,
                                    list.title,
                                    list.url
                                )
                            )
                        }
                    }
                }
                StatusApi.ERROR -> {
                    binding.progressAlbumsUser.hideView()
                    showToast(
                        resources.getString(R.string.check_internet_connection),
                        this@UserDetailActivity
                    )
                }
            }
        }
    }

    private suspend fun fetchAlbums() {
        viewModel.listAlbumsUserApi(userId).observe(this@UserDetailActivity) { result ->
            when (result.statusApi) {
                StatusApi.LOADING -> {
                    binding.progressAlbumsUser.showView()
                }
                StatusApi.SUCCESS -> {
                    result.data?.let {
                        setupToAlbumsRecyclerView(it)
                    }
                }
                StatusApi.ERROR -> {
                    binding.progressAlbumsUser.hideView()
                    showToast(
                        resources.getString(R.string.check_internet_connection),
                        this@UserDetailActivity
                    )
                }
            }
        }
    }

    private fun setupToAlbumsRecyclerView(albumsModel: AlbumsModel) {
        binding.progressAlbumsUser.showView()
        val photosModel = arrayListOf<LocalPhotosModel>()
        albumsModel.map {
            photosModel.add(viewModel.selectPhotosByAlbums(it.id))
        }

        val adapter = ListAlbumsAdapter(albumsModel, photosModel)
        binding.apply {
            listAlbumsRecyclerView.hasFixedSize()
            listAlbumsRecyclerView.layoutManager = LinearLayoutManager(this@UserDetailActivity)
            listAlbumsRecyclerView.adapter = adapter
            binding.progressAlbumsUser.hideView()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupToView(userData: UserModel.UserModelItem) {
        binding.apply {
            progressDetailUser.showView()
            usernameDetailUser.text = "Name : ${userData.name}"
            emailDetailUser.text = userData.email
            addressDetailUser.text =
                "${userData.address.street}, ${userData.address.suite}, ${userData.address.city}, ${userData.address.zipcode}"
            companyDetailUser.text = "Company ${userData.company.name}"
            progressDetailUser.hideView()
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