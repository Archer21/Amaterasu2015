package com.archer.amaterasu.ui.holder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.archer.amaterasu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TopArtistViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.top_artist_image)
    SimpleDraweeView artistImage;
    @Bind(R.id.top_artist_name)
    TextView artistName;
    @Bind(R.id.top_artist_votes)
    TextView artistVotes;
    @Bind(R.id.top_artist_rating)
    TextView artistRating;

    public TopArtistViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setArtistImage(String urlImage){
        Uri uri = Uri.parse(urlImage);
        artistImage.setImageURI(uri);
    }

    public void setArtistName(String name){
        this.artistName.setText(name);
    }

    public void setArtistVotes(int votes) {
        String artistVotes = String.valueOf(votes);
        this.artistVotes.setText(artistVotes);
    }

    public void setArtistRating(float rating) {
        String artistRating = String.valueOf(rating);
        this.artistRating.setText(artistRating);
    }
}


















