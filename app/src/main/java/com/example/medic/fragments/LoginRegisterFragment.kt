package com.example.medic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentLoginRegisterBinding

class LoginRegisterFragment : Fragment() {
    lateinit var binding: FragmentLoginRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginRegisterBinding.inflate(inflater, container, false)
        binding.butNext.isEnabled = false
        binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_unactive)
        binding.enterEmail.doAfterTextChanged { email ->
            if (email.isNullOrEmpty()) {
                binding.butNext.isEnabled = false
                binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_unactive)
            } else {
                binding.butNext.isEnabled = true
                binding.butNext.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.but_active)
            }
        }
        binding.butNext.setOnClickListener {
            findNavController().navigate(R.id.action_loginRegisterFragment_to_emailKodFragment)
        }
        binding.butYandex.setOnClickListener{
            TODO()
        }
        return binding.root

    }

}