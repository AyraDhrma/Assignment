package id.co.arya.kumparan.ui.albums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import id.co.arya.kumparan.R
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.databinding.ActivityPhotosDetailBinding
import id.co.arya.kumparan.utils.StringUtils

class PhotosDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosDetailBinding
    private var localPhotosModel: LocalPhotosModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        localPhotosModel =
            intent.getSerializableExtra(StringUtils.INTENT_DETAIL_DATA) as LocalPhotosModel?
    }

    override fun onStart() {
        super.onStart()
        localPhotosModel?.let { photos ->
            binding.apply {
                photosTitleDetail.text = photos.title
                Picasso.get()
                    .load(photos.url)
                    .placeholder(R.drawable.thumbnail)
                    .error(R.drawable.thumbnail)
                    .into(photosImageDetail);
            }
        }

        events()
    }

    private fun events() {
        binding.apply {
            closeDetail.setOnClickListener {
                onBackPressed()
            }
        }
    }

}