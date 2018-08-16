package com.example.diyahmmt.mcatalogueuiux.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.diyahmmt.mcatalogueuiux.R;


/**
 * A simple {@link Fragment} subclass.

 */
public class FragmentSearch extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<ItemMovies>> {
   private EditText edtTitle;
   private ImageView imgPoster;
   private ListView listView;
   private Button btnSearch;
   private AdapterMovie adapterMovie;
   private View view;
   private static final String EXTRA_MOVIES = "EXTRA_MOVIES";

    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_search, container, false);
        adapterMovie = new AdapterMovie(getActivity());
        adapterMovie.notifyDataSetChanged();
        listView = (ListView) view.findViewById(R.id.lsvMovie);
        listView.setAdapter(adapterMovie);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMovies itemMovies = (ItemMovies) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DetailMovActivity.class);
                intent.putExtra(DetailMovActivity.EXTRA_TITLE, itemMovies.getMovTitle());
                intent.putExtra(DetailMovActivity.EXTRA_OVERVIEW, itemMovies.getMovDeskripsi());
                intent.putExtra(DetailMovActivity.EXTRA_RELEASE_DATE, itemMovies.getMovDate());
                intent.putExtra(DetailMovActivity.EXTRA_IMAGE_JPG, itemMovies.getMovImage());
                intent.putExtra(DetailMovActivity.EXTRA_RATE_COUNT, itemMovies.getMovRateCount());
                intent.putExtra(DetailMovActivity.EXTRA_RATE, itemMovies.getMovRate());
                startActivity(intent);
            }
        });
        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        imgPoster = (ImageView) view.findViewById(R.id.imgPos);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(listenerMovie);
        String title_movie = edtTitle.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIES, title_movie);
        getLoaderManager().initLoader(0, bundle, FragmentSearch.this);
        return view;
    }


    @Override
    public Loader<ArrayList<ItemMovies>> onCreateLoader(int id, Bundle args) {
        String title_movie = "";
        if (args != null){
            title_movie = args.getString(EXTRA_MOVIES);
        }
        return new AsyncTaskLoadMovie(getActivity(), title_movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ItemMovies>> loader, ArrayList<ItemMovies> data) {
        adapterMovie.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ItemMovies>> loader) {
        adapterMovie.setData(null);
    }

    View.OnClickListener listenerMovie = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String judul_movie = edtTitle.getText().toString();
            if(TextUtils.isEmpty(judul_movie)){
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIES, judul_movie);
            getLoaderManager().restartLoader(0, bundle, FragmentSearch.this);
        }
    };


}
