package com.kidscolorworld.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AnimalsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals);

        setupAnimalButtons();
    }

    private void setupAnimalButtons() {

        setupAnimalButton(
                R.id.dogButton,
                "Dog 🐶"
        );

        setupAnimalButton(
                R.id.catButton,
                "Cat 🐱"
        );

        setupAnimalButton(
                R.id.lionButton,
                "Lion 🦁"
        );

        setupAnimalButton(
                R.id.elephantButton,
                "Elephant 🐘"
        );

        setupAnimalButton(
                R.id.monkeyButton,
                "Monkey 🐵"
        );

        setupAnimalButton(
                R.id.butterflyButton,
                "Butterfly 🦋"
        );

        setupAnimalButton(
                R.id.fishButton,
                "Fish 🐟"
        );

        setupAnimalButton(
                R.id.birdButton,
                "Bird 🐦"
        );

        Button backButton = findViewById(
                R.id.backButton
        );

        backButton.setOnClickListener(v ->
                finish()
        );
    }

    private void setupAnimalButton(
            int buttonId,
            String animalName
    ) {

        Button button = findViewById(buttonId);

        button.setOnClickListener(v -> {

            Toast.makeText(
                    AnimalsActivity.this,
                    animalName + " coloring page coming next! 🎨",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }
}
