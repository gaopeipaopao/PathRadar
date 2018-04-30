package com.example.gaope.pathradar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 5个边的雷达图
 * canvas Path drawText Paint
 * Created by gaope on 2018/4/30.
 */

public class Radar extends View {

    private static final String TAG = "Radar";

  /**
   * 5个边
   */
    private int count = 5;

    /**
     * 中心点的X坐标
     */
    private float centerX;

    /**
     * 中心的的Y坐标
     */
    private float centerY;

    /**
     * 多边形的画笔
     */
    private Paint  polygonPaint = new Paint();

    /**
     * 文字的画笔
     */
    private Paint textPaint = new Paint();

    /**
     * 连线的画笔
     */
    private Paint linePaint = new Paint();

    /**
     * 区域的画笔
     */
    private Paint regionPaint = new Paint();

    /**
     * 半径
     */
    private float radius;

    /**
     * 每个多边形之间的间距
     */
    private float rr ;

    /**
     *文本内容
     */
    private char[] text = {'a','b','c','d','e'};

    /**
     *
     * @param context
     * @param attrs
     */


    public Radar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        polygonPaint.setStyle(Paint.Style.STROKE);
        polygonPaint.setStrokeWidth(5);

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);

        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(5);
        textPaint.setTextSize(45);

        regionPaint.setStyle(Paint.Style.FILL);
        regionPaint.setColor(Color.BLUE);
        regionPaint.setAlpha(155);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获得中心点
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        radius = Math.min(centerX,centerY) * 0.9f;
        rr = radius / count;

        Log.d(TAG,"centerX:"+centerX);
        Log.d(TAG,"centerY:"+centerY);

        canvas.translate(centerX,centerY);
        drawPolygon(canvas);
        drawLine(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制区域
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        float x = 0;
        float y = 0;
        Path path = new Path();
        for(int i = 0;i < count; i++){
            if (i == 0){
                x = (float) ((rr * (i + 1)) * Math.cos(54 * Math.PI / 180));
                y = (float) ( (rr * (i + 1)) * Math.sin(54 * Math.PI / 180));
                path.moveTo(x , y);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 1){
                x = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.cos(54 * Math.PI / 180)));
                y = (float) ((rr * (i + 1)) * Math.sin(54 * Math.PI / 180));
                path.lineTo(x,y);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 2){
                x = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.cos(18 * Math.PI / 180)));
                y = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.sin(18 * Math.PI / 180)));
                path.lineTo(x,y);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 3){
                x =  0;
                y = (- 1) * (rr * (i + 1));
                path.lineTo(x,y);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 4){
                x = (float)  ( (rr * (i + 1)) * Math.cos(18 * Math.PI / 180));
                y = (float) ((float) (-1) *  (rr * (i + 1)) * Math.sin(18 * Math.PI / 180));
                path.lineTo(x,y);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
        }
        path.close();
        canvas.drawPath(path,regionPaint);
    }

    /**
     * 绘制文本
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        float x = 0;
        float y = 0;
        for (int i = 0;i < count;i++){
            if (i == 0){
                x = (float) ((rr * 5) * Math.cos(54 * Math.PI / 180));
                y = (float) ( (rr * 5) * Math.sin(54 * Math.PI / 180));
                canvas.drawText(String.valueOf(text[i]),x + textPaint.measureText(String.valueOf(text[i])),y,textPaint);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 1){
                x = (float) ((float) (-1) * ( (rr * 5) * Math.cos(54 * Math.PI / 180)));
                y = (float) ((rr * 5) * Math.sin(54 * Math.PI / 180));
                canvas.drawText(String.valueOf(text[i]),x - 2 * textPaint.measureText(String.valueOf(text[i])),y,textPaint);
                Log.d(TAG,"x:"+x+"   y:"+y);
            }
            if(i == 2){
                x = (float) ((float) (-1) * ( (rr * 5) * Math.cos(18 * Math.PI / 180)));
                y = (float) ((float) (-1) * ( (rr * 5) * Math.sin(18 * Math.PI / 180)));
                canvas.drawText(String.valueOf(text[i]),x - 2 * textPaint.measureText(String.valueOf(text[i])),y,textPaint);
                Log.d(TAG,"x:" + x + "   y:"+y);
            }
            if(i == 3){
                x =  0;
                y = (- 1) * (rr * 5);
                canvas.drawText(String.valueOf(text[i]),x,y - 2 * textPaint.measureText(String.valueOf(text[i])),textPaint);
                Log.d(TAG,"x:" + x+"   y:"+y);
            }
            if(i == 4){
                x = (float)  ( (rr * 5) * Math.cos(18 * Math.PI / 180));
                y = (float) ((float) (-1) *  (rr * 5) * Math.sin(18 * Math.PI / 180));
                canvas.drawText(String.valueOf(text[i]),x + textPaint.measureText(String.valueOf(text[i])),y,textPaint);
                Log.d(TAG,"x:" + x+"   y:"+y);
            }
        }
    }

    /**
     * 绘制直线
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        float x = 0;
        float y = 0;
        for (int i = 0;i < count;i++){
            Path path = new Path();
            path.reset();
            for(int j = 0;j < count;j++){
                if (i == 0){
                    x = (float) ((rr * (j + 1)) * Math.cos(54 * Math.PI / 180));
                    y = (float) ( (rr * (j + 1)) * Math.sin(54 * Math.PI / 180));
                    path.lineTo(x , y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(i == 1){
                    x = (float) ((float) (-1) * ( (rr * (j + 1)) * Math.cos(54 * Math.PI / 180)));
                    y = (float) ((rr * (j + 1)) * Math.sin(54 * Math.PI / 180));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(i == 2){
                    x = (float) ((float) (-1) * ( (rr * (j + 1)) * Math.cos(18 * Math.PI / 180)));
                    y = (float) ((float) (-1) * ( (rr * (j + 1)) * Math.sin(18 * Math.PI / 180)));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(i == 3){
                    x =  0;
                    y = (- 1) * (rr * (j + 1));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(i == 4){
                    x = (float)  ( (rr * (j + 1)) * Math.cos(18 * Math.PI / 180));
                    y = (float) ((float) (-1) *  (rr * (j + 1)) * Math.sin(18 * Math.PI / 180));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
            }
            canvas.drawPath(path,linePaint);
            x = 0;
            y = 0;
        }
    }

    /**
     * 画多边形,顺时针绘制
     */
    private void drawPolygon(Canvas canvas){
        for(int i = 0; i < count; i++){
            Path path = new Path();
            path.reset();
            for(int j = 0; j < count; j++){
                if (j == 0){
                    float x = (float) ((rr * (i + 1)) * Math.cos(54 * Math.PI / 180));
                    float y = (float) ( (rr * (i + 1)) * Math.sin(54 * Math.PI / 180));
                    path.moveTo(x , y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(j == 1){
                    float x = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.cos(54 * Math.PI / 180)));
                    float y = (float) ((rr * (i + 1)) * Math.sin(54 * Math.PI / 180));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(j == 2){
                    float x = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.cos(18 * Math.PI / 180)));
                    float y = (float) ((float) (-1) * ( (rr * (i + 1)) * Math.sin(18 * Math.PI / 180)));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(j == 3){
                    float x =  0;
                    float y = (- 1) * (rr * (i + 1));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
                if(j == 4){
                    float x = (float)  ( (rr * (i + 1)) * Math.cos(18 * Math.PI / 180));
                    float y = (float) ((float) (-1) *  (rr * (i + 1)) * Math.sin(18 * Math.PI / 180));
                    path.lineTo(x,y);
                    Log.d(TAG,"x:"+x+"   y:"+y);
                }
            }
            path.close();
            canvas.drawPath(path,polygonPaint);
        }
    }
}
