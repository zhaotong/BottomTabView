package com.tone.myapplication;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FmStateAdapter extends PagerAdapter {


    private static final String TAG = "FragmentStatePagerAdapt";
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    //    private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<>();
//    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private LinkedHashMap<String, Fragment> mFragments = new LinkedHashMap<>();
//    private LinkedHashMap<String, Fragment.SavedState> mSavedState = new LinkedHashMap<>();


    private Fragment mCurrentPrimaryItem = null;


    private int dataSize;
    private List<Channel> datas = new ArrayList<>();


    public void setNewList(List<Channel> list) {
        int newSize = list.size();
        int oldSize = datas.size();
        //取出oldlist里面不存在newlist中的数据

        for (int i = 0; i < oldSize; i++) {
            Channel channel = datas.get(i);
            if (!list.contains(channel)) {
                String key = getKey(i);

                Fragment fragment = mFragmentManager.findFragmentByTag(key);

                if (fragment != null) {
                    if (mCurTransaction == null) {
                        mCurTransaction = mFragmentManager.beginTransaction();
                    }
                    mCurTransaction.remove(fragment);
                }
            }
        }

        this.datas = list;
        notifyDataSetChanged();
    }

    public FmStateAdapter(FragmentManager fm) {
        mFragmentManager = fm;
    }

    public Fragment getItem(int position) {

        BlackFragment fragment = BlackFragment.newInstance(datas.get(position).getContent());
        fragment.setChannel(datas.get(position));
        fragment.setPosition(position);
        return fragment;
    }


    private String getKey(int position) {
        String key = "android:switcher:" + datas.get(position).hashCode() + ":" + position;
        return key;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        if (mFragments.size() > position) {
        String key = getKey(position);

        Fragment f = mFragments.get(key);
        if (f != null) {
            return f;
        }
//        }

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        Fragment fragment = getItem(position);
        Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
//        if (mSavedState.size() > position) {
//            Fragment.SavedState fss = mSavedState.get(position);
//            if (fss != null) {
//                fragment.setInitialSavedState(fss);
//            }
//        }

//        while (mFragments.size() <= position) {
//            mFragments.clear();
//        }

        fragment.setMenuVisibility(false);
        fragment.setUserVisibleHint(false);

        mFragments.put(getKey(position), fragment);
        mCurTransaction.add(container.getId(), fragment, getKey(position));

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        Log.v(TAG, "Removing item #" + position + ": f=" + object
                + " v=" + ((Fragment) object).getView());
//        while (mSavedState.size() <= position) {
//            mSavedState.put(getKey(position),null);
//        }
//        mSavedState.put(getKey(position), fragment.isAdded()
//                ? mFragmentManager.saveFragmentInstanceState(fragment) : null);
//
        mFragments.remove(fragment);

        mCurTransaction.remove(fragment);
    }

    @Override
    @SuppressWarnings("ReferenceEquality")
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }


    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        BlackFragment fragment = (BlackFragment) object;
        int position = fragment.getPosition();

//        if (datas.get(position).getId() == fragment.getChannel().getId()) {
//            return POSITION_UNCHANGED;
//        } else {
            return POSITION_NONE;
//        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getTitle();
    }


    @Override
    public void notifyDataSetChanged() {
        dataSize = getCount();
        super.notifyDataSetChanged();
    }


    public List<Channel> getItems() {
        return datas;
    }
}
