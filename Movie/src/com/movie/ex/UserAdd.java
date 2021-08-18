package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;
import com.movie.jdbc.UserControl;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Panel;
import java.awt.Canvas;

import java.awt.Color;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAdd extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private UserControl userControl;
	private MovieControl movieControl;
	private ArrayList<String> user_info;

	public UserAdd(ArrayList<String> user_info) throws SQLException {
		this.user_info=user_info;
		userControl=new UserControl();
		movieControl=new MovieControl();
		initialize();
	}

	
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("맑은 고딕", Font.BOLD, 14));
		frame.setTitle("영화추가");
		frame.setBounds(100, 100, 617, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(""));
		ImageIcon normalIcon = new ImageIcon("");
		lblNewLabel.setBounds(147, 66, 220, 306);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("영화명:");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(391, 66, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("장르:");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2.setBounds(391, 101, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("감독:");
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(391, 139, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("배우:");
		lblNewLabel_2_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(391, 183, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("개봉년도:");
		lblNewLabel_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_3.setBounds(391, 222, 71, 15);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("상영시간:");
		lblNewLabel_2_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(392, 260, 70, 15);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("국가:");
		lblNewLabel_2_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(391, 298, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("등급:");
		lblNewLabel_2_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_6.setBounds(391, 342, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_6);
		
		JLabel mnameLabel = new JLabel("샤이닝");
		mnameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		mnameLabel.setBounds(466, 66, 57, 15);
		frame.getContentPane().add(mnameLabel);
		
		JLabel genreLabel = new JLabel("공포");
		genreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		genreLabel.setBounds(466, 102, 57, 15);
		frame.getContentPane().add(genreLabel);
		
		JLabel directorLabel = new JLabel("스탠리 큐브릭");
		directorLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		directorLabel.setBounds(466, 140, 107, 15);
		frame.getContentPane().add(directorLabel);
		
		JLabel actorLabel = new JLabel("잭 니콜슨, \r\n셜리 듀발");
		actorLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		actorLabel.setBounds(466, 183, 125, 15);
		frame.getContentPane().add(actorLabel);
		
		JLabel openLabel = new JLabel("1980");
		openLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		openLabel.setBounds(466, 223, 57, 15);
		frame.getContentPane().add(openLabel);
		
		JLabel timeLabel = new JLabel("144분");
		timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		timeLabel.setBounds(466, 261, 57, 15);
		frame.getContentPane().add(timeLabel);
		
		JLabel countryLabel = new JLabel("영국");
		countryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		countryLabel.setBounds(466, 299, 57, 15);
		frame.getContentPane().add(countryLabel);
		
		JLabel rankingLabel = new JLabel("청불");
		rankingLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		rankingLabel.setBounds(466, 343, 57, 15);
		frame.getContentPane().add(rankingLabel);
		
		JLabel lblNewLabel_1_9 = new JLabel("평가:");
		lblNewLabel_1_9.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_9.setBounds(137, 392, 38, 15);
		frame.getContentPane().add(lblNewLabel_1_9);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new Integer[] {1,2,3,4,5}));
		//comboBox.setToolTipText("");
		comboBox.setBounds(176, 390, 42, 21);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setBounds(222, 390, 137, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		ArrayList<String> movie_list= (ArrayList<String>) movieControl.movie_list();
		comboBox_1.setModel(new DefaultComboBoxModel(movie_list.toArray()));
		comboBox_1.setBounds(12, 66, 113, 30);
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Movie movie=null;
				try {
					movie = movieControl.movie_info((String) comboBox_1.getSelectedItem());

					mnameLabel.setText(movie.mname);
					genreLabel.setText(movie.genre);
					directorLabel.setText(movie.director);
					actorLabel.setText(movie.actor);
					openLabel.setText(Integer.toString(movie.open));
					timeLabel.setText(Integer.toString(movie.time) + "분");
					countryLabel.setText(movie.country);
					rankingLabel.setText(movie.ranking);
					if(movie.img==null){
						lblNewLabel.setIcon(null);
					}
					else {
						InputStream is = movie.img.getBinaryStream(1, (int) movie.img.length());
						BufferedImage bufferedImage = ImageIO.read(is);
						lblNewLabel.setIcon(new ImageIcon(bufferedImage));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("영화 검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MovieFind(user_info);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(137, 33, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("내가 평가한 영화");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserForm(user_info);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(234, 33, 137, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("저장");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String review=textField.getText();
				int rating= (int) comboBox.getSelectedItem();
				try {
					movieControl.save_review(user_info.get(0),mnameLabel.getText(),rating,review);
					JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_2.setBounds(365, 389, 71, 23);
		frame.getContentPane().add(btnNewButton_2);
		frame.setVisible(true);
		
	}
}
