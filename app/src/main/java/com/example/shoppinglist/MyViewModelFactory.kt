package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val repo: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <A : ViewModel?> create(modelClass: Class<A>): A {
        return MyViewModel(repo) as A
    }
}