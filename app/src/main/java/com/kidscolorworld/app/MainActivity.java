package com.kidscolorworld.app;

import android.app.Activity;
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

        setupCategoryButton(
                R.id.animalsButton,
                "Animals",
                true
        );

        setupCategoryButton(
                R.id.toysButton,
                "Toys",
                false
        );

        setupCategoryButton(
                R.id.princessButton,
                "Princess",
                false
        );

        setupCategoryButton(
                R.id.princeButton,
                "Prince",
                false
        );

        setupCategoryButton(
                R.id.alphabetButton,
                "Alphabet",
                false
        );

        setupCategoryButton(
                R.id.numbersButton,
                "Numbers",
                false
        );

        setupCategoryButton(
                R.id.natureButton,
                "Nature",
                false
        );

        setupCategoryButton(
                R.id.vehiclesButton,
                "Vehicles",
                false
        );

        setupCategoryButton(
                R.id.foodButton,
                "Food",
                false
        );

        setupCategoryButton(
                R.id.everydayButton,
                "Everyday Things",
                false
        );
    }

    private void setupCategoryButton(
            int buttonId,
            String categoryName,
            boolean opensAnimals
    ) {

        Button button = findViewById(buttonId);

        button.setOnClickListener(v -> {

            if (opensAnimals) {

                showAnimalsScreen();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        categoryName + " coming soon! 🎨",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void showAnimalsScreen() {

        setContentView(R.layout.animals);

        setupAnimalButton(
                R.id.dogButton,
                "Dog"
        );

        setupAnimalButton(
                R.id.catButton,
                "Cat"
        );

        setupAnimalButton(
                R.id.lionButton,
                "Lion"
        );

        setupAnimalButton(
                R.id.elephantButton,
                "Elephant"
        );

        setupAnimalButton(
                R.id.monkeyButton,
                "Monkey"
        );

        setupAnimalButton(
                R.id.butterflyButton,
                "Butterfly"
        );

        setupAnimalButton(
                R.id.fishButton,
                "Fish"
        );

        setupAnimalButton(
                R.id.birdButton,
                "Bird"
        );

        Button backButton =
                findViewById(R.id.backButton);

        backButton.setOnClickListener(v ->
                showHomeScreen()
        );
    }

    private void setupAnimalButton(
            int buttonId,
            String animalName
    ) {

        Button button =
                findViewById(buttonId);

        button.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    animalName +
                            " coloring page coming next! 🎨",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public void onBackPressed() {

        showHomeScreen();
    }
}
