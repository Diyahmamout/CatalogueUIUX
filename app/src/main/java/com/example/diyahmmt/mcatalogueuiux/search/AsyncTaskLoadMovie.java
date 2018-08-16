package com.example.diyahmmt.mcatalogueuiux.search;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.diyahmmt.mcatalogueuiux.BuildConfig;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ACER on 29-07-2018.
 */

public class AsyncTaskLoadMovie extends AsyncTaskLoader<ArrayList<ItemMovies>> {
    private ArrayList<ItemMovies> movieData;
    private boolean mResult = false;

    private String mTitleMovie;

    public AsyncTaskLoadMovie(final Context context, String titleMovie) {
        super(context);

        onContentChanged();
        this.mTitleMovie = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mResult)
            deliverResult(movieData);
    }

    public void deliverResult(ArrayList<ItemMovies> data) {
        movieData = data;
        mResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mResult) {
            onReleaseResources(movieData);
            movieData = null;
            mResult = false;
        }
    }

    private void onReleaseResources(ArrayList<ItemMovies> data) {
    }

    @Override
    public ArrayList<ItemMovies> loadInBackground() {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        final ArrayList<ItemMovies> movieItemss = new ArrayList<>();
        String url = BuildConfig.URL_SEARCH_MOVIE+"?api_key=" + BuildConfig.API_KEY_MOVIE + "&language=en-US&query=" + mTitleMovie;
        syncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String hasil = new String(responseBody);
                    JSONObject responseObject = new JSONObject(hasil);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject movie = list.getJSONObject(i);
                        ItemMovies movieItems = new ItemMovies(movie);
                        movieItemss.add(movieItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
        return movieItemss;
    }
}
