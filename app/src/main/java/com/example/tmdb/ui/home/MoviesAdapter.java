package com.example.tmdb.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.data.model.Result;
import com.example.tmdb.databinding.ListMovieRecyclerBinding;
import com.example.tmdb.ui.details.MovieDetailsActivity;
import com.example.tmdb.utils.AppConstants;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private List<Result> mResutltList;
    private Activity mActivity;

    public MoviesAdapter(List<Result> resultList, Activity activity)
    {
        this.mResutltList = resultList;
        mActivity = activity;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListMovieRecyclerBinding listMovieRecyclerBinding = ListMovieRecyclerBinding.
                        inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieHolder(listMovieRecyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(mResutltList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mResutltList != null)
            return mResutltList.size();
        return 0;
    }

    public class MovieHolder extends RecyclerView.ViewHolder
    {
        private ListMovieRecyclerBinding binding;
        private Result mResult;

        public MovieHolder(ListMovieRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setMovieHolder(this);
        }

        public void bind(Result result)
        {
            mResult = result;

            Glide.with(binding.getRoot().getContext())
                    .load(AppConstants.TMDB_BASE_URL + result.getPosterPath())
                    .centerCrop()
                    .into(binding.movieThumbNail);

            binding.movieLang.setText("Language :"+result.getOriginalLanguage());
            binding.movieTitle.setText(result.getTitle());
            binding.releaseDate.setText(result.getReleaseDate());
            binding.movieRating.setRating(result.getVoteAverage().floatValue());
            binding.rateValue.setText(""+result.getVoteAverage().floatValue());
        }

        public void showDetails()
        {
            Intent intent = new Intent(mActivity, MovieDetailsActivity.class);
            intent.putExtra("movieId", mResult.getId());
            mActivity.startActivity(intent);
        }
    }
}
