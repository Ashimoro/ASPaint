package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    //private PaintView paintView;
    ImageButton bulka;
    private Paint brush = new Paint();
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bulka= (ImageButton) findViewById(R.id.buttonDraw);
        paintView =(PaintView) findViewById(R.id.paintOne);
        bulka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setPathColor(Color.RED);
            }
        });
    }
    public void onClick(View v) {
        if (v.getId() == R.id.buttonDraw) {
            brush.setColor(Color.BLUE);
        }
    }

}
