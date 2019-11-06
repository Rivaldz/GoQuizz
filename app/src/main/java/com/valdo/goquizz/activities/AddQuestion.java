package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.valdo.goquizz.models.AddQuestionModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

import static android.text.TextUtils.isEmpty;

public class AddQuestion extends AppCompatActivity {

   private static final String TAG =  AddQuestion.class.getCanonicalName();
   private Button addQuestionBut,buttonA,buttonB,buttonC,buttonD;
   private ImageView avatarImage ;
   private EditText inputQuestion, answer1, answer2, answer3, answer4;

   private DatabaseReference mFirebaseDatabase;
   private FirebaseDatabase mFirebaseInstance;
   private StorageReference reference;

   private int userPin;
   private static final int Image_Request_Code = 7;
   private static String trueQuestion = null;

   private Bitmap imagae;
   private Uri filePathUri;
//   private String inputQuetionString, answer1String, answer2String, answer3String, answer4String;
   private static final int GALLERY_REQUEST_CODE = 1;
   private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        avatarImage = findViewById(R.id.avatarImage);
        inputQuestion = findViewById(R.id.input_question);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        answer1 = findViewById(R.id.input_answer1);
        answer2 = findViewById(R.id.input_answer2);
        answer3 = findViewById(R.id.input_answer3);
        answer4 = findViewById(R.id.input_answer4);
        addQuestionBut = findViewById(R.id.buttonAddQuestion);
        reference = FirebaseStorage.getInstance().getReference();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("BankSoal");

        Random rand = new Random();
        userPin = rand.nextInt(9000) + 100;

        addQuestionBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (!isEmpty(inputQuestion.getText().toString()) && !isEmpty(answer1.getText().toString()) && !isEmpty(answer3.getText().toString())
                       && !isEmpty(answer3.getText().toString()) && !isEmpty(answer4.getText().toString())){
//                   AddQuestionModel addQuestionModel = new AddQuestionModel(inputQuestion.getText().toString(), answer1.getText().toString(),answer2.getText().toString(),answer3.getText().toString()
//                   ,answer4.getText().toString());
                   AddQuestionModel  newQuest = new AddQuestionModel(answer1.getText().toString(),answer2.getText().toString(),answer3.getText().toString(),answer4.getText().toString(),trueQuestion);
                   mFirebaseDatabase.child(String.valueOf(userPin)).child(inputQuestion.getText().toString()).setValue(newQuest);
                   uplodeImage();
                   Toast.makeText(getBaseContext(), "Berhasil menambahkan Soal",Toast.LENGTH_SHORT).show();
                   inputQuestion.setText(null);
                   answer1.setText(null);
                   answer2.setText(null);
                   answer3.setText(null);
                   answer4.setText(null);

               }
               else {
                   Toast.makeText(getBaseContext(), "Isikan  pertanyaan dan semua jawaban",Toast.LENGTH_LONG).show();;
               }

            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueQuestion = "A";
                Toast.makeText(getBaseContext(), "Jawaban A ",Toast.LENGTH_SHORT).show();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueQuestion = "C";

                Toast.makeText(getBaseContext(), "Jawaban B ",Toast.LENGTH_SHORT).show();
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueQuestion = "B";

                Toast.makeText(getBaseContext(), "Jawaban C ",Toast.LENGTH_SHORT).show();

            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueQuestion = "D";

                Toast.makeText(getBaseContext(), "Jawaban D ",Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void uplodeImage(){
       avatarImage.setDrawingCacheEnabled(true);
       avatarImage.buildDrawingCache();
       Bitmap bitmap = ((BitmapDrawable) avatarImage.getDrawable()).getBitmap();
       ByteArrayOutputStream stream = new ByteArrayOutputStream();

       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
       byte[] bytes = stream.toByteArray();

       String namaFile = inputQuestion.getText().toString()+".jpg";
       String pathImage = "gambar/"+namaFile;
       UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
       uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               Toast.makeText(AddQuestion.this, "Berhasil menambahkan Gambar",Toast.LENGTH_SHORT).show();
           }
       })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(AddQuestion.this, "Gagal menambahkan",Toast.LENGTH_SHORT).show();
                   }
               });
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
                    filePathUri = imageUri;
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    final Bitmap bitmap  = Bitmap.createScaledBitmap(selectedImage, 1000, 500,false);
                    avatarImage.setImageBitmap(bitmap);
                    this.imagae = bitmap;

                }catch (IOException e){
                    Toast.makeText(this, "Gagal Mengamnbil gambar",Toast.LENGTH_LONG).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return  MimeTypeMap.getFileExtensionFromUrl(contentResolver.getType(uri));
    }

    public void getImage(View view) {
        Intent inten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(inten, GALLERY_REQUEST_CODE);

    }


}
