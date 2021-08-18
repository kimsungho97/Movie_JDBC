package com.movie.ex;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Font;

import com.movie.ex.JoinForm;
import com.movie.jdbc.AdminControl;
import com.movie.jdbc.UserControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginForm extends JFrame {

	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;
	private AdminControl adminControl;
	private UserControl userControl;


	public LoginForm() {
		initialize();
		adminControl=new AdminControl();
		userControl=new UserControl();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("영화 관리 프로그램");
		frame.setBounds(100, 100, 450, 163);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("아이디:");
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblId.setBounds(29, 31, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("비밀번호:");
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPw.setBounds(29, 74, 70, 15);
		frame.getContentPane().add(lblPw);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(143, 30, 155, 21);
		frame.getContentPane().add(idField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 73, 155, 21);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> result1=null;
				ArrayList<String> result2=null;
				try {
					//이 아이디가 admin의 아이디인가
					String temp=idField.getText().strip();
					String temp2=String.valueOf(passwordField.getPassword());
					result1= (ArrayList<String>) adminControl.admin_info(temp);
					result2= (ArrayList<String>) userControl.user_info(temp);

					if(result1!=null && result1.get(1).equals(temp2)){
						new AdminForm();
						frame.dispose();
					}
					//일반 user의 아이디인가
					else if (result2 != null && result2.get(1).equals(temp2)) {
						new UserForm(result2);
						frame.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null,"아이디와 패스워드를 확인해주세요.");
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnLogin.setBounds(311, 29, 97, 34);
		frame.getContentPane().add(btnLogin);
		
		JButton btnJoin = new JButton("회원가입");

		
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinForm();
				frame.dispose();
			}
		});
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnJoin.setBounds(310, 74, 97, 23);
		frame.getContentPane().add(btnJoin);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
	       new LoginForm();
	}
}


