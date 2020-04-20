package com.example.views.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deezer.R
import com.example.deezer.viewmodels.TopAlbumsViewModel
import com.example.deezer.viewmodels.factories.TopAlbumsVMFactory
import com.example.internals.AutoClearedValue
import com.example.models.domain.Album.AlbumData
import com.example.views.AlbumActivity
import com.example.views.adapters.TopAlbumsAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


const val EXTRA_ALBUM = "com.example.views.fragments.extra_album"


class AlbumsFragment:Fragment(),TopAlbumsAdapter.OnAlbumClickListener {

    @Inject
    lateinit var factory:TopAlbumsVMFactory
    private val vm:TopAlbumsViewModel by lazy {
        ViewModelProvider(this,factory).get(TopAlbumsViewModel::class.java)
    }

    private var albums:ArrayList<AlbumData> = arrayListOf()
    private var adapter by AutoClearedValue<TopAlbumsAdapter>()

    companion object{
        fun newInstance():AlbumsFragment{
            return AlbumsFragment()
        }
    }

    override fun onAlbumClicked(album: AlbumData) {
        val intent = Intent(activity?.applicationContext,AlbumActivity::class.java)
        intent.putExtra(EXTRA_ALBUM,album)
        startActivity(intent)
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
        val view = inflater.inflate(R.layout.fragment_albums,container,false)
        initRecylcerView(view)

        vm.albums.observe(viewLifecycleOwner, Observer {
            albums = it.data as ArrayList<AlbumData>
            adapter.loadAlbums(albums)
            adapter.notifyDataSetChanged()
        })

        return view
    }

    private fun initRecylcerView(view:View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerAlbums)
        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(activity!!.applicationContext)
        adapter = TopAlbumsAdapter()
        adapter.setOnAlbumClickListener(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }
}