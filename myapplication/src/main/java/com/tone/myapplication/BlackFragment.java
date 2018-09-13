package com.tone.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.UUID;

public class BlackFragment extends Fragment {

    private String TAG = getClass().getSimpleName();
    private String title = "";
    private DragRecyclerView dragRecyclerView;

    private Channel channel;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

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

        Log.d(TAG, "onCreateView: ");
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: " + title);
        TextView textView = view.findViewById(R.id.text);
        title = getArguments().getString("title", "");


        textView.setText(title + " \n" + (new Date().toLocaleString()));
//        getAid();


        dragRecyclerView = view.findViewById(R.id.dragRecyclerView);
        dragRecyclerView.setItemCanDrag(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dragRecyclerView.setLayoutManager(manager);
        dragRecyclerView.setAdapter(new Adapter());

    }


    private String getAid() {
        try {
            FileReader reader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                Log.d(TAG, "内存信息: " + read);
                if (read.contains("MemTotal")) {
                    String v = read.split(":")[1].replace("kB", "").trim();
                    int size = Integer.valueOf(v);

                    Log.d(TAG, "总内存: " + (size / 1024f / 1024f) + "GB");
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "BOARD :" + Build.BOARD
                + "\n" + "  DEVICE :" + Build.DEVICE
                + "\n" + "  BRAND :" + Build.BRAND
                + "\n" + "  DISPLAY :" + Build.DISPLAY
                + "\n" + "  ID :" + Build.ID
                + "\n" + "  PRODUCT :" + Build.PRODUCT
                + "\n" + "  MODEL :" + Build.MODEL
                + "\n" + "  MANUFACTURER :" + Build.MANUFACTURER);


        String id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d(TAG, "getAid: ANDROID_ID " + id);
        Log.d(TAG, "getAid: UUID " + UUID.randomUUID().toString());

        return "" + id;
    }


    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            if (layoutParams == null)
                layoutParams = new ViewGroup.LayoutParams(380, 200);

            textView.setLayoutParams(layoutParams);
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText("Item " + position);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

}
