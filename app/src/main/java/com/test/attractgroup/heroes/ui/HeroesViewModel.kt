package com.test.attractgroup.heroes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.attractgroup.heroes.data.model.Hero
import com.test.attractgroup.heroes.data.IHeroesRepository

class HeroesViewModel(private val repository: IHeroesRepository) : ViewModel() {

    private val _state = MutableLiveData<HeroesFragmentState>()
    private var _heroes: List<Hero> = ArrayList()

    fun refreshData() {
        _state.value =
            if (_heroes.isNotEmpty()) HeroesFragmentState.LoadDataSuccess(_heroes) else HeroesFragmentState.Loading

        repository.getHeroes(object : IHeroesRepository.LoadHeroesCallback {

            override fun onSuccess(data: List<Hero>) {
                _heroes = ArrayList(data)
                _state.value = HeroesFragmentState.LoadDataSuccess(_heroes)
            }

            override fun onError(message: String) {
                _state.value = HeroesFragmentState.LoadDataFailed(message)
            }
        })
    }

    val state: LiveData<HeroesFragmentState> = _state
}