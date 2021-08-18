package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminMovie extends JFrame {

	private JFrame frame;
	private JTable table;
	private MovieControl movieControl;

	public AdminMovie() throws SQLException {
		movieControl=new MovieControl();
		initialize();
	}


	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("영화 추가");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(230, 10, 107, 29);
		frame.getContentPane().add(lblNewLabel);


		String[] columns = {"영화명", "장르", "감독", "주연배우", "개봉년도", "상영시간", "국가", "등급"};
		ArrayList<String> movies= null;
		try {
			movies = (ArrayList<String>) movieControl.movie_list();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		String[][] contents = new String[movies.size()][columns.length];

		for (int i = 0; i < movies.size(); i++) {
			Movie watched = movieControl.movie_info(movies.get(i));
			String[] watched_content = watched.info_list();
			contents[i][0] = watched_content[0];
			contents[i][1] = watched_content[1];
			contents[i][2] = watched_content[2];
			contents[i][3] = watched_content[3];
			contents[i][4] = watched_content[4];
			contents[i][5] = watched_content[5]+"분";
			contents[i][6] = watched_content[6];
			contents[i][7] = watched_content[7];
		}
		table = new JTable(contents,columns);
		JScrollPane scrollPane=new JScrollPane(table);

		table.setEnabled(false);
		table.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 83, 570, 96);
		frame.getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminForm();
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton.setBounds(12, 261, 107, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_2 = new JButton("영화추가");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMovieAdd();
				frame.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_2.setBounds(475, 261, 107, 23);
		frame.getContentPane().add(btnNewButton_2);

		frame.setVisible(true);
	}
}
