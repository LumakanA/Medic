package com.example.medic.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentLoginRegisterBinding
import com.example.medic.extentions.validateEmail
import kotlin.math.E

const val APP_PREFERENCES = "APP_PREFERENCES"
const val PREF_EMAIL = "PREF_EMAIL"
class LoginRegisterFragment : Fragment() {
    private lateinit var binding: FragmentLoginRegisterBinding
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginRegisterBinding.inflate(inflater, container, false)
        binding.butNext.isEnabled = false
        binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_unactive)
        binding.enterEmail.doAfterTextChanged {
            val emailError = validateEmail(binding.enterEmail.text)
            binding.enterEmail.error = emailError
            if (emailError != null) {
                binding.butNext.isEnabled = false
                binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_unactive)
            } else {
                binding.butNext.isEnabled = true
                binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_active)
            }
        }
        binding.butNext.setOnClickListener {
            val email = binding.enterEmail.text.toString()
            preferences = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            preferences.edit()
                .putString(PREF_EMAIL, email)
                .apply()
            val w = preferences.getString(PREF_EMAIL, "")
            Log.d("pref", "email = $w")
            findNavController().navigate(R.id.action_loginRegisterFragment_to_emailKodFragment)
        }
        binding.butYandex.setOnClickListener{
            TODO()
        }

        binding.otday.setOnClickListener {
            setOnboardCompleted()
            findNavController().navigate(R.id.viewPagerFragment)
        }

        return binding.root

    }
    private fun setOnboardCompleted() {
        val prefs = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("completed", false)
        editor.apply()
    }
}