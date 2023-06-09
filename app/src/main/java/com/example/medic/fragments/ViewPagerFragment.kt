package com.example.medic.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.adapter.PagerAdapter
import com.example.medic.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        initial()
        return binding.root

    }

    private fun initial() {
        val prefs = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val onboardCompleted = prefs.getBoolean("completed", false)
        if (onboardCompleted) {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginRegisterFragment)
        } else {
            binding.viewPager2.adapter = PagerAdapter(this)
        }
    }

}