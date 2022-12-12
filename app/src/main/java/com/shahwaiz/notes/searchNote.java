package com.shahwaiz.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class searchNote extends AppCompatActivity implements Adapter.OnNoteListener {

    Button search,back;
    EditText searchKey;
    ArrayList<note> modelArrayList;
    Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager lm;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);

        db=new DBHelper(this);
        modelArrayList=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerview2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search=findViewById(R.id.search_button);
        searchKey=findViewById(R.id.search_key);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });



        myAdapter=new Adapter(searchNote.this,modelArrayList,this);
        recyclerView.setAdapter(myAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelArrayList.clear();
                String key=searchKey.getText().toString();

                Cursor cursor =db.searchData(key);
                if(cursor.getCount()>0)
                    if (cursor.moveToFirst()) {
                        do
                        {
                            modelArrayList.add(new note(0, cursor.getString(1), cursor.getString(2), cursor.getLong(3),cursor.getLong(4),cursor.getString(5)));
                        } while (cursor.moveToNext());

                        cursor.close();


                        recyclerView.getAdapter().notifyItemInserted(modelArrayList.size());
                        recyclerView.smoothScrollToPosition(modelArrayList.size());

            }

        }

        });




    }

    @Override
    public void onNoteClick(int position) {
        note note1=modelArrayList.get(position);
        String title=note1.getTitle();
        Intent intent= new Intent(searchNote.this,showNote.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}