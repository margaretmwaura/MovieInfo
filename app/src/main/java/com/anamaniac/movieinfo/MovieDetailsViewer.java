package com.anamaniac.movieinfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.http.Url;

public class MovieDetailsViewer extends AppCompatActivity {

    List<TvDataModel> movie;

    public void setMovie(List<TvDataModel> movie) {
        this.movie = movie;
    }

    public Handler Seekbackground = new Handler();
    public String PosterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_viewer);

        TvDataModel intent = getIntent().getParcelableExtra("Movie");

        String Title = intent.title;
        String OverView = intent.overview;
        String ReleaseDate = intent.release_date;
        Number Popularity = intent.popularity;
        Boolean Video = intent.video;
        Boolean Adult = intent.adult;
        String OriginalLanguage = intent.original_language;
        String OriginalTitle = intent.original_title;

        PosterPath = getResources().getString(R.string.ImageUrl) + intent.backdrop_path;
        Log.d("PassedMovie", "This is the mvoie that has been passed " + PosterPath);
        TextView title = findViewById(R.id.title);
        TextView Description = findViewById(R.id.Description);
        //ImageView imageView = findViewById(R.id.PosterViewLayout);
        title.setText(Title);
        Description.setText(OverView);

        Picasso.get().load(PosterPath).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ImageView imageView = findViewById(R.id.PosterViewLayout);
                imageView.setImageBitmap(bitmap);
                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable Palette palette) {
                        DominantColor = palette.getVibrantColor(getResources().getColor(R.color.Grey));

                    }
                });
                LinearLayout linearLayout = findViewById(R.id.LayoutView);
                linearLayout.setBackgroundColor(DominantColor);

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("failed bitmap","the bitmap has failed" + e.getMessage());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("bitmap Loading","The bitmap is downloading");
                ImageView imageView = findViewById(R.id.PosterViewLayout);
                Picasso.get().load(PosterPath).into(imageView);

                //imageView.setImageDrawable(placeHolderDrawable);

            }
        });
    }

    public int DominantColor;

    public void seekUpdate() {

        Log.d("SeekUpdate","Seek update has been called");
        ImageView imageView = findViewById(R.id.PosterViewLayout);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                DominantColor = palette.getVibrantColor(getResources().getColor(R.color.Grey));

            }
        });
        LinearLayout linearLayout = findViewById(R.id.LayoutView);
        linearLayout.setBackgroundColor(DominantColor);
        Seekbackground.postDelayed(run, 100);


    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            Log.d("SeekUpdateRun", "Seek update has been called from the run");
            seekUpdate();
        }
    };




    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OnPause","On pause has nbeen called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume","ONrESUME HAS BEEN CALLED");

    }
}
