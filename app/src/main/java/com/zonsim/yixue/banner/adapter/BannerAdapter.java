package com.zonsim.yixue.banner.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zonsim.yixue.R;
import com.zonsim.yixue.banner.Banner;
import com.zonsim.yixue.util.L;

import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/26.
 */

public class BannerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private List<Banner> mBanners;
    private RequestManager mGlideRM;
    
    public BannerAdapter(@NonNull List<Banner> banners) {
        mBanners = banners;
    }
    
    public void replaceData(@NonNull List<Banner> banners) {
        mBanners = banners;
        notifyDataSetChanged();
    }
    
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        
        if (mGlideRM == null) {
            mGlideRM = Glide.with(parent.getContext());
        }
        
        
        return new ItemVH(inflate);
    }
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Banner banner = mBanners.get(position);
        
        ItemVH itemVH = (ItemVH) holder;
        
        itemVH.ivBanner.setImageResource(banner.getResId());
        
        mGlideRM.load(banner.getResId())
                .centerCrop()
                .crossFade()
                .into(itemVH.ivBanner);
        
        L.i("---onBindViewHolder => position : " + position);
        L.i("---onBindViewHolder => position : " + itemVH.getAdapterPosition());
    }
    
    @Override
    public int getItemCount() {
        L.i("getItemCount");
        return mBanners.size();
    }
    
    
    private static class ItemVH extends RecyclerView.ViewHolder {
        ImageView ivBanner;
        
        ItemVH(View itemView) {
            super(itemView);
            ivBanner = (ImageView) itemView.findViewById(R.id.iv_banner);
        }
    }
}
