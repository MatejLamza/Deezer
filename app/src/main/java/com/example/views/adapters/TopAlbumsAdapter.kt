package com.example.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezer.R
import com.example.models.domain.Album.AlbumData
import kotlinx.android.synthetic.main.item_album.view.*

class TopAlbumsAdapter : RecyclerView.Adapter<TopAlbumsAdapter.TopAlbumsViewHolder>() {

    private var _albums: ArrayList<AlbumData> = arrayListOf()

    fun loadAlbums(mAlbums:ArrayList<AlbumData>){
        _albums = mAlbums
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumsViewHolder =
        TopAlbumsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))

    override fun getItemCount(): Int = _albums.size

    override fun onBindViewHolder(holder: TopAlbumsViewHolder, position: Int) {
        holder.album = _albums[position]

        Glide.with(holder.itemView.context)
            .load(holder.album!!.cover)
            .into(holder.itemView.ivAlbumCover)
    }

    class TopAlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var album: AlbumData? = null
            set(value) {
                field = value
                itemView.tvAlbumName.text = album!!.title
                itemView.tvAlbumArtist.text = album!!.artist.name
            }
    }
}