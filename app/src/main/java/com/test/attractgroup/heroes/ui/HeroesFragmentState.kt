package com.test.attractgroup.heroes.ui

import com.test.attractgroup.heroes.data.model.Hero

sealed class HeroesFragmentState {
    object Loading: HeroesFragmentState()
    class ShowLoadedData(val heroes: List<Hero>): HeroesFragmentState()
    class ShowFilteredData(val heroesFullList: List<Hero>, val heroesFilteredList: List<Hero>): HeroesFragmentState()
    class ShowLoadDataError(val message: String): HeroesFragmentState()
    class ShowHeroInfo(val heroes: List<Hero>, val position: Int): HeroesFragmentState()
}