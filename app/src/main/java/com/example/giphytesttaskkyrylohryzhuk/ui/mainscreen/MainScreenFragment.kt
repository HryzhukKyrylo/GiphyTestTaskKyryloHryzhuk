package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.giphytesttaskkyrylohryzhuk.R
import com.example.giphytesttaskkyrylohryzhuk.data.model.Data
import com.example.giphytesttaskkyrylohryzhuk.databinding.FragmentMainScreenBinding
import com.example.giphytesttaskkyrylohryzhuk.ui.detalscreen.DetalScreenFragment.Companion.GIFS_URL
import com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.adapter.CustomRecyclerAdapter
import com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.viewmodel.MainViewModel
import com.example.giphytesttaskkyrylohryzhuk.utils.Status
import com.example.giphytesttaskkyrylohryzhuk.utils.StringUtils
import com.example.giphytesttaskkyrylohryzhuk.utils.showSnack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : Fragment(), CustomRecyclerAdapter.OnItemClickedListener {
    @Inject lateinit var stringUtils: StringUtils
    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val giphyAdapter: CustomRecyclerAdapter by lazy { CustomRecyclerAdapter() }
    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGiphy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackPressedListener()
        initAdapter()
        initListener()
        initObserve()
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = giphyAdapter
    }

    private fun initListener() {
        giphyAdapter.setListener(this)
    }

    private fun initObserve() {
        viewModel.responseGiphy.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.data.let { list -> list?.let { retrieveList(it) } }
                    }
                    Status.LOADING -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        resource.message?.let { it1 -> showMessage(it1) }

                    }
                }
            }
        })
    }

    private fun showMessage(message : String){
        if(message == stringUtils.noInternetConnection()) {
            binding.constraintLayout.showSnack(
                stringUtils.noInternetConnection(),
               stringUtils.retry()
            ) {
                viewModel.getGiphy()
            }
        }else {
            Toast.makeText(requireContext(), stringUtils.somethingWentWrong(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initBackPressedListener() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (backPressed + 2000 > System.currentTimeMillis()) {
                        isEnabled = false
                        activity?.onBackPressed()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            stringUtils.clickAgain(),
                            Toast.LENGTH_SHORT
                        ).show()
                        backPressed = System.currentTimeMillis()
                    }
                }
            }
        )
    }

    private fun retrieveList(list: List<Data>) {
        giphyAdapter.updateItems(list)
    }

    override fun onImageClicked(gifUrl: String) {
        findNavController().navigate(
            R.id.navigateToDetalScreenFragment,
            bundleOf(GIFS_URL to gifUrl)
        )
    }
}