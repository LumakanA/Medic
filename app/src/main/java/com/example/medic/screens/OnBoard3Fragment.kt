package com.example.medic.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentOnBoard3Binding
import com.example.medic.databinding.FragmentScreensaverBinding
import com.example.medic.fragments.APP_PREFERENCES

const val ONBOARD = "ONBOARD"
class OnBoard3Fragment : Fragment() {

    lateinit var binding: FragmentOnBoard3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoard3Binding.inflate(inflater, container, false)
        binding.skipTextView.setOnClickListener {
            setOnboardCompleted()
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginRegisterFragment)
        }
        return binding.root
    }
    private fun setOnboardCompleted() {
        val prefs = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("completed", true)
        editor.apply()
        val w = prefs.getString(ONBOARD, "")
        Log.d("pref","onBoard = ${w.toBoolean().toString()}")
    }
}