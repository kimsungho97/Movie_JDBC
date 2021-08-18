package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieFindForm extends JFrame {

	private JFrame frame;
	private JTable table;
	private ArrayList<String> user_info;
	private MovieControl movieControl;
	private Movie movie;

	public MovieFindForm(ArrayList<String> user_info, Movie movie) throws SQLException, IOException {
		this.movieControl=new MovieControl();
		this.movie=movie;
		this.user_info = user_info;
		initialize();
	}


	private void initialize() throws SQLException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(movie.mname+"에 남겨진 평가입니다");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(12, 10, 182, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("장르:");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2.setBounds(12, 45, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_2 = new JLabel(movie.genre);
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(87, 46, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_2_1 = new JLabel("감독:");
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(12, 83, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_3 = new JLabel(movie.director);
		lblNewLabel_1_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(87, 84, 107, 15);
		frame.getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_2_2 = new JLabel("주연배우:");
		lblNewLabel_2_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(12, 127, 63, 15);
		frame.getContentPane().add(lblNewLabel_2_2);

		JLabel lblNewLabel_1_4 = new JLabel(movie.actor);
		lblNewLabel_1_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_1_4.setBounds(87, 127, 125, 15);
		frame.getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_2_3 = new JLabel("개봉년도:");
		lblNewLabel_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_3.setBounds(12, 166, 71, 15);
		frame.getContentPane().add(lblNewLabel_2_3);

		JLabel lblNewLabel_1_5 = new JLabel(Integer.toString(movie.open));
		lblNewLabel_1_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(87, 167, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_2_4 = new JLabel("상영시간:");
		lblNewLabel_2_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(13, 204, 70, 15);
		frame.getContentPane().add(lblNewLabel_2_4);

		JLabel lblNewLabel_1_6 = new JLabel(Integer.toString(movie.time)+"분");
		lblNewLabel_1_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(87, 205, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_6);

		JLabel lblNewLabel_2_5 = new JLabel("국가:");
		lblNewLabel_2_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(12, 242, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_5);

		JLabel lblNewLabel_1_7 = new JLabel(movie.country);
		lblNewLabel_1_7.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_7.setBounds(87, 243, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_7);

		JLabel lblNewLabel_2_6 = new JLabel("등급:");
		lblNewLabel_2_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_6.setBounds(12, 286, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_6);

		JLabel lblNewLabel_1_8 = new JLabel(movie.ranking);
		lblNewLabel_1_8.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_8.setBounds(87, 287, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_8);

		JLabel lblNewLabel = new JLabel("");
		if(movie.img==null){
			lblNewLabel.setIcon(null);
		}
		else {
			InputStream is = movie.img.getBinaryStream(1, (int) movie.img.length());
			BufferedImage bufferedImage = ImageIO.read(is);
			lblNewLabel.setIcon(new ImageIcon(bufferedImage));
		}
		lblNewLabel.setBounds(289, 11, 222, 306);
		frame.getContentPane().add(lblNewLabel);

		String[] columns={"아이디","평점","후기"};
		ArrayList<ArrayList<String>> list=movieControl.get_review_by_mname(movie.mname);
		String[][] contents=new String[list.size()][columns.length];

		for(int i=0;i<list.size();i++){
			contents[i][0]=list.get(i).get(0);
			contents[i][1]=list.get(i).get(2);
			contents[i][2]=list.get(i).get(3);
		}

		table = new JTable(contents,columns);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane=new JScrollPane(table);

		scrollPane.setBounds(12, 341, 499, 81);
		frame.getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("확인");
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
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnNewButton.setBounds(210, 452, 94, 41);
		frame.getContentPane().add(btnNewButton);

		frame.setVisible(true);
	}
}
