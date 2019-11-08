package com.anamaniac.movieinfo;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackgroundAuthorization
{
    public int flagError;
    public int flagCurrent;
    public int flagPrevious;
    public View view;
    public Context mContext;
    public Boolean Success = Boolean.FALSE;

    public void GetUser(Call<UserDataModel> call, Context context) {
        this.mContext = context;
        call.enqueue(new Callback<UserDataModel>() {


            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t)
            {
                Log.d("AuthentocationError","This is the error while trying to authenticate " + t.getMessage());
            }

            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {

                String Expiry = response.body().Expiry;
                String RequestToken = response.body().request_token;
                Success = response.body().success;
//                LoginActivity loginActivity = new LoginActivity();
//                loginActivity.Returns(Expiry,RequestToken,Success);
                view.display(Success,Expiry,RequestToken);
            }
        });
    }
    public void GetPopularMovies(Call<MovieInfo1> call, Context context) {
        this.mContext = context;
        call.enqueue(new Callback<MovieInfo1>() {
            @Override
            public void onResponse(Call<MovieInfo1> call, Response<MovieInfo1> response) {
                Integer Page = response.body().page;
                Integer TotalResults = response.body().total_results;
                Log.d("Total results", "this is the total results" + TotalResults);
                Integer TotalPages = response.body().total_pages;
                List<TvDataModel> Results = response.body().results;
                view.RecyclerViewDisplay(Results,TotalResults);

            }

            @Override
            public void onFailure(Call<MovieInfo1> call, Throwable t) {

            }
        });
    }

    public CallBackgroundAuthorization(View view)
    {
        this.view = view;
    }

    public interface View
    {
        public void display(Boolean login,String Expiry,String RequestToken);
        public void RecyclerViewDisplay(List<TvDataModel> Results, Integer TotalResults);
    }
}
