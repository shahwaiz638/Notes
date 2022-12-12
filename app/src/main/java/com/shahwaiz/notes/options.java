package com.shahwaiz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class options extends AppCompatActivity {

    Button title_first,new_created,old_created,new_updated,old_updated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        title_first=findViewById(R.id.sort_title);
        new_created=findViewById(R.id.new_first);
        old_created=findViewById(R.id.old_first);
        new_updated=findViewById(R.id.latest_update_first);
        old_updated=findViewById(R.id.last_updated_first);

        title_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.putExtra("sort",1);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        new_created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.putExtra("sort",2);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        old_created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.putExtra("sort",3);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        new_updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.putExtra("sort",4);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        old_updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.putExtra("sort",5);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}