package com.example.homework4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class CustomView extends View {
    private int x;
    private int y;
    private int r;
    final private RectF oval = new RectF();
    private Calculations calculations;
    private touchActionListener touchActionListener;
    private Canvas canvas;
    private int[] colours;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        x = MeasureSpec.getSize(widthMeasureSpec);
        y = MeasureSpec.getSize(heightMeasureSpec);
        r = (Math.min(x, y))/2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas=canvas;
        firstDrawing(colours);
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        calculations = new Calculations();
        if(colours==null){
            colours = calculations.getRandomColorArray(5);
        }
        super.onAttachedToWindow();
    }

    private void sectorDrawing(int sectorNumber,int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        if(sectorNumber==4){
            canvas.drawCircle(x/2,y/2,r/3,paint);
        }
        else{
            oval.set(x/2- r, y/2 - r, x/2 + r, y/2 + r);
            canvas.drawArc(oval,90F*sectorNumber,90F,true,paint);
        }
    }

    private void firstDrawing(int[] colours){
        sectorDrawing(0,colours[0]);
        sectorDrawing(1,colours[1]);
        sectorDrawing(2,colours[2]);
        sectorDrawing(3,colours[3]);
        sectorDrawing(4,colours[4]);
    }

    interface touchActionListener{
        void onTouchDown(int x,int y);
    }

    public void setTouchActionListener(CustomView.touchActionListener touchActionListener) {
        this.touchActionListener = touchActionListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if (touchActionListener!=null){
                touchActionListener.onTouchDown((int)event.getX(),(int)event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    public void colorChanger(int x,int y){
        x=this.x/2-x;
        y=this.y/2-y;
        double h = Math.sqrt(x*x+y*y);
        if(h<=(double)r/3) colours = calculations.getRandomColorArray(5);
        else if(h<=r&&x<=0&&y<0) colours[0] = calculations.getRandomColor();
        else if(h<=r&&x>0&&y<=0) colours[1] = calculations.getRandomColor();
        else if(h<=r&&x>=0&&y>0) colours[2] = calculations.getRandomColor();
        else if(h<=r&&x<0&&y>=0) colours[3] = calculations.getRandomColor();
        invalidate();
    }

    public int[] getColours() {
        return colours;
    }

    public void setColours(int[] colours) {
        this.colours = colours;
        invalidate();
    }
}
