package com.tone.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.util.ListUpdateCallback;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter implements ListUpdateCallback {
    private String TAG = getClass().getSimpleName();

    private List<Channel> items = new ArrayList<>();
    private List<Channel> newList = new ArrayList<>();
    private ArrayMap<String, Fragment> fragments = new ArrayMap();

    public List<Channel> getItems() {
        return items;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setNewList(List<Channel> newList) {
        this.newList = newList;
    }

    public void add(int index, String title) {
//        items.add(index, new Channel(title, items.size() + 1));
//        notifyDataSetChanged();
    }

    public void remove(int index) {
//        String title = items.get(index).getTitle();
//        items.remove(index);
//        fragments.remove(title);
//        notifyDataSetChanged();
    }


    public void change(int index) {
//        String title = items.get(index);
//        items.remove(index);
//        items.add(1, title);
//        notifyDataSetChanged();

    }

    @Override
    public Fragment getItem(int i) {
        String key = items.get(i).getTitle();
        Fragment fragment = fragments.get(key);
        Log.d(TAG, "getItem:  key=" + key + "   fragment  = " + fragment);
        if (fragment == null) {
            fragment = BlackFragment.newInstance(items.get(i).getContent());
            fragments.put(key, fragment);
        }
        Log.d(TAG, "getItem:  key=" + key + "   fragment  = " + fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.d(TAG, "getItemPosition: " + object);
//        BlackFragment fragment = (BlackFragment) object;
//        String key = fragment.getTitle();
//        int k1 = fragments.indexOfKey(key);
//        int k2 = items.indexOf(key);
//        if (k1 != k2)
        return POSITION_NONE;
//        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getTitle();
    }


    @Override
    public void onInserted(int position, int count) {
        Log.d(TAG, "onInserted:  position = " + position);
        items.add(position,newList.get(position));
        notifyDataSetChanged();
    }

    @Override
    public void onRemoved(int position, int count) {
        Log.d(TAG, "onRemoved:  position = " + position);
        items.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        Log.d(TAG, "onMoved:  fromPosition = " + fromPosition);
        Channel channel = items.remove(fromPosition);
        items.add(toPosition,channel);
        notifyDataSetChanged();

    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        Log.d(TAG, "onChanged: position = " + position);
        items.remove(position);
        items.add(position, (Channel) payload);
        notifyDataSetChanged();
    }
}
