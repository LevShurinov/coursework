package shurinovlev.coursework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by 1 on 28.03.2018.
 */

public class Graph extends View {

    Paint mPaint = new Paint();

    double x1 = -1, y1, x2 = 5, y2, h=0.01;

    double[] A = Main.A;
    double[] f = Main.f;
    double[] a = Main.a;

    int u1 = 0, v1 = 0, u2, v2;





    public Graph(Context context) {
        super(context);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
    }
    public Graph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Graph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public Graph(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    double f(double x) {
        return A[0] * Math.sin(2 * Math.PI * f[0] * x + a[0]) + A[1] * Math.sin(2 * Math.PI * f[1] * x + a[1]) + A[2] * Math.sin(2 * Math.PI * f[2] * x + a[2]) + A[3] * Math.sin(2 * Math.PI * f[3] * x + a[3]) + A[4] * Math.sin(2 * Math.PI * f[4] * x + a[4]) + A[5] * Math.sin(2 * Math.PI * f[5] * x + a[5]) + A[6] * Math.sin(2 * Math.PI * f[6] * x + a[6]) + A[7] * Math.sin(2 * Math.PI * f[7] * x + a[7]) + A[8] * Math.sin(2 * Math.PI * f[8] * x + a[8]) + A[9] * Math.sin(2 * Math.PI * f[9] * x + a[9]);
    }

    int XtoU(double x) {
        return (int) ((u2 - u1) * (x - x1) / (x2 - x1) + u1);
    }

    int YtoV(double y) {
        return (int) ((v2 - v1) * (y - y1) / (y2 - y1) + v1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        u2 = canvas.getWidth();
        v2 = canvas.getHeight();

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(u1,v1,u2,v2,mPaint);



        y1 = A[0]+A[1]+A[2]+A[3]+A[4]+A[5]+A[6]+A[7]+A[8]+A[9]+1;
        y2 = -y1;
        //график


        mPaint.setColor(Color.GREEN);
        for (double x=0.0; x<x2;){
            canvas.drawLine(XtoU(x),YtoV(f(x)),XtoU(x+h),YtoV(f(x+h)),mPaint);
            x+=h;
        }

        mPaint.setColor(Color.BLACK);
        //ось х
        if( y1 * y2 <0){
            canvas.drawLine(u1,YtoV(0.0),u2,YtoV(0.0),mPaint);
        }
        //ось у
        if (x1 * x2 <0){
            canvas.drawLine(XtoU(0.0),v2,XtoU(0.0),v1, mPaint);
        }


        for (int i= (int) y2; i<y1; i++){
            canvas.drawText(String.valueOf(i), XtoU(-(x2-x1)/20), YtoV(i), mPaint);
            canvas.drawLine(XtoU(-(x2-x1)/70), YtoV(i), XtoU((x2-x1)/70), YtoV(i), mPaint);
        }
        for (int i= (int) x1; i<x2; i++){
            if(i==0){}
            else {
                canvas.drawText(String.valueOf(i), XtoU(i), YtoV((y2-y1)/20), mPaint);
                canvas.drawLine(XtoU(i), YtoV(-(y2-y1)/70), XtoU(i), YtoV((y2-y1)/70), mPaint);
            }
        }





    }
}

