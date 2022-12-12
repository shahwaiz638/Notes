package com.shahwaiz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckPassword extends AppCompatActivity {

    String title;
    EditText pass;
    Button set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        pass=findViewById(R.id.password2);
        set=findViewById(R.id.Check_password);

        Intent intent=getIntent();
        title=intent.getStringExtra("title");

        DBPassword dbp =new DBPassword(this);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correct=false;
                
                
                
                //while(correct==false)
                {
                    String code=pass.getText().toString().trim();
                    Intent intent2 =new Intent();
                    correct=dbp.checkPassword(title,code);
                    if(correct==false)
                    {
                        //Toast.makeText(CheckPassword.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        intent2.putExtra("correct",0);
                    }
                    else
                    {
                        //Toast.makeText(CheckPassword.this, "Correct", Toast.LENGTH_SHORT).show();
                        intent2.putExtra("correct",1);
                    }
                    setResult(RESULT_OK,intent2);
                    finish();
                }




            }
        });
    }
}