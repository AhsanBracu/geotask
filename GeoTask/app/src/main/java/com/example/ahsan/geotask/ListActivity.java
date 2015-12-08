package com.example.ahsan.geotask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.ahsan.geotask.model.Task;

public class ListActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    ArrayList<Task> arrayList=new ArrayList<Task>();

    ListView listView;
    Task_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseHelper=new DatabaseHelper(this);
        arrayList=databaseHelper.getTask();
        adapter=new Task_Adapter(getApplicationContext(),arrayList);
        listView=(ListView)findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


    }

}
