package com.example.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.deezer.R
import com.example.deezer.base.ScopedFragment
import com.example.deezer.viewmodels.PlaylistViewModel
import com.example.deezer.viewmodels.factories.PlaylistVMFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedFragment:ScopedFragment() {

    @Inject
    lateinit var factory: PlaylistVMFactory

    private lateinit var vm: PlaylistViewModel

    companion object{
        fun newInstance():FeedFragment{
            return FeedFragment()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_feed,container,false)
        vm = ViewModelProvider(this,factory).get(PlaylistViewModel::class.java)

        showData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun showData(){
        vm.livePlaylists.observe(viewLifecycleOwner, Observer {
            setImage(ivPlaylist1, it.data!![0].picture!!)
            setImage(ivPlaylist2,it.data!![1].picture!!)
        })

        vm.liveGenre.observe(viewLifecycleOwner, Observer {
            setImage(ivGenre1,it[0].picture!!)
            tvGenre1.text = it[0].name
            setImage(ivGenre2,it[1].picture!!)
            tvGenre2.text = it[1].name
        })
    }

    private fun setImage(imageView: ImageView?, picture: String) {
        Glide.with(this)
            .load(picture)
            .into(imageView!!)
    }
}