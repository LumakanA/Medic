package com.example.medic.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentScreensaverBinding

class ScreensaverFragment : Fragment() {
    lateinit var binding: FragmentScreensaverBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreensaverBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler.postDelayed({
            findNavController().navigate(R.id.action_screensaverFragment_to_viewPagerFragment)
        }, 2000)
    }
}