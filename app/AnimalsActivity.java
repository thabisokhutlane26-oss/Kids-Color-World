package com.kidscolorworld.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AnimalsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals);

        Button dogButton = findViewById(R.id.dogButton);

        dogButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AnimalsActivity.this,
                    ColoringActivity.class
            );

            startActivity(intent);
        });

        setupComingSoon(R.id.catButton);
        setupComingSoon(R.id.lionButton);
        setupComingSoon(R.id.elephantButton);
        setupComingSoon(R.id.monkeyButton);
        setupComingSoon(R.id.butterflyButton);
        setupComingSoon(R.id.fishButton);
        setupComingSoon(R.id.birdButton);

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());
    }

    private void setupComingSoon(int buttonId) {

        Button button = findViewById(buttonId);

        button.setOnClickListener(v ->
                android.widget.Toast.makeText(
                        AnimalsActivity.this,
                        "This animal is coming next! 🎨",
                        android.widget.Toast.LENGTH_SHORT
                ).show()
        );
    }
}
