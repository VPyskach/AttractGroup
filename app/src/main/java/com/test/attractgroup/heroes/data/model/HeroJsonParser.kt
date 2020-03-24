package com.test.attractgroup.heroes.data.model

import android.util.Log
import org.json.JSONArray
import java.lang.Exception

class HeroJsonParser(val str: String) {
    private val TAG = HeroJsonParser::class.java.simpleName

    fun getHeroes(): List<Hero>? {
        try {
            val result = ArrayList<Hero>()
            val heroes = JSONArray(str)
            for (i in 0 until heroes.length()) {
                val heroJson = heroes.getJSONObject(i)
                val id = heroJson.getInt("itemId")
                val name = heroJson.getString("name")
                val image = heroJson.getString("image")
                val description = heroJson.getString("description")
                val time = heroJson.getLong("time")
                result.add(Hero(id, name, image, description, time))
            }
            return result
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing json: ${e.message}")
        }

        return null
    }
}