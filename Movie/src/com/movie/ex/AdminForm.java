package com.movie.ex;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AdminForm extends JFrame{

	private JFrame frame;

	
	public AdminForm() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("관리자페이지");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("관리자 페이지");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(168, 10, 107, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("사용자 목록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AdminUser();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton.setBounds(155, 66, 129, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("영화추가");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AdminMovie();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_1.setBounds(155, 133, 129, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("홈");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginForm();
				frame.dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(155, 196, 129, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		frame.setVisible(true);
	}


}
