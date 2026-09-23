package com.kidscolorworld.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCategoryButtons();
    }

    private void setupCategoryButtons() {

        setupButton(
                R.id.animalsButton,
                "Animals 🐶"
        );

        setupButton(
                R.id.toysButton,
                "Toys 🧸"
        );

        setupButton(
                R.id.princessButton,
                "Princess 👸"
        );

        setupButton(
                R.id.princeButton,
                "Prince 🤴"
        );

        setupButton(
                R.id.alphabetButton,
                "Alphabet 🔤"
        );

        setupButton(
                R.id.numbersButton,
                "Numbers 🔢"
        );

        setupButton(
                R.id.natureButton,
                "Nature 🌳"
        );

        setupButton(
                R.id.vehiclesButton,
                "Vehicles 🚗"
        );

        setupButton(
                R.id.foodButton,
                "Food 🍎"
        );

        setupButton(
                R.id.everydayButton,
                "Everyday Things 🏠"
        );
    }

    private void setupButton(
            int buttonId,
            String categoryName
    ) {

        Button button = findViewById(buttonId);

        button.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    categoryName + " coming soon! 🎨",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }
}
