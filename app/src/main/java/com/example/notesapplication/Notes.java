package com.example.notesapplication;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class Notes extends AppCompatActivity {
    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter adapter;
    ListView listView;
    Context context=this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addNote){
            Intent intent=new Intent(getApplicationContext(),Note_Edit.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId()==R.id.exit){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        listView=findViewById(R.id.notes);

        SharedPreferences sharedPreferences=getApplicationContext()
                .getSharedPreferences("com.example.notesapplication", Context.MODE_PRIVATE);
        HashSet<String>set= (HashSet<String>) sharedPreferences.getStringSet("notes",null);
        if(set==null){
            notes.add("Örnek not");
        }else{
            notes=new ArrayList(set);
        }

        adapter=new ArrayAdapter(context, android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),Note_Edit.class);
                intent.putExtra("noteID",i);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int delete=i;
                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Emin misin?")
                        .setMessage("Bu notu silmek istiyor musun?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(delete);
                                adapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences=getApplicationContext()
                                        .getSharedPreferences("com.example.notesapplication", Context.MODE_PRIVATE);
                                HashSet<String> set=new HashSet<>(Notes.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("Hayır",null)
                        .show();
                return true;
            }
        });
    }
}
