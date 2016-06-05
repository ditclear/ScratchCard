package com.ditclear.scratchcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by vienan on 16/6/5.
 */
public class ScratchCard extends TextView {

    private Paint mPaint;
    private int gap;            //间隙大小
    private int radius;         //圆的半径
    private int remain;         //遗留下的距离
    private int circleNum;      //圆形数量
    private int color;          //圆的颜色

    public ScratchCard(Context context) {
        super(context);
    }

    public ScratchCard(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs,0);
    }

    public ScratchCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {


        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.ScratchCard);
        color=array.getColor(R.styleable.ScratchCard_card_color, Color.WHITE);
        gap=array.getInteger(R.styleable.ScratchCard_card_gap,8);
        radius=array.getInteger(R.styleable.ScratchCard_circle_radius,10);
        array.recycle();

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain==0) remain=(w-gap)%(radius*2+gap);
        circleNum=(w-gap)/(radius*2+gap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {

            float x=gap+radius+remain/2+i*(radius*2+gap);
            canvas.drawCircle(x,0,radius,mPaint);
            canvas.drawCircle(x,getHeight(),radius,mPaint);
        }
    }


    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(color);
        invalidate();
    }
}
