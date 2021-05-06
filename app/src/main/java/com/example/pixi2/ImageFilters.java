package com.example.pixi2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.Image;

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
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        int k = 0;
        for(int i=0;i<byteArray.length;++i){
            if(byteArray[i]!=Color.BLUE){
                byte temp= byteArray[i];
                byteArray[i]=byteArray[k];
                byteArray[k]=temp;
                if(i>k) k=i-k;
                if(i<k) k=k-1;
            }
        }

        Bitmap.Config configBmp = Bitmap.Config.valueOf(bitmap.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        bitmap_tmp.copyPixelsFromBuffer(buffer);
        return bitmap_tmp;

    }


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

    public static Bitmap filter3(){ //fekete
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int e,m,i=0,j=0;
        while(i< width && j<height){
            e=blackpixel(bitmap,i,j);
            m=blackpixel(bitmap,i,e);
            if(e!=-1 && m != -1){
                for(int y=e;y<m;++y){
                    if(bitmap.getPixel(i,y)!=Color.BLUE){
                        bitmap.setPixel(i,y,Color.WHITE);
                    }
                }
            }
        }
        return bitmap;
    }

    public static int blackpixel(Bitmap bitmap, int w, int h){
        int i=0;
        for(i=0; i<w;++i){
            if(bitmap.getColor(i,h)==Color.valueOf(0,0,0,255)){
                return i;
            }
        }
        return -1;
    }

}
