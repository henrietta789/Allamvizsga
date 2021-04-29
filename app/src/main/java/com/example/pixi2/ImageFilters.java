package com.example.pixi2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ImageFilters {

    public static Bitmap filter0(){
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for(int i=0; i<width;++i){
            for(int j=0; j<height-1;++j){
                int pixelColor = bitmap.getPixel(i,j);
                int pixelColora = bitmap.getPixel(i,j+1);
                if(pixelColor== Color.BLACK && pixelColora != Color.BLACK){
                    bitmap.setPixel(i, j, Color.rgb(255, 0, 0));
                }
            }
        }
        return bitmap;
        
    }
    public static Bitmap filter1(){
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        ByteBuffer buffer = ByteBuffer.allocate(bitmap.getRowBytes()*bitmap.getHeight());
        bitmap.copyPixelsToBuffer(buffer);

        return bitmap;

    }
    public static Bitmap filter2(){
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for(int i=0; i<width;++i){
            for(int j=0; j<height-1;++j){
                if(bitmap.getPixel(i,j)<bitmap.getPixel(i,j+1)){
                    bitmap.setPixel(i, j, Color.rgb(255, 0, 0));
                }
            }
        }
        return bitmap;

    }
}
