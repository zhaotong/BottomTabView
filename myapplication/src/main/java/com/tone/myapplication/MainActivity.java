package com.tone.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tone.bottomtabview.BottomTabView;

public class MainActivity extends AppCompatActivity {

    private BottomTabView tabView;
    private ViewPager viewPager;
    private FragmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabView = findViewById(R.id.bottomTabView);
        adapter=new FragmentAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        tabView.setupWithViewPager(viewPager);

    }


    public void add(View view){
        adapter.add(0,(adapter.getItems().size()+1)+" view");

    }
    public void change(View view){
        adapter.change(4);
        viewPager.setCurrentItem(1);
    }

    public void remove(View view){
        adapter.remove(0);
    }
}
