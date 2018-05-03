package shurinovlev.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Scanner;

public class Main extends AppCompatActivity {


    static double A[] = new double[10];
    static double f[] = new double[10];
    static double a[] = new double[10];
    EditText Amplitude, Phase, Frequency;
    Button start;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Amplitude = (EditText) findViewById(R.id.amplitude);
        Phase = (EditText) findViewById(R.id.phase);
        Frequency = (EditText) findViewById(R.id.frequency);
        start = (Button) findViewById(R.id.start);
    }

    public void onClick(View v) {


        for (i = 0; i < 10; i++) {
            A[i] = 0;
            f[i] = 0;
            a[i] = 0;
        }

        String amplitude = Amplitude.getText().toString();
        String frequency = Frequency.getText().toString();
        String phase = Phase.getText().toString();


        Scanner scanAmp = new Scanner(amplitude);
        Scanner scanFreq = new Scanner(frequency);
        Scanner scanPhase = new Scanner(phase);
        scanAmp.useDelimiter(" ");

        i = 0;
        while (scanAmp.hasNext()) {
            if (i < 10) {
                A[i] = Double.parseDouble(scanAmp.next());
                i++;
            } else break;
        }
        i = 0;
        while (scanFreq.hasNext()) {
            if (i < 10) {
                f[i] = Double.parseDouble(scanFreq.next());
                i++;
            } else break;
        }
        i = 0;
        while (scanPhase.hasNext()) {
            if (i < 10) {
                a[i] = Double.parseDouble(scanPhase.next());
                i++;
            } else break;
        }

        /*class thread extends AsyncTask<Double, Double, Double>{

            @Override
            protected Double doInBackground(Double... params) {




                return null;
            }
        }*/

        Intent intent = new Intent(Main.this, filter.class);


        startActivity(intent);


    }
}

