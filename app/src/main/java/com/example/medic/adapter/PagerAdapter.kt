package com.example.medic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.medic.screens.OnBoard1Fragment
import com.example.medic.screens.OnBoard2Fragment
import com.example.medic.screens.OnBoard3Fragment

class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnBoard1Fragment()
            1 -> OnBoard2Fragment()
            else -> OnBoard3Fragment()
        }
    }
}