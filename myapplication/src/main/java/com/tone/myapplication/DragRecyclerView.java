package com.tone.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

public class DragRecyclerView extends RecyclerView {

    private ItemTouchHelper mItemTouchHelper;

    private boolean isItemCanDrag = true;

    public DragRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void init(Context context) {
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {

                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                final int swipeFlags = 0;

                return makeMovementFlags(isItemCanDrag ? dragFlags : 0, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {

                int viewPosition = viewHolder.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();
                Adapter adapter = getAdapter();
                adapter.notifyItemMoved(viewPosition, targetPosition);
                return true;
            }

            @Override
            public void onSwiped(ViewHolder viewHolder, int direction) {
                int viewPosition = viewHolder.getAdapterPosition();
                Adapter adapter = getAdapter();
                adapter.notifyItemRemoved(viewPosition);

            }
        });
        mItemTouchHelper.attachToRecyclerView(this);

    }

    public boolean isItemCanDrag() {
        return isItemCanDrag;
    }

    public void setItemCanDrag(boolean itemCanDrag) {
        isItemCanDrag = itemCanDrag;
    }
}
