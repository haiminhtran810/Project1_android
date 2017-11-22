package com.example.hcm_102_0006.android_project_m.view.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.Service.model.Movie;
import com.example.hcm_102_0006.android_project_m.Service.model.Result;
import com.example.hcm_102_0006.android_project_m.Service.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.Service.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityHomeBinding;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowMovie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    public static final int RESPONSE = 810;
    private List<String> mCategories;
    private List<Movie> mMovies;
    private List<Movie> mMoviesAgain;
    private AdapterShowMovie mAdapterShowMovie;
    private ActivityHomeBinding mActivityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = new ArrayList<>();
        mActivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);

        mMovies = new ArrayList<>();
        mCategories = new ArrayList<>();
        mMoviesAgain = new ArrayList<>();

        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.categories)));
        mAdapterShowMovie = new AdapterShowMovie(mMovies);
        mActivityHomeBinding.rcyShowMovies.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityHomeBinding.rcyShowMovies.setAdapter(mAdapterShowMovie);
        mActivityHomeBinding.setHomeDataBinding(this);
        getInformationMovies(mCategories.get(0));

    }

    private void getInformationMovies(String category) {
        MovieApi service = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getMovie(category)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Result result) {
                        mMovies.clear();
                        mMoviesAgain.clear();
                        mMovies.addAll(result.getResults());
                        mAdapterShowMovie.notifyDataSetChanged();
                        mMoviesAgain.addAll(mMovies);
                    }
                });
    }

    public void getMoviesFollowCategory(View view) {
        switch (view.getId()) {
            case R.id.btnPopular:
                getInformationMovies(mCategories.get(2));
                break;
            case R.id.btnNowPlaying:
                getInformationMovies(mCategories.get(1));
                break;
            case R.id.btnTopRate:
                getInformationMovies(mCategories.get(0));
                break;
            case R.id.btnGenres:
                startActivityForResult(new Intent(this, GenresActivity.class), RESPONSE);
                break;
            case R.id.btnUpcoming:
                getInformationMovies(mCategories.get(3));
                break;
        }
    }

    public TextWatcher nameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMovies.clear();
                String enterCharacter = charSequence.toString().toUpperCase().trim();
                if (enterCharacter == "") {
                    mMovies.addAll(mMoviesAgain);
                } else {
                    for (Movie movie : mMoviesAgain) {
                        String templateSearch = movie.getTitle().toUpperCase();
                        if (templateSearch.contains(enterCharacter)) {
                            mMovies.add(movie);
                        }
                    }
                }
                mAdapterShowMovie.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

}
