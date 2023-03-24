package com.example.medic.analyzes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.medic.R
import com.example.medic.data.ApiService
import com.example.medic.databinding.FragmentAnalyzesBinding
import kotlinx.coroutines.launch

class AnalyzesFragment : Fragment() {
    lateinit var binding: FragmentAnalyzesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalyzesBinding.inflate(inflater, container, false)


        val adapterAnalyzes = AnalyzesAdapter()
        val adapterNews = NewsAdapter()
        binding.analyzesRV.adapter = adapterAnalyzes
        binding.newsRV.adapter = adapterNews


        lifecycleScope.launch {
            try {
                adapterAnalyzes.submitItems(ApiService.retrofit.getCatalog())

            } catch (ex: Exception) {
                Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            try {
                adapterNews.submitItems(ApiService.retrofit.getNews())
            } catch (ex: Exception) {
                Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.botNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.analyzesItem ->{
                    findNavController().navigate(R.id.analyzesFragment)
                }
                R.id.resultItem -> {
                    findNavController().navigate(R.id.iconAppFragment)
                }
                R.id.supportItem -> {
                    findNavController().navigate(R.id.iconAppFragment)
                }
                R.id.profileItem -> {
                    findNavController().navigate(R.id.createCardFragment)
                }
            }
            true
        }

        return binding.root
    }
}