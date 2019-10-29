package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.valdo.goquizz.R;

import java.io.IOException;

public class AddQuestion extends AppCompatActivity {

   private static final String TAG =  AddQuestion.class.getCanonicalName();
   private ImageButton imageButton ;
   private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageButton = findViewById(R.id.add_imageQuestion);
        setContentView(R.layout.activity_add_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Question");

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent inten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(inten, GALLERY_REQUEST_CODE);
//
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);

                }catch (IOException e){
                    Toast.makeText(this, "Gagal Mengamnbil gambar",Toast.LENGTH_LONG).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void getImage(View view) {
                        Intent inten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(inten, GALLERY_REQUEST_CODE);
    }
}
