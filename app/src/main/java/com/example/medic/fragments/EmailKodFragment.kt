package com.example.medic.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.databinding.FragmentEmailKodBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PREF_EMAIL_KOD = "PREF_EMAIL_KOD"
class EmailKodFragment : Fragment() {
    lateinit var binding: FragmentEmailKodBinding
    lateinit var editTexts: List<EditText>
    lateinit var preferences: SharedPreferences
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailKodBinding.inflate(inflater, container, false)
        //startTimer(10000)
        binding.butBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.enterKod1.requestFocus()
        editTexts = listOf(binding.enterKod1, binding.enterKod2, binding.enterKod3, binding.enterKod4)
      //  startTimer(15000)
        for (i in editTexts.indices) {
            editTexts[i].doAfterTextChanged {text ->
                val allEmpty = editTexts.all { it.text.isNullOrEmpty() }
                if (allEmpty){
                  //  startTimer(11000)
                }
                if (text?.length == 1) {
                    if (i < editTexts.lastIndex){
                        editTexts[i + 1].requestFocus()}
                    else {
                        preferences = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                        val emailKodBuilder = StringBuilder()
                        for (editText in editTexts){
                            emailKodBuilder.append(editText.text.toString())
                        }
                        preferences.edit()
                            .putString(PREF_EMAIL_KOD, emailKodBuilder.toString())
                            .apply()
                        val w = preferences.getString(PREF_EMAIL_KOD, "")
                        Log.d("pref", "email_kod = $w")
                        navigate()
                    }
                }
                else if (i > 0){
                    editTexts[i - 1].requestFocus()
                }
            }
        }

        lifecycleScope.launch {
        var count = 60
            while (true){
                count--
                binding.timer.text = "Отправить код повторно можно\nбудет через $count секунд"
                withContext(Dispatchers.IO) {
                    Thread.sleep(1000)
                }
                if (count == 1){
                    count = 60
                }
            }
        }
        return binding.root
    }

    private fun startTimer(time: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
            }
        }.start()
    }

    private fun navigate(){
        findNavController().navigate(R.id.action_emailKodFragment_to_createPassFragment)
    }
}