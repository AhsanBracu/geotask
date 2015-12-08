package com.example.ahsan.geotask.model;

/**
 * Created by ahsan on 11/18/15.
 */
public class Task {

    public int id=0;
    public String title;
    public String detail;
    public double lat;
    public double lng;
    public int status;
    public double start_time;
    public double finish_time;
    public double due_date;
    
    
    public Task(String title,String detail,double lat,double lng,int status,double start_time,double finish_time, double due_date){
        
        this.title=title;
        this.detail=detail;
        this.lat=lat;
        this.lng=lng;
        this.status=status;
        this.start_time=start_time;
        this.finish_time=finish_time;
        this.due_date=due_date;
        
    } 

}
