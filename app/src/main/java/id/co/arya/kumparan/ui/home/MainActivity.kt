package id.co.arya.kumparan.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.R
import id.co.arya.kumparan.api.StatusApi
import id.co.arya.kumparan.data.factory.MainViewModelFactory
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import id.co.arya.kumparan.data.viewmodel.MainViewModel
import id.co.arya.kumparan.databinding.ActivityMainBinding
import id.co.arya.kumparan.library.adapter.ListPostAdapter
import id.co.arya.kumparan.ui.post.DetailPostActivity
import id.co.arya.kumparan.utils.StringUtils
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

        initObject()

        lifecycleScope.launch(Dispatchers.Main) {
            fetchUser()
            fetchPost()
        }

    }

    override fun onStart() {
        super.onStart()

        events()
    }

    private fun events() {
        binding.apply {
            mainSection.setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_NO_ANIMATION
                    )
                    overridePendingTransition(0, 0)
                    finish()
                    overridePendingTransition(0, 0)
                    startActivity(intent)
                    mainSection.isRefreshing = false
                }, 2000)
            }
        }
    }

    private fun initObject() {
        mainViewModelFactory = MainViewModelFactory(application)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
    }

    suspend fun fetchUser() {
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

    suspend fun fetchPost() {
        mainViewModel.listPostApi()
            .observe(this@MainActivity) { result ->
                when (result.statusApi) {
                    StatusApi.LOADING -> {
                        binding.progressHome.showView()
                    }
                    StatusApi.SUCCESS -> {
                        binding.progressHome.hideView()
                        result.data?.let {
                            setupToPostRecyclerView(
                                it,
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

    private fun setupToPostRecyclerView(
        listPost: PostModel,
        listUser: UserModel
    ) {
        binding.apply {
            val adapter = ListPostAdapter(listPost, listUser)
            listPostRecyclerView.hasFixedSize()
            listPostRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            listPostRecyclerView.adapter = adapter
            adapter.onSelectedPost(object : ListPostAdapter.SelectedPost {
                override fun selectedPost(postDetailModel: PostDetailModel, position: Int) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DetailPostActivity::class.java
                        ).apply {
                            putExtra(
                                StringUtils.INTENT_DETAIL_DATA,
                                postDetailModel
                            )
                        }
                    )
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.emptyTable()
    }

}