package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AdminUserSearch {

    private JFrame frame;
    private JTable table;
    private String id;
    private MovieControl movieControl;

    public AdminUserSearch(String id) throws SQLException {
        movieControl=new MovieControl();
        this.id=id;
        initialize();
    }


    private void initialize() throws SQLException {
        frame = new JFrame();
        frame.setBounds(100, 100, 494, 343);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel(id+"님이 본영화입니다");
        lblNewLabel.setBounds(140, 21, 160, 29);
        frame.getContentPane().add(lblNewLabel);


        ArrayList<ArrayList<String>> list=movieControl.get_review_by_id(id);
        String[] columns = {"영화명", "장르", "감독", "주연배우", "개봉년도", "상영시간", "국가", "등급", "평점", "후기"};
        String[][] contents=new String[list.size()][columns.length];

        for(int i=0;i<list.size();i++){
            String mname=list.get(i).get(1);
            Movie movie=movieControl.movie_info(mname);
            String[] temp=movie.info_list();
            for(int j=0;j<temp.length;j++){
                contents[i][j]=temp[j];
            }
            contents[i][8]=list.get(i).get(2);
            contents[i][9]=list.get(i).get(3);
        }

        table = new JTable(contents,columns);
        table.setEnabled(false);
        table.setBackground(Color.WHITE);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(5, 50, 468, 110);
        frame.getContentPane().add(scrollPane);

        JButton btnNewButton = new JButton("확인");
        btnNewButton.setBounds(141, 190, 163, 20);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnNewButton);
        frame.setVisible(true);
    }
}
