package com.tone.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlackFragment extends Fragment {

    private String TAG = getClass().getSimpleName();
    private String title="";



    public String getTitle() {
        return title;
    }

    public static BlackFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        BlackFragment fragment = new BlackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.balck_fragment, container, false);
        TextView textView = view.findViewById(R.id.text);
        title = getArguments().getString("title", "");
        textView.setText(title);
        Log.d(TAG, "onCreateView:   title = " + title);
        return view;
    }


}
