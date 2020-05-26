package com.example.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class PaintView extends View {

    private Path path = new Path();
    private Paint brush = new Paint();
    Button bulka;

    private Bitmap btmBackground, btmView;
    private float mX, mY;
    private final int DEFFERENCE_SPACE = 4;
    private Canvas mCanvas;
    private int colorBackground;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush = new Paint();

        brush.setAntiAlias(true);
        brush.setColor(Color.MAGENTA);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        colorBackground = Color.WHITE;
    }

    //    public boolean onTouchEvent(MotionEvent event) {
//        float pointX = event.getX();
//        float pointY = event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                path.moveTo(pointX,pointY);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                path.lineTo(pointX,pointY);
//                break;
//            default:
//                return true;
//        }
//        invalidate();
//        return false;
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMode(x,y);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }

        return true;
    }

    private void touchUp() {
        path.reset();
    }

    private void touchMode(float x, float y) {
        float dx = Math.abs(x-mX);
        float dy = Math.abs(y-mY);

        if(dx >= DEFFERENCE_SPACE || dy >= DEFFERENCE_SPACE){
            path.quadTo(x,y,(x+mX)/2,(y+mY)/2);

            mY = y;
            mX = x;

            mCanvas.drawPath(path,brush);
            invalidate();
        }

    }

    private void touchStart(float x, float y) {
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }
    @Override
    protected  void onDraw(Canvas canvas){
        //canvas.drawPath(path,brush);

        //canvas.drawColor(colorBackground);
        //canvas.drawBitmap(btmBackground,0,0,null);
        canvas.drawBitmap(btmView,0,0,null);
    }

    public void setPathColor(int color) {
        brush.setColor(color);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //btmBackground = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        btmView = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(btmView);
    }

}