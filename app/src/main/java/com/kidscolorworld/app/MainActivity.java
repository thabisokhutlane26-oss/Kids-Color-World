package com.kidscolorworld.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView coloringPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coloringPage = findViewById(R.id.coloringPage);

        setupColorButtons();

        Button clearButton = findViewById(R.id.clearButton);

        clearButton.setOnClickListener(v -> {
            coloringPage.setImageResource(R.drawable.coloring_page);

            Toast.makeText(
                    MainActivity.this,
                    "Picture cleared!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private void setupColorButtons() {

        int[] colors = {
                Color.RED,
                Color.rgb(255, 152, 0),
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                Color.rgb(156, 39, 176),
                Color.rgb(255, 105, 180),
                Color.BLACK
        };

        int[] buttonIds = {
                R.id.colorRed,
                R.id.colorOrange,
                R.id.colorYellow,
                R.id.colorGreen,
                R.id.colorBlue,
                R.id.colorPurple,
                R.id.colorPink,
                R.id.colorBlack
        };

        for (int i = 0; i < buttonIds.length; i++) {

            ImageButton button = findViewById(buttonIds[i]);

            final int color = colors[i];

            button.setBackgroundColor(color);

            button.setOnClickListener(v -> {

                Toast.makeText(
                        MainActivity.this,
                        "Color selected!",
                        Toast.LENGTH_SHORT
                ).show();
            });
        }
    }
}
