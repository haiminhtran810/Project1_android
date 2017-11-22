package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.Service.model.Movie;
import com.example.hcm_102_0006.android_project_m.databinding.ItemMovieBinding;
import java.util.List;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class AdapterShowMovie extends RecyclerView.Adapter<AdapterShowMovie.MyViewHolder> {

    private static List<Movie> sMovies;
    public AdapterShowMovie(List<Movie> mMovies) {
        this.sMovies = mMovies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding movieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MyViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setBinding(sMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return sMovies == null ? 0 : sMovies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> mTitle = new ObservableField<>();
        public ObservableField<String> mVoteAverage = new ObservableField<>();
        public ObservableField<String> profileImage = new ObservableField<>();

        public ItemMovieBinding mItemMovieBinding;

        public MyViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            mItemMovieBinding = itemMovieBinding;
        }

        public void setBinding(Movie movie) {
            if (mItemMovieBinding.getItemView() == null) mItemMovieBinding.setItemView(this);
            mTitle.set(movie.getTitle());
            mVoteAverage.set(movie.getVote_average() + "");
            profileImage.set(movie.getPoster_path());
        }

        @BindingAdapter("imageUrl")
        public static void setImageUrl(ImageView imageView, String url) {
            Context context = imageView.getContext();
            String imagePath = "http://image.tmdb.org/t/p/w185/"+ url;
            Glide.with(context).load(imagePath).into(imageView);
        }

    }
}
