package com.shahwaiz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetPassword extends AppCompatActivity {

    String title1;
    EditText pass1;
    Button set1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        pass1=findViewById(R.id.password);
        set1=findViewById(R.id.set_password);

        Intent intent=getIntent();
        title1=intent.getStringExtra("title");

        DBPassword dbp =new DBPassword(this);

        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=pass1.getText().toString().trim();
                boolean check=dbp.setPassword(title1,code);
                if(check)
                {
                    Toast.makeText(SetPassword.this, "Note Locked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SetPassword.this, "Cant Lock", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }
}