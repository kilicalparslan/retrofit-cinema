package com.alparslankilic.retrofitcinema;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //bağlanmak istediğimiz end point i kullanmamızı sağlar
    @GET("list_movies.json?sort_by=date_added")
    Call<MovieResponse> getLastMovies();


}

