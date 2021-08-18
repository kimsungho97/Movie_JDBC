package com.movie.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserControl {
    static private String url="jdbc:mysql://localhost:3306/movie?characterEncoding=utf8&autoReconnect=true";
    static private String db_id="root";
    static private String db_pw="1234";

    //사용자 회원가입
    public void save(String id, String pw, String uname, int age) throws SQLException {
        String sql="insert into user(id,pw,uname,age) values(?,?,?,?)";
        Connection conn= null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;

        conn= DriverManager.getConnection(url,db_id,db_pw);

        //Sql문 완성
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        pstmt.setString(2,pw);
        pstmt.setString(3,uname);
        pstmt.setInt(4,age);

        int row=pstmt.executeUpdate();
        System.out.println("추가된 row수: "+row);
    }

    //사용자 id 목록
    public List<String> user_list() throws SQLException {
        String sql="select id from user";
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<String> id_list=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 완성
        rs=stmt.executeQuery(sql);

        while(rs.next()){
            id_list.add(rs.getString("id"));
        }
        return id_list;
    }

    //사용자 id를 기준으로 개인 정보 가져옴
    public List<String> user_info(String id) throws SQLException {
        String sql="SELECT * FROM USER WHERE id = '"+id+"'";
        System.out.println(sql);
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs= null;
        ArrayList<String> id_info=new ArrayList<>();

        conn= DriverManager.getConnection(url,db_id,db_pw);
        stmt= conn.createStatement();
        //Sql문 완성
        rs=stmt.executeQuery(sql);

        if(rs.next()) {
            id_info.add(rs.getString("id"));
            id_info.add(rs.getString("pw"));
            id_info.add(rs.getString("uname"));
            id_info.add(Integer.toString(rs.getInt("age")));
            return id_info;
        }
        else{
            return null;
        }

    }

    //사용자 삭제(사용자 id를 받음)
    public void remove(String id) throws SQLException {

        Connection conn= null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;

        conn= DriverManager.getConnection(url,db_id,db_pw);

        //User table에서 삭제
        String sql="delete from user where id = '"+id+"'";
        pstmt=conn.prepareStatement(sql);
        int row=pstmt.executeUpdate();

        //watch_list에서 삭제
        sql="delete from watch_list where id = '"+id+"'";
        pstmt=conn.prepareStatement(sql);
        row=pstmt.executeUpdate();
    }


    public static void main(String[] args) throws SQLException {
        /*
        save("user","user","성재",25);
        save("test","test","성호",25);
        ArrayList<String> users= (ArrayList<String>) user_list();
        for(String id:users){
            System.out.println(id);
        }

        ArrayList<String> user= (ArrayList<String>) user_info("user");
        for(String info:user){
            System.out.println(info);
        }
        */
    }
}
