package com.kidscolorworld.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayDeque;

public class ColoringActivity extends Activity {

    private ImageView coloringPage;
    private Bitmap workingBitmap;

    private int selectedColor = Color.RED;

    private static final int COLOR_TOLERANCE = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloring);

        coloringPage = findViewById(R.id.coloringPage);

        loadPicture();
        setupColors();

        Button clearButton = findViewById(R.id.clearButton);

        clearButton.setOnClickListener(v -> {
            loadPicture();

            Toast.makeText(
                    this,
                    "Picture cleared! 🎨",
                    Toast.LENGTH_SHORT
            ).show();
        });

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        coloringPage.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                float[] point = getBitmapCoordinates(
                        event.getX(),
                        event.getY()
                );

                if (point != null) {

                    floodFill(
                            workingBitmap,
                            Math.round(point[0]),
                            Math.round(point[1]),
                            selectedColor
                    );

                    coloringPage.setImageBitmap(
                            workingBitmap
                    );
                }

                return true;
            }

            return true;
        });
    }

    private void loadPicture() {

        Drawable drawable = getResources().getDrawable(
                R.drawable.coloring_page
        );

        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {

            bitmap = ((BitmapDrawable) drawable).getBitmap();

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

        workingBitmap = bitmap.copy(
                Bitmap.Config.ARGB_8888,
                true
        );

        coloringPage.setImageBitmap(workingBitmap);
    }

    private void setupColors() {

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

        int[] ids = {
                R.id.colorRed,
                R.id.colorOrange,
                R.id.colorYellow,
                R.id.colorGreen,
                R.id.colorBlue,
                R.id.colorPurple,
                R.id.colorPink,
                R.id.colorBlack
        };

        for (int i = 0; i < ids.length; i++) {

            ImageButton button = findViewById(ids[i]);

            final int color = colors[i];

            button.setBackgroundColor(color);

            button.setOnClickListener(v ->
                    selectedColor = color
            );
        }
    }

    private float[] getBitmapCoordinates(
            float x,
            float y
    ) {

        if (workingBitmap == null) {
            return null;
        }

        float viewWidth = coloringPage.getWidth();
        float viewHeight = coloringPage.getHeight();

        float bitmapWidth = workingBitmap.getWidth();
        float bitmapHeight = workingBitmap.getHeight();

        float scale = Math.min(
                viewWidth / bitmapWidth,
                viewHeight / bitmapHeight
        );

        float displayedWidth = bitmapWidth * scale;
        float displayedHeight = bitmapHeight * scale;

        float left =
                (viewWidth - displayedWidth) / 2f;

        float top =
                (viewHeight - displayedHeight) / 2f;

        if (x < left ||
                x > left + displayedWidth ||
                y < top ||
                y > top + displayedHeight) {

            return null;
        }

        return new float[]{
                (x - left) / scale,
                (y - top) / scale
        };
    }

    private void floodFill(
            Bitmap bitmap,
            int startX,
            int startY,
            int newColor
    ) {

        if (startX < 0 ||
                startY < 0 ||
                startX >= bitmap.getWidth() ||
                startY >= bitmap.getHeight()) {

            return;
        }

        int targetColor =
                bitmap.getPixel(startX, startY);

        if (targetColor == newColor) {
            return;
        }

        ArrayDeque<Point> queue =
                new ArrayDeque<>();

        queue.add(
                new Point(startX, startY)
        );

        boolean[][] visited =
                new boolean[
                        bitmap.getWidth()
                ][
                        bitmap.getHeight()
                ];

        visited[startX][startY] = true;

        while (!queue.isEmpty()) {

            Point p = queue.remove();

            int current =
                    bitmap.getPixel(p.x, p.y);

            if (!similar(current, targetColor)) {
                continue;
            }

            bitmap.setPixel(
                    p.x,
                    p.y,
                    newColor
            );

            add(queue, visited, bitmap,
                    p.x + 1, p.y);

            add(queue, visited, bitmap,
                    p.x - 1, p.y);

            add(queue, visited, bitmap,
                    p.x, p.y + 1);

            add(queue, visited, bitmap,
                    p.x, p.y - 1);
        }
    }

    private void add(
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

        int color =
                bitmap.getPixel(x, y);

        if (isBlackLine(color)) {
            return;
        }

        visited[x][y] = true;

        queue.add(new Point(x, y));
    }

    private boolean isBlackLine(int color) {

        return Color.red(color) < 80 &&
                Color.green(color) < 80 &&
                Color.blue(color) < 80;
    }

    private boolean similar(
            int a,
            int b
    ) {

        return Math.abs(
                Color.red(a) - Color.red(b)
        ) <= COLOR_TOLERANCE
                &&
                Math.abs(
                        Color.green(a) -
                                Color.green(b)
                ) <= COLOR_TOLERANCE
                &&
                Math.abs(
                        Color.blue(a) -
                                Color.blue(b)
                ) <= COLOR_TOLERANCE;
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
