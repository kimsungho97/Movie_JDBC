package com.movie;

import java.sql.Blob;

public class Movie {
    public String mname;
    public String genre;
    public String director;
    public String actor;
    public int open;
    public int time;
    public String country;
    public String ranking;
    public Blob img;

    public String[] info_list(){
        String[] result=new String[8];
        result[0]=mname;
        result[1]=genre;
        result[2]=director;
        result[3]=actor;
        result[4]=Integer.toString(open);
        result[5]=Integer.toString(time);
        result[6]=country;
        result[7]=ranking;

        return result;
    }
}
