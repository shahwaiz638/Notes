package com.shahwaiz.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class addNote extends AppCompatActivity {

    Button save_note,upload,lock;
    EditText title,desc;
    DBHelper db;
    ImageView im;
    Uri uri;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        lock=findViewById(R.id.set_lock);
        save_note=findViewById(R.id.finish);
        title=findViewById(R.id.title_edit);
        desc=findViewById(R.id.Description);
        upload=findViewById(R.id.upl_image);
        im=findViewById(R.id.image);
        db= new DBHelper(this);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=1;
                String title_txt=title.getText().toString().trim();
                Intent intent=new Intent(addNote.this,SetPassword.class);
                intent.putExtra("title",title_txt);
                startActivityForResult(intent,1);
            }
        });
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title_txt=title.getText().toString().trim();
                String description=desc.getText().toString().trim();
                long time= System.currentTimeMillis();
                String str="";
                if(uri!=null)
                {
                    str=uri.toString();
                }
                boolean check=db.insertData(id,title_txt,description,time,str);
                if(check)
                {
                    Toast.makeText(addNote.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(addNote.this, "Error", Toast.LENGTH_SHORT).show();
                }



                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
            if(resultCode==RESULT_OK)
            {
                uri=data.getData();
                im.setImageURI(uri);
            }
    }
}