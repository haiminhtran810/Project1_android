package com.example.hcm_102_0006.android_project_m.Service.repository;

import com.example.hcm_102_0006.android_project_m.Service.model.GenresMovie;
import com.example.hcm_102_0006.android_project_m.Service.model.Result;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public interface MovieApi {
    String SERVICE_URL = "https://api.themoviedb.org";
    String KEY_API = "c733ac6aba3d86364a56d1145bc1d1f9";

    //Get information Category: https://api.themoviedb.org/3/movie/upcoming?api_key=c733ac6aba3d86364a56d1145bc1d1f9
    @GET("/3/movie/{category}?api_key=c733ac6aba3d86364a56d1145bc1d1f9")
    Observable<Result> getMovie (@Path("category") String category);

    //https://api.themoviedb.org/3/genre/movie/list?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    @GET("/3/genre/movie/list?api_key=" + KEY_API)
    Observable<GenresMovie> getGenres ();

}
