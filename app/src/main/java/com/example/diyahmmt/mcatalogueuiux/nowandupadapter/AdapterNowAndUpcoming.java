package com.example.diyahmmt.mcatalogueuiux.nowandupadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.diyahmmt.mcatalogueuiux.R;
import com.example.diyahmmt.mcatalogueuiux.search.DetailMovActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by ACER on 29-07-2018.
 */

public class AdapterNowAndUpcoming extends RecyclerView.Adapter<AdapterNowAndUpcoming.ViewHolder>{
    private List<ItemMovies> moviesList;
    private Context context;

    public AdapterNowAndUpcoming(List<ItemMovies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_now_playing, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ItemMovies movies = moviesList.get(position);
        holder.titleMovie.setText(movies.getTitleMovie());
        holder.overview.setText(movies.getDescriptionMovie());
        String dateRelease = movies.getDateMovie();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateRelease);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String dateReal = dateFormat1.format(date);
            holder.dateRelease.setText(dateReal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500/" + movies.getImageMovie())
                .into(holder.imgPoster);

        holder.btnFav.setOnClickListener(new ClickListener(position, new ClickListener.OnItemClick() {
            @Override
            public void onItemClicked(View view, int pos) {
                Toast.makeText(context, "Favorite " + movies.getTitleMovie(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnShare.setOnClickListener(new ClickListener(position, new ClickListener.OnItemClick() {
            @Override
            public void onItemClicked(View view, int pos) {
                Toast.makeText(context, "Share " + movies.getTitleMovie(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.cDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemMovies movieList = moviesList.get(position);
                Intent intent = new Intent(context, DetailMovActivity.class);
                intent.putExtra(DetailMovActivity.EXTRA_TITLE, movieList.getTitleMovie());
                intent.putExtra(DetailMovActivity.EXTRA_OVERVIEW, movieList.getDescriptionMovie());
                intent.putExtra(DetailMovActivity.EXTRA_IMAGE_JPG, movieList.getImageMovie());
                intent.putExtra(DetailMovActivity.EXTRA_RELEASE_DATE, movieList.getDateMovie());
                intent.putExtra(DetailMovActivity.EXTRA_RATE, movieList.getRateMovie());
                intent.putExtra(DetailMovActivity.EXTRA_RATE_COUNT, movieList.getRate_countMovie());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleMovie, overview, dateRelease;
        public ImageView imgPoster;
        public Button btnFav, btnShare;
        public LinearLayout cDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            titleMovie = (TextView) itemView.findViewById(R.id.tv_itemName);
            imgPoster = (ImageView) itemView.findViewById(R.id.imgItem);
            overview = (TextView) itemView.findViewById(R.id.tv_itemOverview);
            dateRelease = (TextView) itemView.findViewById(R.id.tv_dateRelease);
            btnFav = (Button) itemView.findViewById(R.id.btn_favorite);
            btnShare = (Button) itemView.findViewById(R.id.btn_share);
            cDetail = (LinearLayout) itemView.findViewById(R.id.c_movie);
        }
    }

}


