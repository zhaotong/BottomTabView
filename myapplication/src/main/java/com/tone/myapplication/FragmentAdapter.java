package com.tone.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private String TAG = getClass().getSimpleName();

    private List<String> items = new ArrayList<>();
    private ArrayMap<String, Fragment> fragments = new ArrayMap();

    public List<String> getItems() {
        return items;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(int index, String title) {
        items.add(index, title);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        String title = items.get(index);
        items.remove(index);
        fragments.remove(title);
        notifyDataSetChanged();
    }


    public void change(int index) {
        String title = items.get(index);
        items.remove(index);
        items.add(1, title);
        notifyDataSetChanged();

    }

    @Override
    public Fragment getItem(int i) {
        String key = items.get(i);
        Fragment fragment = fragments.get(key);
        Log.d(TAG, "getItem:  key=" + key + "   fragment  = " + fragment);
        if (fragment == null) {
            fragment = BlackFragment.newInstance(items.get(i));
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
        return items.get(position);
    }
}
