package com.test.attractgroup.heroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.attractgroup.R
import com.test.attractgroup.heroes.data.model.Hero

class HeroesFragment : Fragment() {

    private lateinit var heroesViewModel: HeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)
        heroesViewModel.refreshData()
        heroesViewModel.state.observe(this, Observer {
            renderState(it)
        })
        return root
    }

    private fun renderState(state: HeroesFragmentState){
        when (state){
            is HeroesFragmentState.Loading -> showLoading()

            is HeroesFragmentState.LoadDataSuccess -> showHeroesList(state.heroes)

            is HeroesFragmentState.LoadDataFailed -> showLoadingError(state.message)

            is HeroesFragmentState.ShowHeroInfo -> showHeroInfo(state.heroes, state.position)
        }
    }

    private fun showHeroesList(data: List<Hero>){

    }

    private fun showHeroInfo(data: List<Hero>, position: Int){

    }

    private fun showLoadingError(message: String){

    }

    private fun showLoading(){

    }


}