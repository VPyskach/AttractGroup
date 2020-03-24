package com.test.attractgroup.heroes.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.attractgroup.heroes.data.model.Hero
import com.test.attractgroup.heroes.data.IHeroesRepository
import com.test.attractgroup.heroes.ui.HeroesFragmentState

class HeroesViewModel(private val repository: IHeroesRepository) : ViewModel() {

    private val _viewStateObserver = MutableLiveData<HeroesFragmentState>()
    private var _viewState: HeroesFragmentState? = null

    fun onStart() {
        if (_viewState != null) {
            updateState()
        } else {
            _viewState = HeroesFragmentState.Loading
            updateState()
            repository.getHeroes(object : IHeroesRepository.LoadHeroesCallback {

                override fun onSuccess(data: List<Hero>) {
                    _viewState = HeroesFragmentState.ShowLoadedData(data)
                    updateState()
                }

                override fun onError(message: String) {
                    _viewState = HeroesFragmentState.ShowLoadDataError(message)
                    updateState()
                }
            })
        }
    }

    private fun updateState() {
        _viewStateObserver.value = _viewState
    }


    fun filterData(enteredString: String) {

    }

    fun onHeroItemClick(id: Int) {
        if (_viewState is HeroesFragmentState.ShowLoadedData) {
            val heroes = (_viewState as HeroesFragmentState.ShowLoadedData).heroes
            val position = getPositionById(heroes, id)
            if (position != -1)
                _viewState = HeroesFragmentState.ShowHeroInfo(heroes, position)
        }
    }

    private fun getPositionById(heroes: List<Hero>, id: Int): Int {
        for (i in heroes.indices)
            if (heroes[i].id == id)
                return i
        return -1
    }

    fun onBackClick() {

    }

    val stateObserver: LiveData<HeroesFragmentState> = _viewStateObserver
}