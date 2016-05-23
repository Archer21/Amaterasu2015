package com.archer.amaterasu.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.amaterasu.R;
import com.archer.amaterasu.common.BaseFragment;
import com.archer.amaterasu.common.BasePresenter;
import com.archer.amaterasu.domain.Song;
import com.facebook.imagepipeline.request.Postprocessor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongDetailFragment extends BaseFragment {

    @Bind(R.id.song_background)
    ImageView songBackground;
    @Bind(R.id.song_title)
    TextView songTitle;
    @Bind(R.id.song_image)
    ImageView songImage;
    @Bind(R.id.artist_name)
    TextView artistName;

    Realm realm;
    Song  currentSong;

    public SongDetailFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_song_detail;
    }

    @Override
    public void onResume() {
        super.onResume();
        configSong();

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getActivity(), view);

    }

    public void configSong(){
        currentSong = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("SONG"));
        setSongBackground(currentSong.getSongImageMedium());
        setSongImage(currentSong.getSongImageSmall());
        setSongTitle(currentSong.getSongTitle());
        setSongArtistName(currentSong.getSongArtist());
    }

    public void setSongBackground(String urlImage){
        Picasso.with(CONTEXT)
                .load(urlImage)
                .placeholder(R.drawable.placeholder_image)
                .transform(new BlurTransformation(CONTEXT, 25))
                .into(songBackground);
    }

    public void setSongImage(String urlImage){
        Picasso.with(CONTEXT)
                .load(urlImage)
                .placeholder(R.drawable.placeholder_image)
                .into(songImage);
    }

    public void setSongTitle(String title){
        songTitle.setText(title);
    }

    public void setSongArtistName(String name){
        artistName.setText(name);
    }
}














