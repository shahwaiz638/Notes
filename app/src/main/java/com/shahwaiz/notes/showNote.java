package com.shahwaiz.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class showNote extends AppCompatActivity {

    TextView title,desc;
    ImageView iv;
    Button back;
    DBHelper db;
    Uri uri;
    String title1,desc1,uriString;
    int correct=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        db=new DBHelper(this);
        title=findViewById(R.id.showTitle);
        desc=findViewById(R.id.showDesc);
        iv=findViewById(R.id.showPic);
        back=findViewById(R.id.back2);
        Intent intent=getIntent();

        String title_txt=intent.getStringExtra("title");
        //title.setText(title_txt);

        note n;
        Cursor cursor=db.searchData(title_txt);
        if(cursor.getCount()>0)
            if (cursor.moveToFirst()) {
                do {
                    n = new note(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getLong(4), cursor.getString(5));
                } while (cursor.moveToNext());

                title1=n.getTitle();
                desc1=n.getDescription();
                uriString=n.getUri();
                if(n.getId()==1)
                {
                    //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(showNote.this,CheckPassword.class);
                    intent2.putExtra("title",title_txt);
                    startActivityForResult(intent2,1);



                }
                else
                {
                    title.setText(n.getTitle());
                    desc.setText(n.getDescription());

                    String uri_form=n.getUri();
                    Uri imgUri=Uri.parse(uriString);
                    iv.setImageURI(null);
                    iv.setImageURI(imgUri);

                }






                cursor.close();

            }

        //iv.setImageURI(uri);


                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
            if(resultCode==RESULT_OK)
            {
                correct=data.getIntExtra("correct",0);
                if(correct==1)
                {
                    title.setText(title1);
                    desc.setText(desc1);

                    String uri_form=uriString;
                    uri = Uri.parse(uri_form);
                    iv.setImageURI(uri);

                }
                else
                {
                    Toast.makeText(this, "incorrect Password", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
    }
}