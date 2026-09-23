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

        Button animalsButton = findViewById(R.id.animalsButton);
        Button toysButton = findViewById(R.id.toysButton);
        Button princessButton = findViewById(R.id.princessButton);
        Button princeButton = findViewById(R.id.princeButton);
        Button alphabetButton = findViewById(R.id.alphabetButton);
        Button numbersButton = findViewById(R.id.numbersButton);
        Button natureButton = findViewById(R.id.natureButton);
        Button vehiclesButton = findViewById(R.id.vehiclesButton);
        Button foodButton = findViewById(R.id.foodButton);
        Button everydayButton = findViewById(R.id.everydayButton);

        animalsButton.setOnClickListener(v ->
                showComingSoon("Animals 🐶"));

        toysButton.setOnClickListener(v ->
                showComingSoon("Toys 🧸"));

        princessButton.setOnClickListener(v ->
                showComingSoon("Princess 👸"));

        princeButton.setOnClickListener(v ->
                showComingSoon("Prince 🤴"));

        alphabetButton.setOnClickListener(v ->
                showComingSoon("Alphabet 🔤"));

        numbersButton.setOnClickListener(v ->
                showComingSoon("Numbers 🔢"));

        natureButton.setOnClickListener(v ->
                showComingSoon("Nature 🌳"));

        vehiclesButton.setOnClickListener(v ->
                showComingSoon("Vehicles 🚗"));

        foodButton.setOnClickListener(v ->
                showComingSoon("Food 🍎"));

        everydayButton.setOnClickListener(v ->
                showComingSoon("Everyday Things 🏠"));
    }

    private void showComingSoon(String category) {

        Toast.makeText(
                MainActivity.this,
                category + " coloring pages coming soon! 🎨",
                Toast.LENGTH_SHORT
        ).show();
    }
}
