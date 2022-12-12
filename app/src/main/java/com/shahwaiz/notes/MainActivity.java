package com.shahwaiz.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener{

    ArrayList<note> modelArrayList;
    Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager lm;

    int sort=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerview);

        modelArrayList=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button add_note = findViewById(R.id.addNote);
        ImageButton search=findViewById(R.id.searchNote);
        Button delete=findViewById(R.id.deleteNote);
        Button update=findViewById(R.id.updateNote);
        ImageButton options=findViewById(R.id.options);



        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,options.class);
                startActivityForResult(intent,1);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, updateNote.class));
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, deleteNote.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, searchNote.class));
            }
        });
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addNote.class));
            }
        });

        DBHelper data = new DBHelper(this);
        //data.deleteAll();
        modelArrayList=this.getData();
        myAdapter = new Adapter(this, modelArrayList,this);
        recyclerView.setAdapter(myAdapter);
//        myAdapter = new Adapter(this, modelArrayList);
//        recyclerView.setAdapter(myAdapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        modelArrayList.clear();
        modelArrayList=getData();
        //modelArrayList.add(new note(1,"dead","kill me","no time"));
        myAdapter=new Adapter(MainActivity.this,modelArrayList,this);
        lm=new LinearLayoutManager(MainActivity.this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(lm);
    }


    public ArrayList<note> getData()
    {
        DBHelper db = new DBHelper(this);
        Cursor cursor = db.getData(sort);
        if(cursor.getCount()>0)
        if (cursor.moveToFirst()) {
            do {
                modelArrayList.add(new note(0, cursor.getString(1), cursor.getString(2), cursor.getLong(3),cursor.getLong(4),cursor.getString(5)));
            } while (cursor.moveToNext());
            cursor.close();
            return modelArrayList;
        }
        return modelArrayList;
    }

    @Override
    public void onNoteClick(int position) {
        note note1=modelArrayList.get(position);
        String title=note1.getTitle();
        Intent intent= new Intent(Intent.ACTION_OPEN_DOCUMENT).setClass(MainActivity.this,showNote.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            if(resultCode==RESULT_OK)
            {   sort=data.getIntExtra("sort",1);   }
    }
}