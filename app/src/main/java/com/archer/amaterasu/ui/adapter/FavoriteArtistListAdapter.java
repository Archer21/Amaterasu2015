package com.archer.amaterasu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.amaterasu.R;
import com.archer.amaterasu.domain.Artist;
import com.archer.amaterasu.ui.holder.FavoritesListArtistsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FavoriteArtistListAdapter extends RecyclerView.Adapter<FavoritesListArtistsViewHolder>{

    Context context;
    List<Artist> favoriteArtistList;

    public FavoriteArtistListAdapter(Context context) {
        this.context = context;
        this.favoriteArtistList = new ArrayList<>();
    }

    public void addAll(List<Artist> artists) {
        if (artists == null)
            throw new NullPointerException("The items cannot be null");

        this.favoriteArtistList = artists;
        notifyItemRangeInserted(getItemCount() - 1, artists.size());
    }

    @Override
    public FavoritesListArtistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_favorite_list_artist_row, parent, false);
        return new FavoritesListArtistsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoritesListArtistsViewHolder holder, int position) {
        Artist currentArtist = favoriteArtistList.get(position);
        holder.setArtistImage(currentArtist.getPhoto());
    }

    @Override
    public int getItemCount() {
        return favoriteArtistList.size();
    }
}















