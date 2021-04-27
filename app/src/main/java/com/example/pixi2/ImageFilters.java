package com.example.pixi2;

import android.graphics.Bitmap;
import android.media.Image;

public class ImageFilters {
    Image img;
    public ImageFilters(Image img) {
        this.img = img;
    }

    public void filter1(Image img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                //result[row][col] = img.getPixel(col, row);
            }
        }
    }

}
