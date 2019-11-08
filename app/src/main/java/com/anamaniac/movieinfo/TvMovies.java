package com.anamaniac.movieinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvMovies extends AppCompatActivity implements CallBackgroundAuthorization.View, RecyclerViewInterface {
    public Retrofit retrofit;
    public  TvMoviesAdapter tvMoviesAdapter;
    public List<TvDataModel> Movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_movies);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        tvMoviesAdapter = new TvMoviesAdapter();
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(tvMoviesAdapter);

        String BASE_URL = "https://api.themoviedb.org/3/";

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ThyInterface toUse = retrofit.create(ThyInterface.class);
        Call<MovieInfo1> call = toUse.getPopularMovies(BuildConfig.ApiKey);
        CallBackgroundAuthorization callBackgroundAuthorization = new CallBackgroundAuthorization(this);
        callBackgroundAuthorization.GetPopularMovies(call,this);
        tvMoviesAdapter.setClickLIstener(this);


    }


    @Override
    public void display(Boolean login, String Expiry, String RequestToken) {

    }

    @Override
    public void RecyclerViewDisplay(List<TvDataModel> Results, Integer TotalResults) {
        this.Movies = Results;
        tvMoviesAdapter.setMovieList(Results);
        tvMoviesAdapter.getResults(TotalResults);

    }


    @Override
    public void onClick(View view, int Position, List<TvDataModel> Movies) {
        Intent intent = new Intent(TvMovies.this,MovieDetailsViewer.class);
        TvDataModel tvDataModel = Movies.get(Position);
        //tvDataModel.title;

        intent.putExtra("Movie", Movies.get(Position));
        Log.d("MovieDetails","This is the details of the moview we are passing " + Movies.get(Position).title);
        startActivity(intent);
    }
}
