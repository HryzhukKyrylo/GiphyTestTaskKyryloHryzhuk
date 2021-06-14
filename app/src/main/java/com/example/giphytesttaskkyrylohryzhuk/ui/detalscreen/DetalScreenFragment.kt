package com.example.giphytesttaskkyrylohryzhuk.ui.detalscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.giphytesttaskkyrylohryzhuk.databinding.FragmentDetalScreenBinding
import com.example.giphytesttaskkyrylohryzhuk.utils.CheckNetwork
import com.example.giphytesttaskkyrylohryzhuk.utils.Resource


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
        if (CheckNetwork.hasInternetConnection(requireContext())) {
            Glide.with(requireContext())
                .load(image)
                .load(image)
                .into(binding.imageViewDetal)
        } else {
            Toast.makeText(requireContext(), "No Internet connection!", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        const val GIFS_URL = "image_url"
    }
}