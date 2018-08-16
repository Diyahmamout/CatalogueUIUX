package com.example.diyahmmt.mcatalogueuiux.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diyahmmt.mcatalogueuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ACER on 29-07-2018.
 */

public class AdapterMovie extends BaseAdapter {
    private ArrayList<ItemMovies> movieData = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private String foverview;

    public AdapterMovie(Context context) {
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<ItemMovies> items){
        movieData = items;
        notifyDataSetChanged();
    }

    public void addItem(final ItemMovies item) {
        movieData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        movieData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (movieData == null) return 0;
        return movieData.size();
    }

    @Override
    public ItemMovies getItem(int position) {
        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDeskripsi;
        TextView tvDate;
        ImageView imgPoster;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_row_movies, null);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            holder.tvDeskripsi = (TextView)convertView.findViewById(R.id.tvDeskripsi);
            holder.tvDate = (TextView)convertView.findViewById(R.id.tvDate);
            holder.imgPoster = (ImageView)convertView.findViewById(R.id.imgPos);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(movieData.get(position).getMovTitle());

        String overview = movieData.get(position).getMovDeskripsi();

        if(TextUtils.isEmpty(overview)){
            foverview = "-";
        }else{
            foverview = overview;
        }
        holder.tvDeskripsi.setText(foverview);

        String rDate = movieData.get(position).getMovDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(rDate);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String date_of_release = new_date_format.format(date);
            holder.tvDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/"+movieData.get(position).getMovImage()).
                placeholder(context.getResources().getDrawable(R.drawable.ic_photo_library_black_24dp)).
                error(context.getResources().getDrawable(R.drawable.ic_photo_library_black_24dp)).into(holder.imgPoster);
        return convertView;
    }
}
