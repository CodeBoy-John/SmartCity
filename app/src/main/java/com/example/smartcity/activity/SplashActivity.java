package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.MainActivity;
import com.example.smartcity.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
private TextView internet;
private TextView enterMain;
Banner banner;
LinearLayout layout_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//判断是否是第一次登录
        SharedPreferences preferences = getSharedPreferences("SmartCity",MODE_PRIVATE);
        boolean isFirstLogin = preferences.getBoolean("isFirstLogin",true);
//        if (isFirstLogin){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstLogin",false);
            editor.commit();
            initUI();
            initData();
            addListener();
//        }else {
//            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//            startActivity(intent);
        }
   // }
    private void initUI(){
        layout_splash = findViewById(R.id.layout_splash);
        internet = findViewById(R.id.internet);
        enterMain = findViewById(R.id.enterMain);

    }
    private void initData(){
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_im2);
        images.add(R.drawable.banner_im3);
        images.add(R.drawable.banner_im4);
        images.add(R.drawable.banner_im5);

        banner = findViewById(R.id.banner_splash);
        //设置Banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        //此处需要创建一个图片加载器的类GlideImageLoader
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
     //   banner.setDelayTime(2000);
        //不需自动轮播时
        banner.isAutoPlay(false);
        //banner设置方法全部调用完毕后，最后调用监听
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==4){
                    layout_splash.setVisibility(View.VISIBLE);
                }else {
                    layout_splash.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        banner.start();
    }
    private void addListener(){
        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socketDialog();
            }
        });
        enterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void socketDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.internet,null);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);

        final EditText et_ip = view.findViewById(R.id.et_ip);
        final EditText et_port = view.findViewById(R.id.et_port);
        Button btn_setNet = view.findViewById(R.id.button);

        final SharedPreferences preferences = getSharedPreferences("SmartCity",MODE_PRIVATE);
        final String strNetIP = preferences.getString("IP","192.168.1.110");
        String strNetPort = preferences.getString("port","8080");

        et_ip.setText(strNetIP);
        et_port.setText(strNetPort);

        btn_setNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //正则表达式判断IP地址
                String strNetIp = et_ip.getText().toString().trim();
                String strNetPort = et_port.getText().toString().trim();

                SharedPreferences preferences1 = getSharedPreferences("SmartCity",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("IP",strNetIP);
                editor.putString("port",strNetPort);
                editor.commit();
                Toast.makeText(getApplicationContext(),"设置成功",Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}