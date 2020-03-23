package com.test.attractgroup.heroes.data.model

import android.util.Log
import org.json.JSONArray
import java.lang.Exception

class HeroJsonParser(val str: String) {
    private val TAG = HeroJsonParser::class.java.simpleName

    fun getHeroes(): List<Hero>? {
        val result: ArrayList<Hero>? = null
        try {
            val heroes = JSONArray(str)
            for (hero in 0 until heroes.length()) {

            }
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing json: ${e.message}")
        }

        return result
    }
}