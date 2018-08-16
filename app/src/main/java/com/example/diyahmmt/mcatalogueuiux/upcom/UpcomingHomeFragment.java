package com.example.diyahmmt.mcatalogueuiux.upcom;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diyahmmt.mcatalogueuiux.BuildConfig;
import com.example.diyahmmt.mcatalogueuiux.R;
import com.example.diyahmmt.mcatalogueuiux.nowandupadapter.AdapterNowAndUpcoming;
import com.example.diyahmmt.mcatalogueuiux.nowandupadapter.ItemMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class UpcomingHomeFragment extends Fragment {
    private RecyclerView rcvCategory;
    private View view;
    private List<ItemMovies> moviesItemList;
    private RecyclerView.Adapter adapter;
    private static final String URL_API = BuildConfig.URL_MOVIE + "/upcoming?api_key=" + BuildConfig.API_KEY_MOVIE + "&language=en-US";

    public UpcomingHomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_now_play_home, container, false);
        rcvCategory = (RecyclerView) view.findViewById(R.id.rcvCategory);
        rcvCategory.setHasFixedSize(true);
        rcvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesItemList = new ArrayList<>();
        data();
        return view;
    }
    private void data() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Waiting ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ItemMovies itemMovies = new ItemMovies();
                        JSONObject movie = jsonArray.getJSONObject(i);
                        itemMovies.setTitleMovie(movie.getString("title"));
                        itemMovies.setDescriptionMovie(movie.getString("overview"));
                        itemMovies.setDateMovie(movie.getString("release_date"));
                        itemMovies.setImageMovie(movie.getString("poster_path"));
                        itemMovies.setRate_countMovie(movie.getString("vote_count"));
                        itemMovies.setRateMovie(movie.getString("vote_average"));
                        moviesItemList.add(itemMovies);
                    }
                    adapter = new AdapterNowAndUpcoming(moviesItemList, getActivity());
                    rcvCategory.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                data();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
