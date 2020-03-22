package com.test.attractgroup.heroes.ui

import com.test.attractgroup.heroes.data.model.Hero

sealed class HeroesFragmentState {
    object Loading: HeroesFragmentState()
    class LoadDataSuccess(val heroes: List<Hero>): HeroesFragmentState()
    class LoadDataFailed(val message: String): HeroesFragmentState()
    class ShowHeroInfo(val heroes: List<Hero>, val startPosition: Int): HeroesFragmentState()
}