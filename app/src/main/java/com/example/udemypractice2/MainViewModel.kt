package com.example.udemypractice2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// AndroidのViewModelを継承 :ViewModel()
class MainViewModel :ViewModel(){
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
}