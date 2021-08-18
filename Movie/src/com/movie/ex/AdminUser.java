package com.movie.ex;

import com.movie.jdbc.MovieControl;
import com.movie.jdbc.UserControl;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminUser extends JFrame{

	private JFrame frame;
	private JTable table_1;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private MovieControl movieControl;
	private UserControl userControl;
	public AdminUser() throws SQLException {
		this.movieControl=new MovieControl();
		this.userControl=new UserControl();
		initialize();
	}

	
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("사용자 목록");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(188, 10, 107, 29);
		frame.getContentPane().add(lblNewLabel);

		ArrayList<ArrayList<String>> list=movieControl.get_all_review();
		String[] columns={"아이디","영화명","평점","후기"};
		String[][] contents=new String[list.size()][columns.length];
		for(int i=0;i<list.size();i++){
			contents[i][0]=list.get(i).get(0);
			contents[i][1]=list.get(i).get(1);
			contents[i][2]=list.get(i).get(2);
			contents[i][3]=list.get(i).get(3);
		}
		table_1 = new JTable(contents,columns);
		JScrollPane scrollPane=new JScrollPane(table_1);

		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setBounds(12, 49, 480, 81);
		frame.getContentPane().add(scrollPane);
		
		btnNewButton = new JButton("뒤로가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminForm();
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton.setBounds(12, 193, 107, 23);
		frame.getContentPane().add(btnNewButton);

		JButton showUser_info = new JButton("사용자 조회");
		showUser_info.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		showUser_info.setBounds(191, 193, 121, 23);
		showUser_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()!=-1) {
					String id = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
					try {
						new AdminUserSearch(id);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(showUser_info);

		btnNewButton_2 = new JButton("사용자 삭제");
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_2.setBounds(371, 193, 121, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id= (String) table_1.getValueAt(table_1.getSelectedRow(),0);
				try {
					userControl.remove(id);
					JOptionPane.showMessageDialog(null,"삭제 완료.");
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_2);
		
		frame.setVisible(true);
	}
}
