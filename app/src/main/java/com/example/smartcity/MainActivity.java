package com.example.smartcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.smartcity.fragment.IndexFragment;
import com.example.smartcity.fragment.NewsFragment;
import com.example.smartcity.fragment.ServiceFragment;
import com.example.smartcity.fragment.SmartBuildingFragment;
import com.example.smartcity.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private LinearLayout linearLayout;
    private Fragment [] fragments;
    private int lastFragment;

    private IndexFragment indexFragment;
    private NewsFragment newsFragment;
    private ServiceFragment serviceFragment;
    private SmartBuildingFragment smartBuildingFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate( final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.index :{

                    }
                }
                return false;
            }
        });
    }
    private void initUI(){
        linearLayout = findViewById(R.id.layout_bottom);
        bottomNavigationView = findViewById(R.id.nav_view);
    }
    private void initData(){}

}