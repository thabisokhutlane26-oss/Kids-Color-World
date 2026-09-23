package com.kidscolorworld.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayDeque;

public class MainActivity extends Activity {

    private ImageView coloringPage;

    private Bitmap originalBitmap;
    private Bitmap workingBitmap;

    private int selectedColor = Color.RED;

    private static final int COLOR_TOLERANCE = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coloringPage = findViewById(R.id.coloringPage);

        loadColoringPicture();
        setupColorButtons();

        Button clearButton = findViewById(R.id.clearButton);

        clearButton.setOnClickListener(v -> {
            loadColoringPicture();

            Toast.makeText(
                    MainActivity.this,
                    "Picture cleared! 🎨",
                    Toast.LENGTH_SHORT
            ).show();
        });

        coloringPage.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (workingBitmap != null) {

                    float[] point = getBitmapCoordinates(
                            event.getX(),
                            event.getY()
                    );

                    if (point != null) {

                        int x = Math.round(point[0]);
                        int y = Math.round(point[1]);

                        floodFill(
                                workingBitmap,
                                x,
                                y,
                                selectedColor
                        );

                        coloringPage.setImageBitmap(workingBitmap);
                    }
                }

                return true;
            }

            return true;
        });
    }

    private void loadColoringPicture() {

        Drawable drawable = getResources().getDrawable(
                R.drawable.coloring_page
        );

        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {

            bitmap = ((BitmapDrawable) drawable)
                    .getBitmap();

        } else {

            bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888
            );

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(
                    0,
                    0,
                    canvas.getWidth(),
                    canvas.getHeight()
            );

            drawable.draw(canvas);
        }

        originalBitmap = bitmap.copy(
                Bitmap.Config.ARGB_8888,
                false
        );

        workingBitmap = originalBitmap.copy(
                Bitmap.Config.ARGB_8888,
                true
        );

        coloringPage.setImageBitmap(workingBitmap);
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

                selectedColor = color;

                Toast.makeText(
                        MainActivity.this,
                        "Color selected 🎨",
                        Toast.LENGTH_SHORT
                ).show();
            });
        }
    }

    private float[] getBitmapCoordinates(float touchX, float touchY) {

        if (workingBitmap == null) {
            return null;
        }

        float imageWidth = coloringPage.getWidth();
        float imageHeight = coloringPage.getHeight();

        float bitmapWidth = workingBitmap.getWidth();
        float bitmapHeight = workingBitmap.getHeight();

        float scale = Math.min(
                imageWidth / bitmapWidth,
                imageHeight / bitmapHeight
        );

        float displayedWidth = bitmapWidth * scale;
        float displayedHeight = bitmapHeight * scale;

        float left = (imageWidth - displayedWidth) / 2f;
        float top = (imageHeight - displayedHeight) / 2f;

        if (touchX < left ||
                touchX > left + displayedWidth ||
                touchY < top ||
                touchY > top + displayedHeight) {

            return null;
        }

        float bitmapX =
                (touchX - left) / scale;

        float bitmapY =
                (touchY - top) / scale;

        return new float[]{
                bitmapX,
                bitmapY
        };
    }

    private void floodFill(
            Bitmap bitmap,
            int startX,
            int startY,
            int newColor
    ) {

        if (startX < 0 ||
                startX >= bitmap.getWidth() ||
                startY < 0 ||
                startY >= bitmap.getHeight()) {

            return;
        }

        int targetColor = bitmap.getPixel(
                startX,
                startY
        );

        if (colorsAreSimilar(
                targetColor,
                newColor
        )) {
            return;
        }

        ArrayDeque<Point> queue =
                new ArrayDeque<>();

        queue.add(
                new Point(startX, startY)
        );

        boolean[][] visited = new boolean[
                bitmap.getWidth()
        ][
                bitmap.getHeight()
        ];

        visited[startX][startY] = true;

        while (!queue.isEmpty()) {

            Point point = queue.remove();

            int x = point.x;
            int y = point.y;

            int currentColor =
                    bitmap.getPixel(x, y);

            if (!colorsAreSimilar(
                    currentColor,
                    targetColor
            )) {
                continue;
            }

            bitmap.setPixel(
                    x,
                    y,
                    newColor
            );

            addPoint(
                    queue,
                    visited,
                    bitmap,
                    x + 1,
                    y
            );

            addPoint(
                    queue,
                    visited,
                    bitmap,
                    x - 1,
                    y
            );

            addPoint(
                    queue,
                    visited,
                    bitmap,
                    x,
                    y + 1
            );

            addPoint(
                    queue,
                    visited,
                    bitmap,
                    x,
                    y - 1
            );
        }
    }

    private void addPoint(
            ArrayDeque<Point> queue,
            boolean[][] visited,
            Bitmap bitmap,
            int x,
            int y
    ) {

        if (x < 0 ||
                y < 0 ||
                x >= bitmap.getWidth() ||
                y >= bitmap.getHeight()) {

            return;
        }

        if (visited[x][y]) {
            return;
        }

        int color = bitmap.getPixel(x, y);

        if (isBoundary(color)) {
            return;
        }

        visited[x][y] = true;

        queue.add(
                new Point(x, y)
        );
    }

    private boolean isBoundary(int color) {

        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return red < 80 &&
                green < 80 &&
                blue < 80;
    }

    private boolean colorsAreSimilar(
            int color1,
            int color2
    ) {

        int redDifference =
                Math.abs(
                        Color.red(color1)
                                -
                        Color.red(color2)
                );

        int greenDifference =
                Math.abs(
                        Color.green(color1)
                                -
                        Color.green(color2)
                );

        int blueDifference =
                Math.abs(
                        Color.blue(color1)
                                -
                        Color.blue(color2)
                );

        return redDifference <= COLOR_TOLERANCE
                &&
                greenDifference <= COLOR_TOLERANCE
                &&
                blueDifference <= COLOR_TOLERANCE;
    }

    private static class Point {

        int x;
        int y;

        Point(int x, int y) {

            this.x = x;
            this.y = y;
        }
    }
}
