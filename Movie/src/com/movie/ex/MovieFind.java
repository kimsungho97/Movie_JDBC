package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieFind extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private ArrayList<String> user_info;
	private MovieControl movieControl;
	public MovieFind(ArrayList<String> user_info) {
		movieControl=new MovieControl();
		this.user_info=user_info;
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("영화 검색");
		frame.setBounds(100, 100, 404, 195);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(63, 70, 116, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movie movie=null;
				try {
					movie= movieControl.movie_info(textField.getText());
					if(movie==null){
						JOptionPane.showMessageDialog(null,"해당 영화가 없습니다.");
					}
					else{
						new MovieFindForm(user_info,movie);
						frame.dispose();
					}
				} catch (SQLException | IOException throwables) {
					throwables.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(200, 69, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("영화 검색");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(150, 10, 106, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("뒤로 가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserAdd(user_info);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(200, 117, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
}
