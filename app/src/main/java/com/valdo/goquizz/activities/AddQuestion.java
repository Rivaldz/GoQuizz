package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdo.goquizz.R;

import java.io.IOException;
import java.io.InputStream;

public class AddQuestion extends AppCompatActivity {

   private static final String TAG =  AddQuestion.class.getCanonicalName();
   private ImageView avatarImage ;
   private DatabaseReference mFirebaseDatabase;
   private FirebaseDatabase mFirebaseInstance;
   private EditText inputQuestion, answer1, answer2, answer3, answer4;
   private String  userPin;
   private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        avatarImage = findViewById(R.id.avatarImage);
        inputQuestion = findViewById(R.id.input_question);
        answer1 = findViewById(R.id.input_answer1);
        answer2 = findViewById(R.id.input_answer2);
        answer3 = findViewById(R.id.input_answer3);
        answer4 = findViewById(R.id.input_answer4);





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
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    final Bitmap bitmap  = Bitmap.createScaledBitmap(selectedImage, 1000, 500,false);
                    avatarImage.setImageBitmap(bitmap);

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
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
}
