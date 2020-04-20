package com.example.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deezer.R
import com.example.models.domain.AlbumTracks.AlbumTracksData
import kotlinx.android.synthetic.main.item_album_track.view.*

class AlbumTracksAdapter:RecyclerView.Adapter<AlbumTracksAdapter.AlbumTracksViewHolder>() {

    private var _tracks:ArrayList<AlbumTracksData> = arrayListOf()
    private lateinit var _listener : OnPreviewClicked

    fun setListener(listener:OnPreviewClicked){
        _listener = listener
    }

    fun loadTracks(tracks:ArrayList<AlbumTracksData>){
        _tracks = tracks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumTracksViewHolder =
        AlbumTracksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_track,parent,false))

    override fun getItemCount(): Int = _tracks.size

    override fun onBindViewHolder(holder: AlbumTracksViewHolder, position: Int) {
        holder.albumTracks = _tracks[position]

        holder.itemView.btnPreview.setOnClickListener {
            _listener.onClickPreview(_tracks[position])
        }

    }

    class AlbumTracksViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var albumTracks:AlbumTracksData? = null
        set(value) {
            field = value
            itemView.IvalbumTrackName.text = albumTracks!!.title
        }
    }

    interface OnPreviewClicked{
        fun onClickPreview(albumTrack:AlbumTracksData)
    }
}