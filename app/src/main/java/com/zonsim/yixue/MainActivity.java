package com.zonsim.yixue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zonsim.yixue.banner.Banner;
import com.zonsim.yixue.banner.BannerActivity;
import com.zonsim.yixue.banner.RecyclerBanner;
import com.zonsim.yixue.myexams.MyExamsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private RecyclerBanner mRecyclerBanner;
    private List<Banner> mBanners;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mRecyclerBanner = (RecyclerBanner) findViewById(R.id.iv_banner);
    
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
    
        
        mRecyclerBanner.replaceBanners(mBanners);
        
    }
    
    public void startMyExams(View view) {
        Intent intent = new Intent(this, MyExamsActivity.class);
        startActivity(intent);
    }
    
    public void startBanner(View view) {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }
}
