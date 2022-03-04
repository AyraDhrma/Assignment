package id.co.arya.kumparan.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import id.co.arya.kumparan.R
import id.co.arya.kumparan.api.StatusApi
import id.co.arya.kumparan.data.factory.HomeViewModelFactory
import id.co.arya.kumparan.data.viewmodel.HomeViewModel
import id.co.arya.kumparan.databinding.FragmentHomeBinding
import id.co.arya.kumparan.utils.hideView
import id.co.arya.kumparan.utils.showToast
import id.co.arya.kumparan.utils.showView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModelFactory =
            HomeViewModelFactory()
        homeViewModel =
            ViewModelProvider(requireActivity(), homeViewModelFactory)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchPost()
    }

    private fun fetchPost() {
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.listPostApi()
                .observe(viewLifecycleOwner) { result ->
                    when (result.statusApi) {
                        StatusApi.LOADING -> {
                            binding.progressHome.showView()

                        }
                        StatusApi.SUCCESS -> {
                            binding.progressHome.hideView()
                            result.data?.let {
                                homeViewModel.setupToPostRecyclerView(
                                    binding,
                                    it,
                                    mContext
                                )
                            }
                        }
                        StatusApi.ERROR -> {
                            binding.progressHome.hideView()
                            showToast(
                                resources.getString(R.string.check_internet_connection),
                                mContext
                            )
                        }
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}