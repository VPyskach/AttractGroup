package com.test.attractgroup.heroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.attractgroup.R
import com.test.attractgroup.common.hide
import com.test.attractgroup.common.show
import com.test.attractgroup.heroes.data.model.Hero
import com.test.attractgroup.heroes.ui.recycler.HeroItemClickListener
import com.test.attractgroup.heroes.ui.recycler.HeroesRecyclerAdapter
import com.test.attractgroup.heroes.ui.view_model.HeroesViewModel
import kotlinx.android.synthetic.main.fragment_heroes.*

class HeroesFragment : Fragment(), HeroItemClickListener{

    private lateinit var heroesViewModel: HeroesViewModel
    private var heroesRecyclerAdapter: HeroesRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_heroes, container, false)
        heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)
        heroesViewModel.state.observe(this, Observer {
            renderState(it)
        })
        heroesViewModel.refreshData()
        return root
    }

    private fun renderState(state: HeroesFragmentState){
        when (state){
            is HeroesFragmentState.Loading -> showLoading()

            is HeroesFragmentState.ShowLoadedData -> showHeroesList(state.heroes)

            is HeroesFragmentState.ShowLoadDataError -> showLoadingError(state.message)

            is HeroesFragmentState.ShowFilteredData -> showFilteredData(state.heroes)

            is HeroesFragmentState.ShowHeroInfo -> showHeroInfo(state.heroes, state.position)
        }
    }

    override fun onHeroClick(id: Int) {

    }

    private fun showHeroesList(data: List<Hero>){
        vpHeroInfo.hide()
        progressBar.hide()
        llHeroesList.show()
        rvHeroesList.layoutManager = LinearLayoutManager(context)
        heroesRecyclerAdapter = HeroesRecyclerAdapter(this)
        heroesRecyclerAdapter?.setData(data)
        rvHeroesList.adapter = heroesRecyclerAdapter
    }

    private fun showFilteredData(data: List<Hero>){
        vpHeroInfo.hide()
        progressBar.hide()
        llHeroesList.show()
        heroesRecyclerAdapter?.updateData(data)
    }

    private fun showHeroInfo(data: List<Hero>, position: Int){
        progressBar.hide()
        llHeroesList.hide()
        vpHeroInfo.show()

    }

    private fun showLoadingError(message: String){
        vpHeroInfo.hide()
        llHeroesList.hide()
        progressBar.hide()

    }

    private fun showLoading(){
        vpHeroInfo.hide()
        llHeroesList.hide()
        progressBar.show()
    }


}