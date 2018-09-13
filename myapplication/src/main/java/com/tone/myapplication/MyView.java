package com.tone.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{
    Paint paint;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        paint=new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        PathEffect effect=new PathEffect();
    }
}
