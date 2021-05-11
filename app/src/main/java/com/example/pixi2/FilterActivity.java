package com.example.pixi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class FilterActivity extends AppCompatActivity {
    private static int SELECT_PICTURE = 200;
    Button add, save;
    static ImageView img;
    static Bitmap  bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        add= findViewById(R.id.button_add);
        save= findViewById(R.id.button_save);
        img = findViewById(R.id.kep_imageView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b  = FilterListFragment.getImageBitmap();
                MediaStore.Images.Media.insertImage(getContentResolver(), b, "title" , "description");
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    //KÉP ---> IMAGEVIEW
                    img.setImageURI(selectedImageUri);
                }
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), selectedImageUri);
                try {
                    bitmap = ImageDecoder.decodeBitmap(source);
                    //img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static Bitmap getImageBitmap(){
        return bitmap;
    }
}