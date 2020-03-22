package com.test.attractgroup.heroes.data

import com.test.attractgroup.heroes.data.model.Hero

interface IHeroesRepository {

    fun getHeroes(callback: LoadHeroesCallback)

    interface LoadHeroesCallback {
        fun onSuccess(data: List<Hero>)
        fun onError(message: String)
    }
}