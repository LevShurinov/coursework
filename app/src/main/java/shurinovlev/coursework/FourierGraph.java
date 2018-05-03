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

public class FourierGraph extends View {


    Paint mPaint = new Paint();
    double A[] = Main.A;
    double f0[] = Main.f;
    double a[] = Main.a;

    double status = filter.status;
    double from = filter.from;
    double before = filter.before;

    double x1 = -1.0, y1 , x2=20.0, y2 = -1.0;

    int u1 = 0, v1 = 0, u2, v2;







    double Re, Im, f;
    int N = 1000;


    double dt = x2/N;
    double amplitude[] = new double[N];
    double frequency[] = new double[N];


    double function[] = new double[N];

    public FourierGraph(Context context) {
        super(context);

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);

    }

    public FourierGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public FourierGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public FourierGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    double f(int n) {
        return A[0] * Math.sin(2 * Math.PI * f0[0] * (n * dt) + a[0]) + A[1] * Math.sin(2 * Math.PI * f0[1] * (n * dt) + a[1]) + A[2] * Math.sin(2 * Math.PI * f0[2] * (n * dt) + a[2]) + A[3] * Math.sin(2 * Math.PI * f0[3] * (n * dt) + a[3]) + A[4] * Math.sin(2 * Math.PI * f0[4] * (n * dt) + a[4]) + A[5] * Math.sin(2 * Math.PI * f0[5] * (n * dt) + a[5]) + A[6] * Math.sin(2 * Math.PI * f0[6] * (n * dt) + a[6]) + A[7] * Math.sin(2 * Math.PI * f0[7] * (n * dt) + a[7]) + A[8] * Math.sin(2 * Math.PI * f0[8] * (n * dt) + a[8]) + A[9] * Math.sin(2 * Math.PI * f0[9] * (n * dt) + a[9]);
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



        // вычисление функции
        for ( int n = 0; n < N; n++ ){
            function[n] = f(n);
        }


        // вычисление Re и Im части
        Runnable ReIm = new Runnable() {
            @Override
            public void run() {

                for ( int n = 0; n < N; n++ ){

                    Re = 0.0;
                    Im = 0.0;
                    f = n*dt;
                    for (int j = 0; j < N; j++){
                        Re = Re + function[j]*Math.cos( 2 * Math.PI * f * j * dt );
                        Im = Im + function[j]*Math.sin( 2 * Math.PI * f * j * dt );
                    }
                    frequency[n]= f;
                    amplitude[n] = Math.hypot(Re, Im) *2/ N;
                }


            }
        };

        Thread thread_ReIm = new Thread(ReIm);

        thread_ReIm.start();


        if (thread_ReIm.isAlive()){
            try {
                thread_ReIm.join();
            }
            catch (InterruptedException e){}
        }




        /*
        for ( int n = 0; n < N; n++ ){
            Re = 0.0;
            Im = 0.0;
            w=0.0;
            w= n*dt;
            for (int j = -N; j < N; j++){
                Re = Re + Re(w, j);
                Im = Im + Im(w, j);
            }
            Am[n] = Math.sqrt(Re * Re + Im * Im)/N;
            ww[n]= w;

        }
        */

        double max=0;
        for (int i=0; i<10; i++){
            if(A[i]>max) max= A[i];
        }
        y1=max+1;

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(u1,v1,u2,v2,mPaint);


        if (status == 0) {
        }

        if (status == 1) {
            int k = 0;
            while ((int) (before / 0.02 + k) < N) {
                amplitude[(int) (before / 0.02 + k)] = 0;
                k++;
            }
        }

        if (status == 2) {
            int k = 0;
            while (k < (int) (from / 0.02 + 1)) {
                amplitude[k] = 0;
                k++;
            }
        }

        if (status == 3) {

            int k = (int) (from / 0.02);
            while (k < (int) (before / 0.02 + 1)) {
                amplitude[k] = 0;
                k++;
            }
        }

        if (status == 4) {

            int k = 0;
            while (k < (int) (from / 0.02 + 1)) {
                amplitude[k] = 0;
                k++;
            }
            k = 0;
            while ((int) (before / 0.02 + k) < N) {
                amplitude[(int) (before / 0.02 + k)] = 0;
                k++;
            }

        }




        mPaint.setColor(Color.BLACK);

        double x11=(x2-x1)/70;
        double y11=(y2-y1)/70;
        //ось х
        if( y1 * y2 <0){
            canvas.drawLine(u1,YtoV(0.0),u2,YtoV(0.0),mPaint);
        }
        //ось у
        if (x1 * x2 <0){
            canvas.drawLine(XtoU(0.0),v2,XtoU(0.0),v1, mPaint);
        }
        mPaint.setColor(Color.RED);
        for (int k = 0; k < N - 1; k++) {

            double x = frequency[k];
            double y = amplitude[k];
            double xx = frequency[k + 1];
            double yy = amplitude[k + 1];


            canvas.drawLine(XtoU(x), YtoV(y), XtoU(xx), YtoV(yy), mPaint);
        }
        mPaint.setColor(Color.BLACK);

        for (int i= (int) y2; i<y1; i++){
            canvas.drawText(String.valueOf(i), XtoU(-(x2-x1)/40), YtoV(i), mPaint);
            canvas.drawLine(XtoU(-x11), YtoV(i), XtoU(x11), YtoV(i), mPaint);
        }
        for (int i= (int) x1; i<x2; i++){
            if(i==0){}
            else {
                canvas.drawText(String.valueOf(i), XtoU(i), YtoV((y2-y1)/20), mPaint);
                canvas.drawLine(XtoU(i), YtoV(-y11), XtoU(i), YtoV(y11), mPaint);
            }
        }








    }
}
