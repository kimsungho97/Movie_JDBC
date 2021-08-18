package com.movie.ex;

import com.movie.Movie;
import com.movie.jdbc.MovieControl;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class AdminMovieAdd extends JFrame{

	private JFrame frame;
	private JTextField mnameField;
	private JTextField genreField;
	private JTextField directorField;
	private JTextField actorField;
	private JTextField openField;
	private JTextField timeField;
	private JTextField countryField;
	private JTextField rankingField;
	private MovieControl movieControl;
	private File img;

	public AdminMovieAdd() {
		movieControl=new MovieControl();
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("영화 추가");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(240, 10, 107, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		//lblNewLabel_1.setIcon(new ImageIcon());
		lblNewLabel_1.setBounds(23, 82, 220, 302);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("영화명:");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(319, 82, 57, 15);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("장르:");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2.setBounds(319, 117, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("감독:");
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(319, 155, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("주연배우:");
		lblNewLabel_2_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(319, 199, 71, 15);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("개봉년도:");
		lblNewLabel_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_3.setBounds(319, 238, 71, 15);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("상영시간:");
		lblNewLabel_2_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(320, 276, 70, 15);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("국가:");
		lblNewLabel_2_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(319, 314, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("등급:");
		lblNewLabel_2_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_2_6.setBounds(319, 358, 57, 15);
		frame.getContentPane().add(lblNewLabel_2_6);
		
		mnameField = new JTextField();
		mnameField.setText("세븐");
		mnameField.setBounds(403, 82, 148, 21);
		frame.getContentPane().add(mnameField);
		mnameField.setColumns(10);

		genreField = new JTextField();
		genreField.setText("범죄");
		genreField.setColumns(10);
		genreField.setBounds(403, 115, 148, 21);
		frame.getContentPane().add(genreField);
		
		directorField = new JTextField();
		directorField.setText("데이빗 핀처");
		directorField.setColumns(10);
		directorField.setBounds(403, 153, 148, 21);
		frame.getContentPane().add(directorField);
		
		actorField = new JTextField();
		actorField.setText("브래드 피트, 모건 프리먼");
		actorField.setColumns(10);
		actorField.setBounds(403, 197, 148, 21);
		frame.getContentPane().add(actorField);
		
		openField = new JTextField();
		openField.setText("1995");
		openField.setColumns(10);
		openField.setBounds(402, 236, 149, 21);
		frame.getContentPane().add(openField);
		
		timeField = new JTextField();
		timeField.setText("127분");
		timeField.setColumns(10);
		timeField.setBounds(402, 274, 149, 21);
		frame.getContentPane().add(timeField);
		
		countryField = new JTextField();
		countryField.setText("미국");
		countryField.setColumns(10);
		countryField.setBounds(403, 312, 148, 21);
		frame.getContentPane().add(countryField);
		
		rankingField = new JTextField();
		rankingField.setText("청불");
		rankingField.setColumns(10);
		rankingField.setBounds(403, 356, 148, 21);
		frame.getContentPane().add(rankingField);

		JLabel img_path=new JLabel();
		JButton find_path=new JButton("이미지 지정");
		img_path.setBounds(33,377,163,37);
		find_path.setBounds(33,410,120,25);
		find_path.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser=new JFileChooser();
				int has_chosen=jFileChooser.showOpenDialog(null);
				if(has_chosen==0){
					img= jFileChooser.getSelectedFile();
					lblNewLabel_1.setIcon(new ImageIcon(img.getPath()));
					img_path.setText(img.getPath());
				}
			}
		});
		frame.getContentPane().add(img_path);
		frame.getContentPane().add(find_path);


		JButton btnNewButton_2 = new JButton("영화추가");
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_2.setBounds(189, 434, 98, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Movie to_save=new Movie();
				to_save.mname=mnameField.getText();
				to_save.ranking=rankingField.getText();
				to_save.country=countryField.getText();
				to_save.actor=actorField.getText();
				to_save.open=Integer.parseInt(openField.getText());
				to_save.time=Integer.parseInt(timeField.getText());
				to_save.director=directorField.getText();
				to_save.genre=genreField.getText();
				byte[] byteArray=new byte[(int)img.length()];
				try {
					FileInputStream inputStream=new FileInputStream(img);
					inputStream.read(byteArray);
					to_save.img=new javax.sql.rowset.serial.SerialBlob(byteArray);
					movieControl.save(to_save);
					JOptionPane.showMessageDialog(null,"저장되었습니다.");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_2);

		
		JButton btnNewButton_1 = new JButton("홈");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminForm();
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_1.setBounds(327, 435, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
	       new AdminMovieAdd();
	}

}
