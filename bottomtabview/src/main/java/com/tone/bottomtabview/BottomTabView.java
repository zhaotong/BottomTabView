package com.tone.bottomtabview;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BottomTabView extends TabLayout {

    private SparseArray<TabViewItem> tabItems = new SparseArray<>();
    private LayoutInflater inflater;
    private OnTabItemSelectedListener selectedListener;
    private int imageWidth, imageHeight;
    private float textSize;

    public BottomTabView(Context context) {
        super(context);
        init(context);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        inflater = LayoutInflater.from(context);


        addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                if (tab.getCustomView() != null)
                    tab.getCustomView().setSelected(true);
                if (selectedListener != null)
                    selectedListener.onTabSelected(getSelectedTabPosition());

            }

            @Override
            public void onTabUnselected(Tab tab) {
                if (tab.getCustomView() != null)
                    tab.getCustomView().setSelected(false);
                if (selectedListener != null)
                    selectedListener.onTabUnselected(tab.getPosition());

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

    }


    public void setTabItems(SparseArray<TabViewItem> items) throws Exception {
        if (items != null)
            this.tabItems = items;
        int count = getTabCount();
        if (count != tabItems.size())
            throw new Exception("item's size ");
        for (int i = 0; i < count; i++) {
            TabViewItem item = tabItems.get(i);
            Tab tab = getTabAt(i);

            View view = inflater.inflate(R.layout.bottom_tab_view_item_layout, null);
            ImageView imageView = view.findViewById(R.id.bottom_tab_view_item_icon);

            if (imageWidth != 0 && imageHeight != 0) {
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.width = imageWidth;
                params.height = imageHeight;
                imageView.setLayoutParams(params);
            }
            TextView textView = view.findViewById(R.id.bottom_tab_view_item_text);
            textView.setText(item.getTitle());
            if (textSize != 0)
                textView.setTextSize(textSize);
            textView.setTextColor(getTabTextColors());
            if (item.getResId() == 0) {
                imageView.setVisibility(GONE);
            } else {
                imageView.setImageResource(item.getResId());
                imageView.setImageTintList(getTabTextColors());
            }


            tab.setCustomView(view);
        }
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidthHeight(int imageWidth,int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public int getImageHeight() {
        return imageHeight;
    }


    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setSelectedListener(OnTabItemSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }


}
