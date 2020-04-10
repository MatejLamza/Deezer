package com.example.views.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import nl.psdcompany.duonavigationdrawer.views.DuoOptionView

class DrawerMenuAdapter:BaseAdapter() {

    private var menuOptions:ArrayList<String> = arrayListOf()
    private var optionViews: ArrayList<DuoOptionView> = arrayListOf()

    fun setOptions(mOptions:ArrayList<String>){
        menuOptions = mOptions
    }

    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View {
        var currentOption = menuOptions[pos]
        var optionView:DuoOptionView

        if(view == null){
            optionView = DuoOptionView(parent!!.context)
        } else{
            optionView = view as DuoOptionView
        }

        optionView.bind(currentOption)
        optionViews.add(optionView)

        return optionView
    }

    override fun getItem(pos: Int): Any = menuOptions[pos]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = menuOptions.size
}