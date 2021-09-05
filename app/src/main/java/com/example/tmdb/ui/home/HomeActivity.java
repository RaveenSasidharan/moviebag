package com.example.tmdb.ui.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmdb.BR;
import com.example.tmdb.R;
import com.example.tmdb.data.model.Result;
import com.example.tmdb.databinding.ActivityHomeBinding;
import com.example.tmdb.di.component.ActivityComponent;
import com.example.tmdb.ui.base.BaseActivity;
import com.example.tmdb.utils.NetworkUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements IHomeNavigator{

    private ActivityHomeBinding mHomeBinding;

    private HomeBanerAdapter mHomeBanerAdapter;

    private MoviesAdapter  mMoviesAdapter;

    private List<Result> mTrendingListResult;

    @Override
    public int getBindingVariable() {
        return BR.homeViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mHomeBinding.setActivityHome(this);

        if (NetworkUtils.isNetworkConnected(this))
            mViewModel.getTrendingMoviesFromServer();
        else
            mViewModel.getTrendingMoviesFromRoom();


    }



    @Override
    public void showTrendingMovies(List<Result> resultList) {

        this.mTrendingListResult = resultList;

        mMoviesAdapter = new MoviesAdapter(resultList, this);

        if (resultList.size() > 3)
        {
            List<String> urlList = new ArrayList<>();
            for (int index = 0; index < 3; index++)
                urlList.add(resultList.get(index).getPosterPath());
            mHomeBanerAdapter = new HomeBanerAdapter(urlList);
            mHomeBinding.moviePager.setAdapter(mHomeBanerAdapter);

            new TabLayoutMediator(mHomeBinding.movietabs,mHomeBinding.moviePager,new TabLayoutMediator.TabConfigurationStrategy(){

                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                }
            } ).attach();
        }

        mHomeBinding.movierecycler.setAdapter(mMoviesAdapter);


    }

    @Override
    public void updateTrendingMovies(List<Result> resultList) {

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(),Toast.LENGTH_LONG).show();
    }

    public void showFilterOption()
    {
        PopupMenu popup = new PopupMenu(HomeActivity.this, mHomeBinding.filter);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.rate:
                        sortByRate();
                        break;

                    case R.id.date:
                        sortByDate();
                        break;

                    case R.id.popularity:
                        sortByPopularity();
                        break;

                }

                mMoviesAdapter.notifyDataSetChanged();
                return true;
            }
        });

        popup.show();//showing popup menu
    }

    private void sortByPopularity() {

        Collections.sort(mTrendingListResult, new Comparator<Result>() {
            @Override
            public int compare(Result result1, Result result2) {
                return result1.getPopularity().compareTo(result2.getPopularity());
            }
        });
    }

    private void sortByDate() {
        Collections.sort(mTrendingListResult, new Comparator<Result>() {
            @Override
            public int compare(Result result1, Result result2) {
                return result1.getReleaseDate().compareTo(result2.getReleaseDate());
            }
        });

    }

    private void sortByRate() {
        Collections.sort(mTrendingListResult, new Comparator<Result>() {
            @Override
            public int compare(Result result1, Result result2) {
                return result1.getVoteAverage().compareTo(result2.getVoteAverage());
            }
        });
    }
}
