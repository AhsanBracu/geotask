package com.example.ahsan.geotask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.ahsan.geotask.model.Task;

public class MainActivity extends AppCompatActivity {
    Button submit,show_list;
    Button calender,start_Time,end_time;
    Button map_Show;
    EditText task_title,task_details;
    public static  int map_result=200;
    double lat,lng;
    TextView address,show_drawer;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseHelper databaseHelper;
    String date,time;
    DatabaseHelper mDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDatabaseHelper=new DatabaseHelper(this);
        show_drawer=(TextView)findViewById(R.id.drawer_Show);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        start_Time=(Button)findViewById(R.id.start_Time);
        start_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time();
            }
        });
        end_time=(Button)findViewById(R.id.end_Time);
        task_title=(EditText)findViewById(R.id.title);
        task_details=(EditText)findViewById(R.id.description);
        databaseHelper=new DatabaseHelper(this);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,mPlanetTitles));

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };


        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              onDrawerClicked(position);

            }
        });

        submit=(Button)findViewById(R.id.submit);
        show_list=(Button)findViewById(R.id.show_list);
        calender=(Button)findViewById(R.id.calendar);
        map_Show=(Button)findViewById(R.id.show_map);
        address=(TextView)findViewById(R.id.address);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatefield();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submittask();
            }
        });
        map_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplication(),MapActivity.class);

            //    startActivity(new Intent(getApplication(), MapActivity.class));
                startActivityForResult(i, 200);
            }
        });
        show_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),ListActivity.class));
            }
        });



    }

    public void time(){
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                time=hourOfDay+":"+minute;

            }
        }, mHour, mMinute, false);

        timePickerDialog.show();
    }

    public void onDrawerClicked(int position){

       show_drawer.setText("Position:::::: "+position);
        mDrawerLayout.closeDrawer(mDrawerList);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==200){

          //  if(resultCode==RESULT_OK){
            //String s=data.getStringExtra("Latitude");

                Toast.makeText(getApplication(),"yeeeeee ",Toast.LENGTH_LONG).show();
                lat=data.getDoubleExtra("Latitude",0.0);
                lng=data.getDoubleExtra("Longitude",0.0);
                   //Toast.makeText(getApplication(),"yeeeeee "+lat+"  "+lng+"  "+s,Toast.LENGTH_LONG).show();
                 address.setText("lat "+lat+" lng "+lng);

           // }


        }


    }

    public void submittask(){
        String title=task_title.getText().toString();
        String details=task_details.getText().toString();
        long date_millisecond;
        long time_millisecond;
      //  Task task=new Task(title,details,lat,lng,1,0.0,0.0,);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date d=simpleDateFormat.parse(date);
            date_millisecond=d.getTime();
            Toast.makeText(getApplicationContext(),"date  "+date+"  time "+time,Toast.LENGTH_LONG).show();
            simpleDateFormat=new SimpleDateFormat("HH:MM");
            java.util.Date time_t=simpleDateFormat.parse(time);
            time_millisecond=time_t.getTime();
            Toast.makeText(getApplicationContext(),"date  "+date_millisecond+"  time "+time_millisecond,Toast.LENGTH_LONG).show();
            Task task=new Task(title,details,lat,lng,1,time_millisecond,time_millisecond,date_millisecond);
            mDatabaseHelper.task_insert(task);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public void setDatefield(){

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Toast.makeText(getApplicationContext()," "+dayOfMonth + "-" + (monthOfYear + 1)+"-"+year,Toast.LENGTH_LONG).show();

                        date=dayOfMonth+"-"+(monthOfYear+1)+"-"+year;

                    }
                }, mYear, mMonth, mDay);

        dpd.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
