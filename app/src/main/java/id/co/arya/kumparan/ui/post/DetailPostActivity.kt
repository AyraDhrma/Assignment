package id.co.arya.kumparan.ui.post

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.kumparan.R
import id.co.arya.kumparan.api.StatusApi
import id.co.arya.kumparan.data.model.PostCommentsModel
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.data.viewmodel.PostDetailViewModel
import id.co.arya.kumparan.databinding.ActivityDetailPostBinding
import id.co.arya.kumparan.library.adapter.ListPostCommentsAdapter
import id.co.arya.kumparan.ui.user.UserDetailActivity
import id.co.arya.kumparan.utils.StringUtils
import id.co.arya.kumparan.utils.hideView
import id.co.arya.kumparan.utils.showToast
import id.co.arya.kumparan.utils.showView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private var detailModel: PostDetailModel? = null
    private lateinit var viewModel: PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObject()

        detailModel =
            intent.getSerializableExtra(StringUtils.INTENT_DETAIL_DATA) as PostDetailModel?

        fetchComments()
    }

    private fun initObject() {
        viewModel = ViewModelProvider(this)[PostDetailViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        detailModel?.let {
            binding.apply {
                postTitleDetailPost.text = it.postTitle
                postContentDetailPost.text = it.postBody
                postPostedbyDetailPost.text = "Posted by ${it.username}"
            }
        }

        events()
    }

    private fun events() {
        binding.apply {
            closeDetailPost.setOnClickListener {
                onBackPressed()
            }
            postPostedbyDetailPost.setOnClickListener {
                detailModel?.let {
                    startActivity(
                        Intent(
                            this@DetailPostActivity,
                            UserDetailActivity::class.java
                        ).putExtra(
                            StringUtils.INTENT_DETAIL_DATA,
                            it.userId
                        )
                    )
                }
            }
        }
    }

    private fun fetchComments() {
        lifecycleScope.launch(Dispatchers.Main) {
            detailModel?.let { it ->
                viewModel.listPostCommentsApi(it.postId)
                    .observe(this@DetailPostActivity) { result ->
                        when (result.statusApi) {
                            StatusApi.LOADING -> {
                                binding.progressDetailPost.showView()
                            }
                            StatusApi.SUCCESS -> {
                                binding.progressDetailPost.hideView()
                                result.data?.let {
                                    setupToRecyclerView(
                                        it,
                                        this@DetailPostActivity
                                    )
                                }
                            }
                            StatusApi.ERROR -> {
                                binding.progressDetailPost.hideView()
                                showToast(
                                    resources.getString(R.string.check_internet_connection),
                                    this@DetailPostActivity
                                )
                            }
                        }
                    }
            }
        }
    }

    fun setupToRecyclerView(
        data: PostCommentsModel,
        context: Context
    ) {
        val adapter = ListPostCommentsAdapter(data)
        binding.apply {
            listCommentRecyclerView.hasFixedSize()
            listCommentRecyclerView.layoutManager = LinearLayoutManager(context)
            listCommentRecyclerView.adapter = adapter
        }
    }

}