package com.example.firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase.Sinhvien;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;

public class Addlist extends AppCompatActivity {
    EditText edname, edmail;

    Button add;
    ImageButton back;

    ProgressDialog progressDialog;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);

        edname = (EditText) findViewById(R.id.edname);
        edmail = (EditText) findViewById(R.id.edmail);
        back = (ImageButton) findViewById(R.id.back);
        add = (Button) findViewById(R.id.add);
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        progressDialog = new ProgressDialog(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Addlist.this, List.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();
            }
        });

    }




    private void Upload() {
        progressDialog.show();
                    String name = edname.getText().toString().trim();
                    String mail = edmail.getText().toString().trim();
                    progressDialog.dismiss();
                    String UserId = databaseReference.push().getKey();
                    Sinhvien sv = new Sinhvien(name, mail);
                    databaseReference.child(UserId).setValue(sv);
                    Toast.makeText(Addlist.this, "Upload Success..", Toast.LENGTH_SHORT).show();
                    edname.setText("");
                    edmail.setText("");

            };



    }
