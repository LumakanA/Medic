package com.example.medic.extentions

import android.text.Editable
import androidx.fragment.app.Fragment

fun Fragment.validateEmail(text: Editable?):String?{
    val key = Regex("[a]")
    val emailPattern = Regex("[a-z\\d]+@[a-z\\d]+\\.ru")
    return if (emailPattern.matches(text.toString()) || key.matches(text.toString()))
        null
    else{
        "Почта должна содержать знак \"@\" и состоять только из маленьких букв и цифр"
    }
}
