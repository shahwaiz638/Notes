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

public class updateNote extends AppCompatActivity {

    Button save_note,upload,lock;
    EditText oldTitle,newTitle,desc;
    DBHelper db;
    ImageView im;
    Uri uri;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        lock=findViewById(R.id.set_lock);
        save_note=findViewById(R.id.finish);
        oldTitle=findViewById(R.id.old_title);
        newTitle=findViewById(R.id.new_title);
        desc=findViewById(R.id.Description);
        upload=findViewById(R.id.upl_image);
        im=findViewById(R.id.image);
        db= new DBHelper(this);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_title_txt=oldTitle.getText().toString().trim();
                Intent intent=new Intent(updateNote.this,SetPassword.class);
                intent.putExtra("title",old_title_txt);
                startActivityForResult(intent,1);
                id=1;
                Toast.makeText(updateNote.this, "Note Locked", Toast.LENGTH_SHORT).show();
            }
        });
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_title_txt=oldTitle.getText().toString().trim();
                String new_title_txt=newTitle.getText().toString().trim();
                String description=desc.getText().toString().trim();
                long time= System.currentTimeMillis();
                String str="";
                if(uri!=null)
                {
                    str=uri.toString();
                }
                boolean check=db.UpdateData(id,old_title_txt,new_title_txt,description,str);
                if(check)
                {
                    Toast.makeText(updateNote.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(updateNote.this, "Error", Toast.LENGTH_SHORT).show();
                }



                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,100);
            }
        });



    }

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