package com.example.medic.fragments

import com.example.medic.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.databinding.FragmentCreateCardBinding

const val PREF_DATA_USER = "PREF_DATA_USER"

class CreateCardFragment : Fragment() {
    private lateinit var binding: FragmentCreateCardBinding
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCardBinding.inflate(inflater, container, false)

        val items = arrayOf<String>("Мужской", "Женский")
        val adapter = ArrayAdapter(requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , items)
        binding.spisok.setAdapter(adapter)
        binding.spisok.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.spisok.showDropDown()
        }
        binding.butCreate.isEnabled = false
        binding.butCreate.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(),R.color.but_unactive
        )
        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_createCardFragment_to_analyzesFragment)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameEditText.doAfterTextChanged {
            checksAndSet()
        }

        binding.surnameEditText.doAfterTextChanged {
            checksAndSet()
        }

        binding.lastnameEditText.doAfterTextChanged {
            checksAndSet()
        }

        binding.dobEditText.doAfterTextChanged {
            checksAndSet()
        }

        binding.spisok.doAfterTextChanged {
            checksAndSet()
        }

        binding.butCreate.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val lastname = binding.lastnameEditText.text.toString()
            val dob = binding.dobEditText.text.toString()
            val spisok = binding.spisok.text.toString()

            if (name.isNotBlank() && surname.isNotBlank() && lastname.isNotBlank() && dob.isNotBlank() && spisok.isNotBlank()) {
                val myList = listOf(name, surname, lastname, dob, spisok)
                preferences = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                preferences.edit()
                    .putString(PREF_DATA_USER, myList.toList().toString())
                    .apply()
                val w = preferences.getString(PREF_DATA_USER, "")
                Log.d("pref", "data = $w")
                findNavController().navigate(R.id.action_createCardFragment_to_analyzesFragment)
            }
        }
    }

    private fun checksAndSet() {
        val name = binding.nameEditText.text.toString()
        val surname = binding.surnameEditText.text.toString()
        val lastname = binding.lastnameEditText.text.toString()
        val dob = binding.dobEditText.text.toString()
        val spisok = binding.spisok.text.toString()

        if (name.isNotBlank() && surname.isNotBlank() && lastname.isNotBlank() && dob.isNotBlank() && spisok.isNotBlank()) {
            binding.butCreate.isEnabled = true
            binding.butCreate.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),R.color.but_active
            )
        } else {
            binding.butCreate.isEnabled = false
            binding.butCreate.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),R.color.but_unactive
            )
        }
    }
}