package com.example.diyahmmt.mcatalogueuiux.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diyahmmt.mcatalogueuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovActivity extends AppCompatActivity {

    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_IMAGE_JPG = "extra_image_jpg";
    public static String EXTRA_RATE_COUNT = "extra_rate_count";
    public static String EXTRA_RATE = "extra_rate";

    private TextView tvTitle, tvOverview, tvReleaseDate, tvRating;
    private ImageView imgPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mov);
        tvTitle = (TextView)findViewById(R.id.titledet);
        imgPoster = (ImageView)findViewById(R.id.imgPoster);
        tvOverview = (TextView)findViewById(R.id.detOverview);
        tvReleaseDate = (TextView)findViewById(R.id.detDate);
        tvRating = (TextView)findViewById(R.id.detailRate);

        String title1 = getIntent().getStringExtra(EXTRA_TITLE);
        String image_jpg = getIntent().getStringExtra(EXTRA_IMAGE_JPG);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String rating = getIntent().getStringExtra(EXTRA_RATE);
        String rating_count = getIntent().getStringExtra(EXTRA_RATE_COUNT);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTitle.setText(title1);
        tvOverview.setText(overview);
        tvRating.setText(rating_count+" / ("+rating+")");
        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/original/"+image_jpg).into(imgPoster);

    }
}
