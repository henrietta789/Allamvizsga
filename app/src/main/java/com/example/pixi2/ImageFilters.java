package com.example.pixi2;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.nio.ByteBuffer;
import java.util.Random;

public class ImageFilters {

   /*public static int[] Szinek(Bitmap bitmap){
       int s=bitmap.getWidth()*bitmap.getHeight();
       int [] szin= new int[s];
        int i=0;
       for(int x=0;x<bitmap.getWidth();++x){
           for(int y=0;y<bitmap.getHeight();++y) {
               if (bitmap.getPixel(x, y) == Color.BLACK) {
                   szin[i] = 0;
               }
               if (bitmap.getPixel(x, y) == Color.RED) {
                   szin[i] = 1;
               }
               if (bitmap.getPixel(x, y) == Color.GREEN) {
                   szin[i] = 2;
               }
               if (bitmap.getPixel(x, y) == Color.BLUE) {
                   szin[i] = 3;
               }++i;
           }
       }
    return szin;
   }*/

    public static Bitmap filter0(){
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int r = new Random().nextInt(height);
        for(int i=0; i<width;++i){
            for(int j=0; j<height-1;++j){
                if(bitmap.getPixel(i,j)!=bitmap.getPixel(i,j+1)){
                    bitmap.setPixel(i, j, bitmap.getPixel(i,j+1));
                }
                r = new Random().nextInt(height);
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
