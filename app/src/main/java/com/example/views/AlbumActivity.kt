package com.example.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deezer.R
import com.example.deezer.utils.MyUtils
import com.example.deezer.viewmodels.AlbumTracksViewModel
import com.example.deezer.viewmodels.factories.AlbumTracksVMFactory
import com.example.helpers.UIHelper
import com.example.internals.AutoClearedValue
import com.example.models.domain.Album.AlbumData
import com.example.models.domain.AlbumTracks
import com.example.views.adapters.AlbumTracksAdapter
import com.example.views.fragments.EXTRA_ALBUM
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_album.*
import javax.inject.Inject

class AlbumActivity : AppCompatActivity(), AlbumTracksAdapter.OnPreviewClicked {


    @Inject
    lateinit var factory: AlbumTracksVMFactory
    private val vm: AlbumTracksViewModel by lazy {
        ViewModelProvider(this, factory).get(AlbumTracksViewModel::class.java)
    }

    private var tracks: ArrayList<AlbumTracks.AlbumTracksData> = arrayListOf()
    private var adapter: AlbumTracksAdapter? = null

    private lateinit var recivedAlbum: AlbumData

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        getDataFromBundle()
        initRecylcerView()

        recivedAlbum.id?.let { vm.getAlbumTracks(it) }

        setViews()

        vm.liveAlbumTracks.observe(this, Observer {
            tracks = it.tracks as ArrayList<AlbumTracks.AlbumTracksData>
            adapter!!.loadTracks(tracks)
            adapter!!.notifyDataSetChanged()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

    override fun onClickPreview(albumTrack: AlbumTracks.AlbumTracksData) {
        MyUtils.openWebBrowser(albumTrack.preview, this)
    }

    private fun setViews() {
        UIHelper.setImage(this, ivAlbumImage, recivedAlbum.cover!!)
        ivAlbumItemName.text = recivedAlbum.title
        ivAlbumItemArtist.text = recivedAlbum.artist!!.name
    }

    private fun getDataFromBundle() {
        if (intent.hasExtra(EXTRA_ALBUM)) {
            recivedAlbum = intent.getSerializableExtra(EXTRA_ALBUM) as AlbumData
        }
    }

    private fun initRecylcerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rec_Tracks)
        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(this)
        adapter = AlbumTracksAdapter()
        adapter!!.setListener(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }
}