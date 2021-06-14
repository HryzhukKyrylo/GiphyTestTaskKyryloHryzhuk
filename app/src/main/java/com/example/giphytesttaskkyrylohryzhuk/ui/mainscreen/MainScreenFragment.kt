package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.giphytesttaskkyrylohryzhuk.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {

    private lateinit var binding : FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

}