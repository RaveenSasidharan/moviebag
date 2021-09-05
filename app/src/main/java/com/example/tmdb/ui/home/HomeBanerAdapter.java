package com.example.tmdb.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.databinding.ListBannerViewBinding;
import com.example.tmdb.utils.AppConstants;

import java.util.List;

public class HomeBanerAdapter extends RecyclerView.Adapter<HomeBanerAdapter.HomeBanerHolder> {


    private List<String> mUrlList;

    public HomeBanerAdapter(List<String> urlList)
    {
        this.mUrlList = urlList;
    }

    @NonNull
    @Override
    public HomeBanerAdapter.HomeBanerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListBannerViewBinding listBannerViewBinding = ListBannerViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new HomeBanerAdapter.HomeBanerHolder(listBannerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBanerAdapter.HomeBanerHolder holder, int position) {
        holder.bind(mUrlList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mUrlList != null)
            return mUrlList.size();

        return 0;
    }

    public static class HomeBanerHolder extends RecyclerView.ViewHolder
    {
        private ListBannerViewBinding binding;
        public HomeBanerHolder(ListBannerViewBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(String url)
        {
            Glide.with(itemView.getContext())
                    .load(AppConstants.TMDB_BASE_URL + url)
                    .centerCrop()
                    .into(binding.poster);

        }
    }
}
