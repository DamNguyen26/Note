package com.example.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.Date;

public class addNote extends Activity implements View.OnClickListener {

    private Button addNote;
    private EditText titleEditText;
    private EditText descEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Note");
        setContentView(R.layout.add_note);

        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descEditText);
        addNote = findViewById(R.id.addRecord);

        dbManager = new DBManager(this);
        dbManager.open();
        addNote.setOnClickListener(this);
        Button cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addRecord: {
                Date date = java.util.Calendar.getInstance().getTime();
                final String name = titleEditText.getText().toString();
                final String desc = date.toString();

                dbManager.insert(name, desc);

                Intent main = new Intent(addNote.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            }
        }
    }
}
