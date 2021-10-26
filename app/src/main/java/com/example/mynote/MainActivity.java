package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private dbManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{
            DBHelper.id,
            DBHelper.subject,
            DBHelper.desc
    };

    final int[] to = new int[]{
        R.id.id,
        R.id.title,
        R.id.desc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new dbManager(c.this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.listNotes);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView descTextView = view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modifyIntent = new Intent(getApplicationContext(), ModifyNotes.class);
                modifyIntent.putExtra("title",title);
                modifyIntent.putExtra("desc",desc);
                modifyIntent.putExtra("id",id);
                startActivity(modifyIntent);
            }
        });
    }

    public boolean onCeateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addRecord){
            Intent addMem = new Intent(this, addNote.class);
            startActivity(addMem);
        }
        return super.onOptionsItemSelected(item);
    }
}