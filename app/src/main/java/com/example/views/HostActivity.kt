package com.example.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.deezer.R
import com.example.views.adapters.DrawerMenuAdapter
import com.example.views.fragments.AlbumsFragment
import com.example.views.fragments.FeedFragment
import kotlinx.android.synthetic.main.activity_host.*
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

class HostActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {

    private lateinit var drawerLayout: DuoDrawerLayout
    private lateinit var menuView: DuoMenuView

    private var menuAdapter = DrawerMenuAdapter()
    private var options: List<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        options = resources.getStringArray(R.array.drawerMenuOptions).toList()

        drawerLayout = findViewById(R.id.drawer)
        menuView = drawerLayout.menuView as DuoMenuView

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        goToFragment(FeedFragment.newInstance(),false)

        initMenu()
        handleDrawer()
    }

    private fun initMenu() {
        menuAdapter.setOptions(options as ArrayList<String>)
        menuView.setOnMenuClickListener(this)
        menuView.adapter = menuAdapter
    }


    private fun handleDrawer() {
        val drawerToggle =
            DuoDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.ClosedDrawer)
        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {
        title = options[position]

        when(position){
            0 -> goToFragment(FeedFragment.newInstance(),false)
            1 -> goToFragment(AlbumsFragment.newInstance(),false)
        }

        drawerLayout.closeDrawer()
    }

    private fun goToFragment(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.main_container, fragment).commit()
    }

    override fun onHeaderClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFooterClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}