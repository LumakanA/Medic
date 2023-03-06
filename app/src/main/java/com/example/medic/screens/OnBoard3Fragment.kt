package com.example.medic.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentOnBoard3Binding
import com.example.medic.databinding.FragmentScreensaverBinding


class OnBoard3Fragment : Fragment() {

    lateinit var binding: FragmentOnBoard3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoard3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnBoard3Binding.bind(view)
        binding.skipTextView.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginRegisterFragment)
        }
    }
}