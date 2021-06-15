package com.example.giphytesttaskkyrylohryzhuk.ui.detalscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.giphytesttaskkyrylohryzhuk.databinding.FragmentDetalScreenBinding

class DetalScreenFragment : Fragment() {
    private lateinit var binding: FragmentDetalScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val image = arguments?.getString(GIFS_URL)
        if (image == null) {
            findNavController().popBackStack()
            return
        }
        Glide.with(requireContext())
            .load(image)
            .load(image)
            .into(binding.imageViewDetal)
    }

    companion object {
        const val GIFS_URL = "giphy_url"
    }
}