package com.test.attractgroup

import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.GravityCompat
import com.test.attractgroup.common.isTablet
import com.test.attractgroup.heroes.ui.HeroesFragment

class MainActivity : AppCompatActivity() {


    private lateinit var _toolbar: Toolbar
    private lateinit var _appBarIcon: ImageView
    private lateinit var _appBarEditText: EditText
    private lateinit var _navigationDrawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationMenu()
        initAppBar()
        initContent()

    }

    private fun initNavigationMenu() {
        if (!isTablet()) {
            _navigationDrawer = findViewById(R.id.drawer_layout)
        }
    }

    private fun initAppBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        if (!isTablet())
            toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_menu_open_white_24dp)
        toolbar.setNavigationOnClickListener { toolbarIconClickListener() }
    }

    private fun initContent(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, HeroesFragment())
            .commit()
    }

    private fun toolbarIconClickListener() {
        if (!isTablet()) {
            _navigationDrawer.openDrawer(GravityCompat.START)
        }
    }
}
