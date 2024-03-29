package com.archer.amaterasu.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.archer.amaterasu.R;
import com.archer.amaterasu.common.BaseFragment;
import com.archer.amaterasu.common.BasePresenter;
import com.archer.amaterasu.domain.ListSong;
import com.archer.amaterasu.domain.Song;
import com.archer.amaterasu.ui.adapter.FavoritesSongListAdapter;
import com.archer.amaterasu.utils.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesListSongsFragment extends BaseFragment {

    private static final int NUM_COLS = 2;
    private static final String LOG_TAG = FavoritesListSongsFragment.class.getSimpleName();
    @Bind(R.id.recycler_favorites_songs)
    RecyclerView recyclerList;

    FavoritesSongListAdapter adapter;

    public FavoritesListSongsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FavoritesSongListAdapter(CONTEXT);
    }

    @Override
    public void onResume() {
        super.onResume();

//        Al cargar las listas desde realm nos muestra la parte de la imagen
//        en blanco porq solo ponemos la imagen al adaptador, nunca ponemos
//        una imagen a la lista y la guardamos en Realm, por eso debemos
//        agregar un campo de imagen a las listas de canciones y en el holder
//        y adapter con setCover implementar la misma logica.

        RealmResults<ListSong> results = getRealm().where(ListSong.class).findAll();
        adapter.addAll(results);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(CONTEXT, view);
        setHasOptionsMenu(true);
        setupListConfiguration();
    }

    private void setupListConfiguration(){
        recyclerList.setLayoutManager(new GridLayoutManager(CONTEXT, NUM_COLS));
        recyclerList.setAdapter(adapter);
        recyclerList.addItemDecoration(new ItemOffsetDecoration(CONTEXT, R.integer.artist_offset));
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_favorites_list_songs;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_favorites_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.add_list:

                new MaterialDialog.Builder(CONTEXT)
                        .title(R.string.title_activity_song_detail)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(R.string.input_add_list_hint, R.string.input_add_list_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                final ListSong newList = new ListSong();
                                newList.setId(UUID.randomUUID().toString());
                                newList.setName(input.toString());
                                newList.setListSize(0);
                                adapter.addItem(newList);
                                getRealm().executeTransaction(new Realm.Transaction(){
                                    @Override
                                    public void execute(Realm realm) {
//                                        newList.setSongs(null);
                                        realm.copyToRealmOrUpdate(newList);
                                    }
                                });
                            }
                        }).show();
                return true;
            case R.id.delete_list:
                Toast.makeText(CONTEXT, "Items borrados", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void log(){
        RealmResults<ListSong> results = getRealm().where(ListSong.class).findAll();
        Log.e(LOG_TAG, results + "\n");
    }

//    public void setDummieContent(){
//        ArrayList<ListSong> dummieList = new ArrayList<>();
//        ArrayList<Song> dummieSongsOne = new ArrayList<>();
//        ArrayList<Song> dummieSongsTwo = new ArrayList<>();
//        ArrayList<Song> dummieSongsThree = new ArrayList<>();
//        ArrayList<Song> dummieSongsFour = new ArrayList<>();
//
//        ListSong listOne = new ListSong();
//        ListSong listTwo = new ListSong();
//        ListSong listThree = new ListSong();
//        ListSong listFour = new ListSong();
//
//        Song wolf = new Song();
//        wolf.setSongImageSmall("http://t02.deviantart.net/RdkCoVAytgSJkBe3zKhaTBxZJ_s=/300x200/filters:fixed_height(100,100):origin()/pre14/0ca6/th/pre/f/2009/329/5/7/spice_and_wolf_by_chvampaiiaxinuzuka.jpg");
//        Song sao = new Song();
//        sao.setSongImageSmall("https://v.cdn.vine.co/r/avatars/D5311B62C71188549169414316032_32d784aa0ae.1.5.jpg?versionId=SRP81.GMiY1UBZ3zemFhTG_2FB.3wxp5");
//        Song fate = new Song();
//        fate.setSongImageSmall("http://img1.ak.crunchyroll.com/i/spire2/54014d917b61c6f9610ba1ac885f58641411919139_large.png");
//
//        dummieSongsOne.add(wolf);
//        dummieSongsOne.add(sao);
//        dummieSongsOne.add(fate);
//
//        dummieSongsTwo.add(sao);
//        dummieSongsTwo.add(wolf);
//        dummieSongsTwo.add(fate);
//
//        dummieSongsThree.add(fate);
//        dummieSongsThree.add(wolf);
//        dummieSongsThree.add(sao);
//
//        dummieSongsFour.add(wolf);
//        dummieSongsFour.add(fate);
//        dummieSongsFour.add(sao);
//
//        listOne.setName("Best Anime Openings");
//        listOne.setSongs(dummieSongsOne);
//
//        listTwo.setName("Best off Eai Aoi");
//        listTwo.setSongs(dummieSongsTwo);
//
//        listThree.setName("Epic fight music");
//        listThree.setSongs(dummieSongsThree);
//
//        listFour.setName("Twitch stream music");
//        listFour.setSongs(dummieSongsFour);
//
//        dummieList.add(listOne);
//        dummieList.add(listTwo);
//        dummieList.add(listThree);
//        dummieList.add(listFour);
//
//        dummieList.add(listTwo);
//        dummieList.add(listOne);
//        dummieList.add(listFour);
//        dummieList.add(listThree);
//
//        dummieList.add(listOne);
//        dummieList.add(listTwo);
//
//        adapter.addAll(dummieList);
//    }
}


















