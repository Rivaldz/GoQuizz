package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.ImplicitDirectBootViolation;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.valdo.goquizz.R;
import com.valdo.goquizz.models.KuisModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class AddQuiz extends AppCompatActivity {

    private Button btnUpload;
    private ImageView btnimg;
    private DatabaseReference database;
    private FirebaseDatabase firebaseDatabase;
    private StorageReference reference;
    private EditText title, description;
    private static final String TAG = AddQuiz.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    private int quizCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add quiz");
        btnUpload = findViewById(R.id.button_add);
        btnimg = findViewById(R.id.image_icon);
        title = findViewById(R.id.input_title);
        reference = FirebaseStorage.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference("Quiz");
        description = findViewById(R.id.input_description);

        Random rand = new Random();
        quizCode = rand.nextInt(9000) + 100;

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KuisModel kuisModel = new KuisModel(title.getText().toString(), description.getText().toString());
                database.child(String.valueOf(quizCode)).child(title.getText().toString()).child(description.getText().toString()).setValue(kuisModel);
                uploadImg();
                Toast.makeText(getBaseContext(),"Kuis berhasil di buat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), AddQuestion.class);
                startActivity(intent);

            }
        });
    }

    private void uploadImg(){
        btnimg.setDrawingCacheEnabled(true);
        btnimg.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable)btnimg.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);
        byte[] bytes = stream.toByteArray();

        String fileName = title.getText().toString()+".jpg";
        String pathimg = "gambar/"+fileName;
        UploadTask uploadTask = reference.child(pathimg).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               Toast.makeText(AddQuiz.this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddQuiz.this, "gagal menambahkan", Toast.LENGTH_SHORT).show();
            }
        });
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

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    btnimg.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Tidak Bisa load gambar" , Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
