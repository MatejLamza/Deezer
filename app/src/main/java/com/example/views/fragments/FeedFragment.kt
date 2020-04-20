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
import com.example.helpers.UIHelper
import com.example.helpers.UIHelper.Companion.setImage
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject

class FeedFragment:ScopedFragment() {

    @Inject
    lateinit var factory: PlaylistVMFactory

    private val vm: PlaylistViewModel by lazy {
        ViewModelProvider(this,factory).get(PlaylistViewModel::class.java)
    }

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

        showData()

        return view
    }

    private fun showData(){
        vm.livePlaylists.observe(viewLifecycleOwner, Observer {
            setImage(activity!!.applicationContext,ivPlaylist1, it.data!![0].picture!!)
            setImage(activity!!.applicationContext,ivPlaylist2,it.data!![1].picture!!)
        })

        vm.liveGenre.observe(viewLifecycleOwner, Observer {
            setImage(activity!!.applicationContext,ivGenre1,it[0].picture!!)
            tvGenre1.text = it[0].name
            setImage(activity!!.applicationContext,ivGenre2,it[1].picture!!)
            tvGenre2.text = it[1].name
        })
    }


}