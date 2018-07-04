package com.tone.bottomtabview;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BottomTabView extends TabLayout {

    private SparseArray<TabViewItem> tabItems = new SparseArray<>();
    private LayoutInflater inflater;
    private OnTabItemSelectedListener selectedListener;

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


    public void setTabItems(SparseArray<TabViewItem> tabItems) {
        this.tabItems = tabItems;
        int count = getTabCount();
        for (int i = 0; i < count; i++) {
            TabViewItem item = tabItems.get(i);
            Tab tab = getTabAt(i);

            View view = inflater.inflate(R.layout.bottom_tab_view_item_layout, null);
            ImageView imageView = view.findViewById(R.id.bottom_tab_view_item_icon);
            TextView textView = view.findViewById(R.id.bottom_tab_view_item_text);
            textView.setText(item.getTitle());
            textView.setTextColor(getTabTextColors());
            imageView.setImageResource(item.getResId());
            imageView.setImageTintList(getTabTextColors());

            tab.setCustomView(view);
        }
    }


    public void setSelectedListener(OnTabItemSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }


}
