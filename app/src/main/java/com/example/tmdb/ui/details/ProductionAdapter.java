package com.example.tmdb.ui.details;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.data.model.ProductionCompany;
import com.example.tmdb.databinding.ListBannerViewBinding;
import com.example.tmdb.databinding.ListProdItemBinding;
import com.example.tmdb.utils.AppConstants;

import java.util.List;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.ProductionHolder> {


    private List<ProductionCompany> mProductionCompanyList;

    public ProductionAdapter(List<ProductionCompany> productionCompanyList)
    {
        this.mProductionCompanyList = productionCompanyList;
    }

    @NonNull
    @Override
    public ProductionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListProdItemBinding listProdItemBinding = ListProdItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ProductionHolder(listProdItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionHolder holder, int position) {
        holder.bind(mProductionCompanyList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProductionCompanyList != null)
            return mProductionCompanyList.size();

        return 0;
    }

    public static class ProductionHolder extends RecyclerView.ViewHolder
    {
        private ListProdItemBinding listProdItemBinding;
        public ProductionHolder(ListProdItemBinding listProdItemBinding) {
            super(listProdItemBinding.getRoot());

            this.listProdItemBinding = listProdItemBinding;
        }

        public void bind(ProductionCompany productionCompany)
        {

            if (productionCompany.getLogoPath() != null)
                Glide.with(listProdItemBinding.getRoot().getContext())
                        .load(AppConstants.TMDB_BASE_URL+productionCompany.getLogoPath())
                        .centerCrop()
                        .into(listProdItemBinding.logo);
        }
    }
}
