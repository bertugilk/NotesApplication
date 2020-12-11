package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class Note_Edit extends AppCompatActivity {
    EditText editText;
    int noteID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__edit);
        editText=findViewById(R.id.ETnote);
        Intent intent=getIntent();
        noteID=intent.getIntExtra("noteID",-1);
        if(noteID!=-1){
            editText.setText(Notes.notes.get(noteID));
        }else {
            Notes.notes.add("");
            noteID=Notes.notes.size()-1;
            Notes.adapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Notes.notes.set(noteID,String.valueOf(charSequence));
                Notes.adapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext()
                        .getSharedPreferences("com.example.notesapplication", Context.MODE_PRIVATE);
                HashSet<String>set=new HashSet<>(Notes.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}