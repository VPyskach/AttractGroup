package com.test.attractgroup.heroes.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.attractgroup.heroes.data.model.Hero
import com.test.attractgroup.heroes.data.IHeroesRepository
import com.test.attractgroup.heroes.ui.HeroesFragmentState

class HeroesViewModel(private val repository: IHeroesRepository) : ViewModel() {

    private val _state = MutableLiveData<HeroesFragmentState>()
    private var _heroes: List<Hero> = ArrayList()

    fun loadHeroes() {
        _state.value =
            if (_heroes.isNotEmpty()) HeroesFragmentState.ShowFilteredData(
                _heroes
            ) else HeroesFragmentState.Loading

        repository.getHeroes(object : IHeroesRepository.LoadHeroesCallback {

            override fun onSuccess(data: List<Hero>) {
                if (_heroes != data) {
                    _heroes = data
                    _state.value =
                        HeroesFragmentState.ShowFilteredData(_heroes)
                }
            }

            override fun onError(message: String) {
                _state.value =
                    HeroesFragmentState.ShowLoadDataError(message)
            }
        })
    }



    fun filterData(enteredString: String){

    }

    fun onHeroItemClick(id: Int){

    }

    val state: LiveData<HeroesFragmentState> = _state
}