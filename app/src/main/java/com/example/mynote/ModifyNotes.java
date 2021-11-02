package com.example.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNotes extends Activity implements View.OnClickListener {

    private EditText titleText;
    private EditText descText;
    private Button btnUpdate, btnDelete;

    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Note");
        setContentView(R.layout.activity_modify_notes);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = findViewById(R.id.titleEditText);
        descText = findViewById(R.id.descEditText);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();
                dbManager.update(_id,title,desc);
                this.returnHome();
                break;

            case R.id.btnDelete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }
    public void returnHome(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}