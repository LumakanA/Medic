package com.example.medic.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.medic.MainActivity
import com.example.medic.databinding.FragmentOnBoard1Binding
import com.example.medic.fragments.ViewPagerFragment


class OnBoard1Fragment : Fragment() {

    lateinit var binding: FragmentOnBoard1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoard1Binding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skipTextView.setOnClickListener {
            (requireParentFragment() as ViewPagerFragment).binding.viewPager2.setCurrentItem(1, true)
        }
    }
}