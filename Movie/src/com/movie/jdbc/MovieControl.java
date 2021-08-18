package com.movie.jdbc;

import com.movie.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieControl {
    static private String url="jdbc:mysql://localhost:3306/movie?characterEncoding=utf8&autoReconnect=true";
    static private String db_id="root";
    static private String db_pw="1234";

    //영화 추가
    public void save(Movie movie) throws SQLException {
        String sql="insert into movie values(?,?,?,?,?,?,?,?,?)";
        Connection conn= null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;

        conn= DriverManager.getConnection(url,db_id,db_pw);

        //Sql문 완성
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,movie.mname);
        pstmt.setString(2,movie.genre);
        pstmt.setString(3,movie.director);
        pstmt.setString(4,movie.actor);
        pstmt.setInt(5,movie.open);
        pstmt.setInt(6,movie.time);
        pstmt.setString(7,movie.country);
        pstmt.setString(8,movie.ranking);
        pstmt.setBlob(9,movie.img);

        int row=pstmt.executeUpdate();
        System.out.println("추가된 row수: "+row);
    }


    //영화 목록
    public List<String> movie_list() throws SQLException {
        String sql="select mname from movie";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<String> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            list.add(rs.getString("mname"));
        }
        return list;
    }

    //영화 제목으로 조회
    public Movie movie_info(String mname) throws SQLException {
        String sql="SELECT * FROM movie WHERE mname = '"+mname+"'";
        System.out.println(sql);
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        Movie result=new Movie();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 완성
        rs=stmt.executeQuery(sql);

        if(rs.next()) {
            result.mname=rs.getString("mname");
            result.genre=rs.getString("genre");
            result.director=rs.getString("director");
            result.actor=rs.getString("actor");
            result.open=rs.getInt("open");
            result.time=rs.getInt("time");
            result.country=rs.getString("country");
            result.ranking=rs.getString("ranking");
            result.img=rs.getBlob("img");
            return result;
        }
        else{
            return null;
        }

    }


    //사용자가 본 영화 목록
    public List<String> watch_list(String id) throws SQLException {
        String sql="select mname from watch_list where id = '"+id+"'";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<String> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            list.add(rs.getString("mname"));
        }
        return list;
    }

    public void save_review(String id,String mname, int rating, String review) throws SQLException {
        String sql="insert into watch_list values(?,?,?,?)";
        Connection conn= null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;

        conn= DriverManager.getConnection(url,db_id,db_pw);

        //Sql문 완성
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        pstmt.setString(2,mname);
        pstmt.setInt(4,rating);
        pstmt.setString(3,review);
        int row=pstmt.executeUpdate();
        System.out.println("추가된 row수: "+row);
    }

    //평점, 후기 모두 가져옴
    public ArrayList<ArrayList<String>> get_all_review() throws SQLException {
        String sql="select * from watch_list";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<ArrayList<String>> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            ArrayList<String> temp=new ArrayList<>();
            temp.add(rs.getString("id"));
            temp.add(rs.getString("mname"));
            temp.add(Integer.toString(rs.getInt("rating")));
            temp.add(rs.getString("review"));

            list.add(temp);
        }

        return list;
    }

    //평점, 후기 가져오기(사용자 id, 영화 이름)
    public List<String> get_review(String id, String mname) throws SQLException {
        String sql="select rating, review from watch_list where id = '"+id+"' AND mname = '"+mname+"'";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<String> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        if(rs.next()){
            list.add(rs.getString("rating"));
            list.add(rs.getString("review"));
        }
        return list;
    }

    //평점, 후기 가져오기(영화 이름)
    public ArrayList<ArrayList<String>> get_review_by_mname(String mname) throws SQLException {
        String sql="select * from watch_list where mname = '"+mname+"'";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<ArrayList<String>> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            ArrayList<String> temp=new ArrayList<>();
            temp.add(rs.getString("id"));
            temp.add(rs.getString("mname"));
            temp.add(Integer.toString(rs.getInt("rating")));
            temp.add(rs.getString("review"));
            list.add(temp);
        }
        return list;
    }

    //평점, 후기 가져오기(영화 이름)
    public ArrayList<ArrayList<String>> get_review_by_id(String id) throws SQLException {
        String sql="select * from watch_list where id = '"+id+"'";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<ArrayList<String>> list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 실행
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            ArrayList<String> temp=new ArrayList<>();
            temp.add(rs.getString("id"));
            temp.add(rs.getString("mname"));
            temp.add(Integer.toString(rs.getInt("rating")));
            temp.add(rs.getString("review"));
            list.add(temp);
        }
        return list;
    }
}
