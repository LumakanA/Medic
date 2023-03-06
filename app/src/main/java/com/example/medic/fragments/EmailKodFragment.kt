package com.example.medic.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.databinding.FragmentEmailKodBinding


class EmailKodFragment : Fragment() {
    lateinit var binding: FragmentEmailKodBinding
    lateinit var editTexts: List<EditText>
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailKodBinding.inflate(inflater, container, false)
        binding.butBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.enterKod1.requestFocus()
        editTexts = listOf(binding.enterKod1, binding.enterKod2, binding.enterKod3, binding.enterKod4)
        for (i in 0 until editTexts.size - 1) {
            editTexts[i].doAfterTextChanged {
                if (it?.length == 1) {
                    editTexts[i + 1].requestFocus()
                }
            }
        }
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding.enterKod4.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Сохраняем данные из EditText полей в SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("key1", editTexts.joinToString("") { it.text.toString() })
                editor.apply()
                Toast.makeText(requireContext(), "success!", Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }
        return binding.root
    }
}