package com.tone.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.text.format.DateUtils;
import android.view.View;

import com.tone.bottomtabview.BottomTabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomTabView tabView;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabView = findViewById(R.id.bottomTabView);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        tabView.setupWithViewPager(viewPager);

    }


    public void add(View view) {

        List<Channel> newList = new ArrayList<>();
        List<Channel> oldList = adapter.getItems();
        newList.addAll(oldList);

//        long id = System.currentTimeMillis()-1531210000000l;
        String title = "item " + id;
        String content = "item  title = " + id + "   id = " + id;

        newList.add(new Channel(title, content, id));
        id++;

        adapter.setNewList(newList);
//        refresh(newList, oldList);

    }

    public void change(View view) {
        List<Channel> newList = new ArrayList<>();
        List<Channel> oldList = adapter.getItems();
        newList.addAll(oldList);

        Channel channel = newList.remove(oldList.size() / 2);
        newList.add(0, channel);

        adapter.setNewList(newList);
//        refresh(newList, oldList);
    }

    public void remove(View view) {
        List<Channel> newList = new ArrayList<>();
        List<Channel> oldList = adapter.getItems();
        newList.addAll(oldList);

        newList.remove(oldList.size() / 2);

        adapter.setNewList(newList);
//        refresh(newList, oldList);
    }

    private class DiffCallBack extends DiffUtil.Callback {
        private List<Channel> newList, oldList;

        public DiffCallBack(List<Channel> newList, List<Channel> oldList) {
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override
        public int getOldListSize() {
            return oldList == null ? 0 : oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList == null ? 0 : newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();

        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());

        }
    }

//    private void refresh(List<Channel> newList, List<Channel> oldList) {
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallBack(newList, oldList), true);
//        result.dispatchUpdatesTo(adapter);
//
//    }


}
