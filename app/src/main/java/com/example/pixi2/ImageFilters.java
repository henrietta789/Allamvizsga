package com.example.pixi2;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.palette.graphics.Palette;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class ImageFilters {


    public static Bitmap filter0(){ //random generált helyekre rakja be a kép 0-dik pixelével megegyező színű pixeleket
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        int k = new Random().nextInt(byteArray.length);
        for(int i=0;i<byteArray.length-1;++i){
            if(byteArray[i]==byteArray[0]){
                byteArray[i]=byteArray[k];
                byteArray[k]=byteArray[i+1];
                k = new Random().nextInt(byteArray.length);
            }
        }

        Bitmap.Config configBmp = Bitmap.Config.valueOf(bitmap.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        bitmap_tmp.copyPixelsFromBuffer(buffer);
        return bitmap_tmp;
        
    }
    public static Bitmap filter1(){
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap myBitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int [] allpixels = new int [myBitmap.getHeight() * myBitmap.getWidth()];

        myBitmap.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());

        for(int i = 0; i < allpixels.length/4; i++) {
            for (int j = 0; j < allpixels.length/4; j++) {
                if (allpixels[i] >= allpixels[j]) {
                    int temp = allpixels[i];
                    allpixels[i] = allpixels[j];
                    allpixels[j] = temp;
                }
            }
        }
        myBitmap.setPixels(allpixels,0,myBitmap.getWidth(),0, 0, myBitmap.getWidth(),myBitmap.getHeight());
        return myBitmap;

    }
    /*public static  byte[][] make2D(byte[] b,int size){
        byte[][] b2= new byte[size/size][size/2];
        int k=0;
        for(int i=0;i<size/2;++i){
            for(int j=0;j<size/2;++j){
                b2[i][j]=b[k];
                ++k;
            }
        }
        return b2;
    }
    public static  byte[] make1D(byte[][] b,int size){
        byte[] b1= new byte[size*size];
        int k=0;
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                b1[k]=b[i][j];
                ++k;
            }
        }
        return b1;
    }*/

    public static Bitmap filter2(){ // pirosra festi (fekete pixeleket leginkább)
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
