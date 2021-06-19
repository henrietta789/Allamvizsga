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

    public static Bitmap filter3(){ //szinmérték csere
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int c=height-1;
        for(int i=0; i<width;++i){
            for(int j=0; j<height;++j){
                int colour = bitmap.getPixel(i, j);
                int red = Color.red(colour);
                int blue = Color.blue(colour);
                int green = Color.green(colour);
                int alpha = Color.alpha(colour);
                int colour2 = bitmap.getPixel(i, c);
                int red2 = Color.red(colour2);
                int blue2 = Color.blue(colour2);
                int green2 = Color.green(colour2);
                int alpha2 = Color.alpha(colour2);
                if(blue<blue2){
                    bitmap.setPixel(i, c, Color.argb(blue, red, green, alpha));
                    bitmap.setPixel(i, j, Color.argb(blue2, red2, green2, alpha2));
                }
                if(c>0){
                    c--;
                }
            }
        }
        return bitmap;
    }



    public static Bitmap filter4(){ // azt RGB-t BRG-re konvertálja
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for(int i=0; i<width;++i){
            for(int j=0; j<height;++j){
                int colour = bitmap.getPixel(i, j);
                int red = Color.red(colour);
                int blue = Color.blue(colour);
                int green = Color.green(colour);
                int alpha = Color.alpha(colour);
                bitmap.setPixel(i, j, Color.argb(blue, red, green, alpha));
            }
        }
        return bitmap;
    }
    public static Bitmap filter5(){ // pixelek szortolása az R- érték alapján
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n=0;
        while (n<100){
            for(int i=0; i<width;++i){
                for(int j=0; j<height-1;++j){
                    int colour = bitmap.getPixel(i, j);
                    int reda = Color.red(colour);
                    int bluea = Color.blue(colour);
                    int greena = Color.green(colour);
                    int alphaa = Color.alpha(colour);
                    colour = bitmap.getPixel(i, j+1);
                    int redb = Color.red(colour);
                    int blueb = Color.blue(colour);
                    int greenb = Color.green(colour);
                    int alphab = Color.alpha(colour);
                    if(reda<redb){
                        bitmap.setPixel(i,j,Color.argb(alphab,redb, greenb,blueb));
                        bitmap.setPixel(i,j+1,Color.argb(alphaa,reda, greena,bluea));
                    }
                }
            }
            ++n;
        }
        return bitmap;
    }
    public static Bitmap filter6(){ // pixelek szortolása az R- érték alapján vertikálisan
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n=0;
        while (n<150){
            for(int i=0; i<width-1;++i){
                for(int j=0; j<height;++j){
                    int colour = bitmap.getPixel(i, j);
                    int reda = Color.red(colour);
                    int bluea = Color.blue(colour);
                    int greena = Color.green(colour);
                    int alphaa = Color.alpha(colour);
                    colour = bitmap.getPixel(i+1, j);
                    int redb = Color.red(colour);
                    int blueb = Color.blue(colour);
                    int greenb = Color.green(colour);
                    int alphab = Color.alpha(colour);
                    if(reda<redb){
                        bitmap.setPixel(i,j,Color.argb(alphab,redb, greenb,blueb));
                        bitmap.setPixel(i+1,j,Color.argb(alphaa,reda, greena,bluea));
                    }
                }
            }
            ++n;
        }
        return bitmap;
    }
    public static Bitmap filter7(){ // fényesség alapján rendezi a sorokat
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0, R=0,B=0,G=0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i += 1) {
            int color = pixels[i];
            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            n++;
        }
        int BR = (R + B + G) / (n * 3);
        /*for(int i=0; i<width;++i){
            for(int j=0; j<height;++j){
                int colour = bitmap.getPixel(i, j);
                int red = Color.red(colour);
                int blue = Color.blue(colour);
                int green = Color.green(colour);
                int br=(red+green+blue)/3;
                if(BR<(br+100)){
                    bitmap.setPixel(i, j, Color.rgb(blue, red, green));
                }
            }
        }*/
       int  h=0;
        while (h<300){
            for(int i=0; i<width;++i){
                for(int j=0; j<height-1;++j){
                    int colour = bitmap.getPixel(i, j);
                    int reda = Color.red(colour);
                    int bluea = Color.blue(colour);
                    int greena = Color.green(colour);
                    int alphaa = Color.alpha(colour);
                    colour = bitmap.getPixel(i, j+1);
                    int redb = Color.red(colour);
                    int blueb = Color.blue(colour);
                    int greenb = Color.green(colour);
                    int alphab = Color.alpha(colour);
                    int br=(reda+greena+bluea)/3;
                    if(BR<(br+50)){
                        bitmap.setPixel(i,j,Color.argb(alphab,redb, greenb,blueb));
                        bitmap.setPixel(i,j+1,Color.argb(alphaa,reda, greena,bluea));
                    }
                }
            }
            ++h;
        }
        return bitmap;
    }
    public static Bitmap filter8(){ // fényesség alapján rendezi a sorokat horizontálisan
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0, R=0,B=0,G=0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i += 1) {
            int color = pixels[i];
            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            n++;
        }
        int BR = (R + B + G) / (n * 3);
        int  h=0;
        while (h<300){
            for(int i=0; i<width-1;++i){
                for(int j=0; j<height;++j){
                    int colour = bitmap.getPixel(i, j);
                    int reda = Color.red(colour);
                    int bluea = Color.blue(colour);
                    int greena = Color.green(colour);
                    int alphaa = Color.alpha(colour);
                    colour = bitmap.getPixel(i+1, j);
                    int redb = Color.red(colour);
                    int blueb = Color.blue(colour);
                    int greenb = Color.green(colour);
                    int alphab = Color.alpha(colour);
                    int br=(reda+greena+bluea)/3;
                    if(BR<(br+50)){
                        bitmap.setPixel(i,j,Color.argb(alphab,redb, greenb,blueb));
                        bitmap.setPixel(i+1,j,Color.argb(alphaa,reda, greena,bluea));
                    }
                }
            }
            ++h;
        }
        return bitmap;
    }
    public static Bitmap filter9(){ // fényesség alapján rendezi a sorokat középen
        Bitmap bitmaporiginal = FilterActivity.getImageBitmap();
        Bitmap bitmap = bitmaporiginal.copy(Bitmap.Config.ARGB_8888, true);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0, R=0,B=0,G=0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i += 1) {
            int color = pixels[i];
            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            n++;
        }
        int BR = (R + B + G) / (n * 3);
        int  h=0;
        while (h<300){
            for(int i=width/3; i<width/2;++i){
                for(int j=0; j<height-1;++j){
                    int colour = bitmap.getPixel(i, j);
                    int reda = Color.red(colour);
                    int bluea = Color.blue(colour);
                    int greena = Color.green(colour);
                    int alphaa = Color.alpha(colour);
                    colour = bitmap.getPixel(i, j+1);
                    int redb = Color.red(colour);
                    int blueb = Color.blue(colour);
                    int greenb = Color.green(colour);
                    int alphab = Color.alpha(colour);
                    int br=(reda+greena+bluea)/3;
                    if(BR<(br+50)){
                        bitmap.setPixel(i,j,Color.argb(alphab,redb, greenb,blueb));
                        bitmap.setPixel(i,j+1,Color.argb(alphaa,reda, greena,bluea));
                    }
                }
            }
            ++h;
        }
        return bitmap;
    }
}
