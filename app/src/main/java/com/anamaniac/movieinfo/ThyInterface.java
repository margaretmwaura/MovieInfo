package com.anamaniac.movieinfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ThyInterface {

    @GET("authentication/token/new")
    Call<UserDataModel> getAuthenticationToken(@Query("api_key") String ApiKey);
    @GET("movie/popular")
    Call<MovieInfo1> getPopularMovies(@Query("api_key") String ApiKey);

}
