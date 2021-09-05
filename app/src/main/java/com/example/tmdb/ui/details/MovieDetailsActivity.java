package com.example.tmdb.ui.details;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.tmdb.BR;
import com.example.tmdb.R;
import com.example.tmdb.data.model.Movie;
import com.example.tmdb.databinding.ActivityMovieDetailsBinding;
import com.example.tmdb.di.component.ActivityComponent;
import com.example.tmdb.ui.base.BaseActivity;
import com.example.tmdb.utils.AppConstants;
import com.example.tmdb.utils.NetworkUtils;

public class MovieDetailsActivity extends BaseActivity<ActivityMovieDetailsBinding, DetailsViewModel>
implements IDetailsNavigator{
    private ActivityMovieDetailsBinding mDetailsBinding;

    private int movieId;
    private ProductionAdapter mProductionAdapter;

    @Override
    public int getBindingVariable() {
        return BR.detailViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_details;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDetailsBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mDetailsBinding.setMovieDetails(this);

        movieId = getIntent().getIntExtra("movieId", 0);

        if (NetworkUtils.isNetworkConnected(this))
            mViewModel.getMovieFromServer(movieId);
        else
            mViewModel.getMovieFromRoom(movieId);
    }

    public void back()
    {
        finish();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovieDetails(Movie movie) {


        Glide.with(this)
                .load(AppConstants.TMDB_BASE_URL + movie.getPosterPath())
                .centerCrop()
                .into(mDetailsBinding.movieImage);

        mDetailsBinding.movieLang.setText("Language"+movie.getOriginalLanguage());
        mDetailsBinding.movieTitle.setText(movie.getOriginalTitle());
        mDetailsBinding.releaseDate.setText(movie.getReleaseDate());
        mDetailsBinding.releaseStatus.setText(movie.getStatus());
        mDetailsBinding.synopsosDesc.setText(movie.getOverview());
        mDetailsBinding.movieRating.setRating(movie.getVoteAverage().floatValue());

        mProductionAdapter = new ProductionAdapter(movie.getProductionCompanies());
        mDetailsBinding.prodHouseRecycler.setAdapter(mProductionAdapter);


    }
}
