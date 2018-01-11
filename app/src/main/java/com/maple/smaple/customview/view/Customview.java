package com.maple.smaple.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gaogu on 2018/1/10.
 */

public class Customview extends View {
    public Customview(Context context) {
        super(context);
        init(context,null,0);
    }

    public Customview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public Customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private  Paint paint;
    private  Paint pointPaint;
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //创建画笔
         //paint = new Paint();


        /**
         * Paint.setStyle(Style style) 设置绘制模式
         Paint.setColor(int color) 设置颜色
         Paint.setStrokeWidth(float width) 设置线条宽度
         Paint.setTextSize(float textSize) 设置文字大小
         Paint.setAntiAlias(boolean aa) 设置抗锯齿开关
         */
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE); // Style 修改为画线模式
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5.0f);
        paint.setAntiAlias(true);


        //绘制点的画笔
        pointPaint = new Paint();
       // pointPaint.setStyle(Paint.Style.FILL); // 默认
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeWidth(30.0f);
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);


        rectF = new RectF();
        rectF.left = 200;
        rectF.right = 700;
        rectF.top = 200;
        rectF.bottom = 500;


        path = new Path();
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
       // path.addArc(200, 200, 400, 400, -225, 225);
       // path.arcTo(400, 200, 600, 400, -180, 225, false);
       // path.lineTo(400, 542);

        arcRectF = new RectF();
        arcRectF.left = 200;
        arcRectF.top = 200;
        arcRectF.right = 400;
        arcRectF.bottom = 400;

        arcToRectF = new RectF(400, 200, 600, 400);

        path.addArc(arcRectF,-225,225);
        path.arcTo(arcToRectF,-180, 225, false);
        path.lineTo(400, 542);
    }

    //绘制批量点的坐标组
    float[] points = {30, 20, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};

    //绘制批量线的坐标组
    float[] pointsLines = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};


    private RectF rectF;

    private Path path; // 初始化 Path 对象
    private RectF arcRectF,arcToRectF;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //白的画布
        canvas.drawColor(Color.parseColor("#FFFFFF"));

        // 绘制一个圆
       // centerX centerY 是圆心的坐标 ，radius 是圆的半径 ，paint 画笔
       //drawCircle(float centerX, float centerY, float radius, Paint paint) 画圆

        //canvas.drawCircle(300, 300, 200, paint);

        //绘制一个矩形
       // float left, float top, float right, float bottom,矩形四条边的坐标，paint 画笔
       // drawRect(float left, float top, float right, float bottom, Paint paint) 画矩形
        //它还有两个重载方法 drawRect(RectF rect, Paint paint) 和 drawRect(Rect rect, Paint paint) ，让你可以直接填写 RectF 或 Rect 对象来绘制矩形。
       // canvas.drawRect(100, 100, 500, 500, paint);

        //绘制一个点
        //drawPoint(float x, float y, Paint paint) 画点
        //x 和 y 是点的坐标。点的大小可以通过 paint.setStrokeWidth(width) 来设置；点的形状可以通过  paint.setStrokeCap(cap) 来设置：ROUND 画出来是圆形的点，SQUARE 或 BUTT 画出来是方形的点。
        // （点还有形状？是的，反正 Google 是这么说的，你要问问 Google 去，我也很懵逼。）
        //注：Paint.setStrokeCap(cap) 可以设置点的形状，但这个方法并不是专门用来设置点的形状的，而是一个设置线条端点形状的方法。
        // 端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
       // canvas.drawPoint(300,300 , pointPaint);

        //绘制批量点
        //drawPoints(float[] pts, int offset, int count, Paint paint) / drawPoints(float[] pts, Paint paint) 画点（批量）
        //同样是画点，它和 drawPoint() 的区别是可以画多个点。pts 这个数组是点的坐标，每两个成一对；offset 表示跳过数组的前几个数再开始记坐标；count 表示一共要绘制几个点。
        // 说这么多你可能越读越晕，你还是自己试试吧，这是个看着复杂用着简单的方法。
        //canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */, 8 /* 一共绘制 8 个数（4 个点）*/, pointPaint);
       // canvas.drawPoints(points, pointPaint);

        //绘制椭圆
        //drawOval(float left, float top, float right, float bottom, Paint paint) 画椭圆
        //只能绘制横着的或者竖着的椭圆，不能绘制斜的（斜的倒是也可以，但不是直接使用 drawOval()，而是配合几何变换，后面会讲到）。
        // left, top, right, bottom 是这个椭圆的左、上、右、下四个边界点的坐标。
        //canvas.drawOval(400f,600,300,500, pointPaint);
        //canvas.drawOval(rectF,pointPaint);

        //绘制线
        //startX, startY, stopX, stopY 分别是线的起点和终点坐标
        //drawLine(float startX, float startY, float stopX, float stopY, Paint paint) 画线
        //由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。
        //canvas.drawLine(200, 200, 800, 500, pointPaint);

        //绘制批量线
        //drawLines(float[] pts, int offset, int count, Paint paint) / drawLines(float[] pts, Paint paint) 画线（批量）
       // canvas.drawLines(pointsLines, pointPaint);

        //绘制圆角矩形
        //drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) 画圆角矩形
        //left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
        //canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);
       // canvas.drawRoundRect(rectF,30,30,pointPaint);

        //绘制弧形或扇形
        //drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
        //drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；
        // startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），sweepAngle 是弧形划过的角度；
        // useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
        //canvas.drawArc(rectF,-90,90,true,pointPaint);

        //绘制自定义图形
        //drawPath(Path path, Paint paint) 画自定义图形
        //drawPath(path) 这个方法是通过描述路径的方式来绘制图形的，
        // 它的 path 参数就是用来描述图形路径的对象。path 的类型是 Path
        //canvas.drawPath(path, pointPaint);
        //Path 可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。把这些图形结合起来，就可以描述出很多复杂的图形。
        // 下面我就说一下具体的怎么把这些图形描述出来。
    }
}
