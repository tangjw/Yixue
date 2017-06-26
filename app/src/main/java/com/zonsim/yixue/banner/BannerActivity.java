package com.zonsim.yixue.banner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zonsim.yixue.R;
import com.zonsim.yixue.banner.adapter.BannerAdapter;
import com.zonsim.yixue.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/26.
 */

public class BannerActivity extends BaseActivity {
    
    private RecyclerView mRecyclerView;
    private List<Banner> mBanners;
    private BannerAdapter mAdapter;
    
    @Override
    protected void initArgs() {
        mBanners = new ArrayList<>();
        mBanners.add(new Banner("", R.drawable.img_1));
        mBanners.add(new Banner("", R.drawable.img_2));
        mBanners.add(new Banner("", R.drawable.img_3));
        mBanners.add(new Banner("", R.drawable.img_4));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        
        mAdapter = new BannerAdapter(mBanners);
    }
    
    @Override
    protected void initView() {
        setContentView(R.layout.activity_banner);
        
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_banner);
    
    
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setAdapter(mAdapter);
        
    }
}
