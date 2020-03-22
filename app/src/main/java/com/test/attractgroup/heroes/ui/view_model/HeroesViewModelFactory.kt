package com.test.attractgroup.heroes.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.attractgroup.heroes.data.IHeroesRepository

class HeroesViewModelFactory(private val repository: IHeroesRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesViewModel::class.java)) {
            return HeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}