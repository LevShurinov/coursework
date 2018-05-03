package shurinovlev.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by 1 on 03.05.2018.
 */

public class filter extends AppCompatActivity {

    static double from = 0, before = 0, status;
    Spinner spinner1;
    Button go;
    EditText from1, before1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        go = (Button) findViewById(R.id.go);
        from1 = (EditText) findViewById(R.id.from1);
        before1 = (EditText) findViewById(R.id.before1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    from1.setEnabled(false);
                    before1.setEnabled(false);
                    from1.setText("");
                    before1.setText("");
                    status = 0;
                }
                if (position == 1) {
                    from1.setEnabled(false);
                    before1.setEnabled(true);
                    from1.setText("");
                    status = 1;
                }
                if (position == 2) {
                    from1.setEnabled(true);
                    before1.setEnabled(false);
                    before1.setText("");
                    status = 2;
                }
                if (position == 3) {
                    from1.setEnabled(true);
                    before1.setEnabled(true);
                    status = 3;
                }
                if (position == 4) {
                    from1.setEnabled(true);
                    before1.setEnabled(true);
                    status = 4;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status == 0) {
                    from = 0;
                    before = 0;
                }
                if (status == 1) {
                    before = Double.parseDouble(before1.getText().toString());
                }
                if (status == 2) {
                    from = Double.parseDouble(from1.getText().toString());
                }
                if (status == 3) {
                    from = Double.parseDouble(from1.getText().toString());
                    before = Double.parseDouble(before1.getText().toString());
                }
                if (status == 4) {
                    from = Double.parseDouble(from1.getText().toString());
                    before = Double.parseDouble(before1.getText().toString());
                }


                Intent plots = new Intent(filter.this, Plots.class);
                startActivity(plots);
            }
        });


    }
}

