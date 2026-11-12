package com.kidscolorworld.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHomeScreen();
    }

    private void showHomeScreen() {
        setContentView(R.layout.activity_main);

        setupCategoryButton(R.id.animalsButton, "Animals", true);
        setupCategoryButton(R.id.toysButton, "Toys", false);
        setupCategoryButton(R.id.princessButton, "Princess", false);
        setupCategoryButton(R.id.princeButton, "Prince", false);
        setupCategoryButton(R.id.alphabetButton, "Alphabet", false);
        setupCategoryButton(R.id.numbersButton, "Numbers", false);
        setupCategoryButton(R.id.natureButton, "Nature", false);
        setupCategoryButton(R.id.vehiclesButton, "Vehicles", false);
        setupCategoryButton(R.id.foodButton, "Food", false);
        setupCategoryButton(R.id.everydayButton, "Everyday Things", false);
    }

    private void setupCategoryButton(
            int buttonId,
            String categoryName,
            boolean opensAnimals
    ) {
        Button button = findViewById(buttonId);

        button.setOnClickListener(v -> {
            if (opensAnimals) {
                Intent intent = new Intent(
                        MainActivity.this,
                        AnimalsActivity.class
                );

                startActivity(intent);
            } else {
                Toast.makeText(
                        MainActivity.this,
                        categoryName + " coming soon! 🎨",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}