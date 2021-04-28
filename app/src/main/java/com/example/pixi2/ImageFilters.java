package com.example.pixi2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ImageFilters {

    public static Bitmap filter0(){
        Bitmap bitmap = FilterActivity.getImageBitmap();
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for(int i=0; i<height;++i){
            for(int j=0; j<width-1;++j){
                int pixelColor = bitmap.getPixel(i,j);
                int pixelColora = bitmap.getPixel(i,j+1);
                if(pixelColor== Color.BLACK && pixelColora != Color.BLACK){
                    bitmap.setPixel(i, j, Color.rgb(255, 0, 0));
                }
            }
        }
        return bitmap;
        
    }
}
