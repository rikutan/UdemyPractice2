package com.example.udemypractice2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.roundToInt

// AndroidのViewModelを継承 :ViewModel()
class MainViewModel :ViewModel(){
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var bmi by mutableFloatStateOf(0f)
    var result by mutableStateOf("") // BMI結果コメントの保持
    var isResultShown by mutableStateOf(false) // 結果を表示するかどうか

    // BMI = 体重(kg) ÷ (身長(m) × 身長(m) ** 2
    fun calculateBMI() {
        // ?.div(100) ?: 0f ,nullの場合なら?.div(100)をスキップ
        // して0fを返す
        val heightNumber = height.toFloatOrNull()?.div(100) ?: 0f
        val weightNumber = weight.toFloatOrNull() ?: 0f

        // .pow 累乗の計算をする
        bmi = if(heightNumber > 0f && weightNumber > 0f) {
            (weightNumber / heightNumber.pow(2) * 10).roundToInt() / 10f
        } else 0f

        // MainActivity で呼び出せるようにする
        result = bmiResult(bmi)

        // MainでのUIを表示する判定
        isResultShown = true
    }

    // テキストフィールドをクリアする関数
    fun clearTextField() {
        height = ""
        weight = ""
        isResultShown = false
    }

    // BMIの結果をもとにコメントを返す関数
    private fun bmiResult(bmi: Float):String  {
        return when {
            bmi < 18.5 -> "痩せ型"
            bmi < 25 -> "標準"
            bmi < 30 -> "肥満(1度)"
            bmi < 35 -> "肥満(2度)"
            else -> "肥満(3度)"
        }
    }
}