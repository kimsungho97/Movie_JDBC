package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;
import com.movie.jdbc.UserControl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class UserForm extends JFrame {

	private JFrame frame;
	private JTable movie_list;
	private UserControl userControl;
	private MovieControl movieControl;
	private ArrayList<String> movies;
	private ArrayList<String> user_info;

	public UserForm(ArrayList<String> user_info) throws SQLException {
		this.user_info = user_info;
		this.userControl = new UserControl();
		this.movieControl = new MovieControl();
		movies = (ArrayList<String>) movieControl.watch_list(user_info.get(0));
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setTitle("사용자화면");
		frame.setBounds(100, 100, 610, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("아이디:");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 10, 70, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이름:");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 35, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("나이:");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setBounds(12, 60, 70, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblParks = new JLabel(user_info.get(0));
		lblParks.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblParks.setBounds(89, 12, 85, 15);
		frame.getContentPane().add(lblParks);

		JLabel lblNewLabel_4_1 = new JLabel(user_info.get(2));
		lblNewLabel_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(89, 37, 70, 15);
		frame.getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel(user_info.get(3));
		lblNewLabel_4_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_2.setBounds(89, 62, 70, 15);
		frame.getContentPane().add(lblNewLabel_4_2);

		JButton btnNewButton = new JButton("영화 추가");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserAdd(user_info);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(244, 371, 97, 40);
		frame.getContentPane().add(btnNewButton);


		String[] columns = {"영화명", "장르", "감독", "주연배우", "개봉년도", "상영시간", "국가", "등급", "평점", "후기"};
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
			//------------------------
			ArrayList<String> review= (ArrayList<String>) movieControl.get_review(user_info.get(0),watched_content[0]);
			contents[i][8] = review.get(0);
			contents[i][9] = review.get(1);
		}

		movie_list = new JTable(contents, columns);
		JScrollPane scrollPane = new JScrollPane(movie_list);
		scrollPane.setVisible(true);



		//table_1.setToolTipText("");
		movie_list.setEnabled(false);
		movie_list.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 201, 570, 96);
		//frame.add(scrollPane);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	}
}

