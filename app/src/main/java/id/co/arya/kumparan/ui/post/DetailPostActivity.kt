package id.co.arya.kumparan.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.arya.kumparan.R
import id.co.arya.kumparan.data.model.PostDetailModel
import id.co.arya.kumparan.databinding.ActivityDetailPostBinding
import id.co.arya.kumparan.utils.StringUtils

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private var detailModel: PostDetailModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailModel =
            intent.getSerializableExtra(StringUtils.INTENT_DETAIL_DATA) as PostDetailModel?
    }

    override fun onStart() {
        super.onStart()

        detailModel?.let {
            binding.apply {
                postTitleDetailPost.text = it.postTitle
                postContentDetailPost.text = it.postBody
            }
        }

        events()
    }

    private fun events() {
        binding.apply {
            closeDetailPost.setOnClickListener {
                onBackPressed()
            }
        }
    }

}