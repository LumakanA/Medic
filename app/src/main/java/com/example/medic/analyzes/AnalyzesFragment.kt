package com.example.medic.analyzes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.medic.data.ApiService
import com.example.medic.databinding.FragmentAnalyzesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyzesFragment : Fragment() {
    lateinit var binding: FragmentAnalyzesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalyzesBinding.inflate(inflater, container, false)


        val adapter = AnalyzesAdapter()

        binding.analyzesRV.adapter = adapter


        lifecycleScope.launch {
            try {
                adapter.submitItems(ApiService.retrofit.getCatalog())

            } catch (ex: Exception) {
                Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }
}