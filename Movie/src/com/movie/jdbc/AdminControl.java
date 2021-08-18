package com.movie.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminControl {
    static private String url="jdbc:mysql://localhost:3306/movie?characterEncoding=utf8&autoReconnect=true";
    static private String db_id="root";
    static private String db_pw="1234";


    //사용자 id 목록
    public List<String> admin_list() throws SQLException {
        String sql="select id from admin";
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

    //관리자 id를 기준으로 개인 정보 가져옴
    public List<String> admin_info(String id) throws SQLException {
        String sql = "SELECT * FROM admin WHERE id = '" + id + "'";
        System.out.println(sql);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> id_info = new ArrayList<>();

        conn = DriverManager.getConnection(url, db_id, db_pw);
        stmt = conn.createStatement();
        //Sql문 완성
        rs = stmt.executeQuery(sql);

        if (rs.next()) {
            id_info.add(rs.getString("id"));
            id_info.add(rs.getString("pw"));
            return id_info;
        } else {
            return null;
        }

    }
}
