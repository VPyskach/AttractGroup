package com.test.attractgroup.heroes.data

import com.test.attractgroup.api_services.HttpRequest
import com.test.attractgroup.api_services.HttpRequestCallback
import com.test.attractgroup.heroes.data.model.HeroJsonParser


class HeroesRepository : IHeroesRepository {

    override fun getHeroes(callback: IHeroesRepository.LoadHeroesCallback?) {
        HttpRequest().makeAsyncGetRequest(" http://test.php-cd.attractgroup.com/test.json", object : HttpRequestCallback {
            override fun onSuccess(res: String) {
                val heroes = HeroJsonParser(res).getHeroes()
                if (heroes == null) {
                    callback?.onError("Error parsing data")
                    return;
                }

                if (heroes.isEmpty()) {
                    callback?.onError("Heroes list is empty")
                    return;
                }

                callback?.onSuccess(heroes)
            }

            override fun onError(message: String) {
                callback?.onError(message)
            }
        })
    }


}