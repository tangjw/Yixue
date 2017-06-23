package com.zonsim.yixue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zonsim.yixue.myexams.MyExamsActivity;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void startMyExams(View view) {
        Intent intent = new Intent(this, MyExamsActivity.class);
        startActivity(intent);
    }
}
