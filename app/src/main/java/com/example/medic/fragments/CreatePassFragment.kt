package com.example.medic.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentCreatePassBinding

const val PREF_PASSWORD = "PREF_PASSWORD"

class CreatePassFragment : Fragment() {
    private lateinit var binding: FragmentCreatePassBinding
    private lateinit var editTexts: List<EditText>
    private lateinit var cards: List<CardView>
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePassBinding.inflate(inflater, container, false)

        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_createPassFragment_to_createCardFragment)
        }

        editTexts = listOf(binding.editText1, binding.editText2, binding.editText3, binding.editText4)
        cards = listOf(binding.card1, binding.card2, binding.card3, binding.card4)

        binding.editText1.requestFocus()
        binding.editText1.showSoftInputOnFocus = true

        for (i in editTexts.indices) {
            editTexts[i].doAfterTextChanged {
                if (it?.length == 1) {
                    if (i < editTexts.lastIndex) {
                        editTexts[i + 1].requestFocus()
                    } else {
                        preferences = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                        val passwordBuilder = StringBuilder()
                        for (editText in editTexts) {
                            passwordBuilder.append(editText.text.toString())
                        }
                        preferences.edit()
                            .putString(PREF_PASSWORD, passwordBuilder.toString())
                            .apply()
                        val w = preferences.getString(PREF_PASSWORD, "")
                        Log.d("pref", "pass = $w")
                        findNavController().navigate(R.id.action_createPassFragment_to_createCardFragment)
                    }
                    cards[i].setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.but_active))
                } else {
                    if (i > 0) {
                        editTexts[i - 1].requestFocus()
                    }
                    cards[i].setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                }
            }
        }
        return binding.root
    }
}
