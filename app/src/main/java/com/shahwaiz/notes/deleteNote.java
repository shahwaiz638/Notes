package com.shahwaiz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteNote extends AppCompatActivity {

    DBHelper db;
    EditText deleteKey;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        db=new DBHelper(this);

        delete=findViewById(R.id.delete_button);
        deleteKey=findViewById(R.id.delete_key);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=deleteKey.getText().toString();
                boolean result=db.deleteData(key);
                if(result)
                {
                    Toast.makeText(deleteNote.this, "record Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(deleteNote.this, "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }


}