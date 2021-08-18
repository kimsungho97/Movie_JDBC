package com.movie.ex;

import com.movie.jdbc.AdminControl;
import com.movie.jdbc.UserControl;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTextField;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoinForm extends JFrame {

	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JTextField nameField;
	private JLabel lblNewLabel_3;
	private JTextField ageField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private UserControl userControl;
	private JLabel alertLabel;

	public JoinForm() {
		userControl=new UserControl();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원가입");
		frame.setBounds(100, 100, 464, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		//ID
		JLabel lblNewLabel = new JLabel("아이디:");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 27, 70, 15);
		frame.getContentPane().add(lblNewLabel);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(154, 26, 155, 21);
		frame.getContentPane().add(idField);

		//PW
		JLabel lblNewLabel_1 = new JLabel("비밀번호:");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 80, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(154, 79, 155, 21);
		frame.getContentPane().add(passwordField);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 확인:");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(12, 123, 119, 15);
		frame.getContentPane().add(lblNewLabel_1_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(154, 122, 155, 21);
		frame.getContentPane().add(passwordField_1);

		btnNewButton = new JButton("중복 확인");
		btnNewButton.setBounds(321, 25, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> user_info=null;
				try {
					user_info= (ArrayList<String>) userControl.user_info(idField.getText());
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				if(user_info==null){
					JOptionPane.showMessageDialog(null,"사용 가능한 아이디입니다.");
				}
				else{
					JOptionPane.showMessageDialog(null,"중복된 아이디입니다.");
					idField.setText("");
				}
			}
		});
		frame.getContentPane().add(btnNewButton);


		lblNewLabel_2 = new JLabel("이름:");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setBounds(12, 199, 70, 15);
		frame.getContentPane().add(lblNewLabel_2);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(154, 198, 155, 21);
		frame.getContentPane().add(nameField);

		//age
		lblNewLabel_3 = new JLabel("나이:");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setBounds(12, 251, 70, 15);
		frame.getContentPane().add(lblNewLabel_3);

		ageField = new JTextField();
		ageField.setColumns(10);
		ageField.setBounds(154, 250, 155, 21);
		frame.getContentPane().add(ageField);

		//경고 라벨
		alertLabel = new JLabel("");
		alertLabel.setForeground(Color.RED);
		alertLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		alertLabel.setBounds(154, 275, 155, 21);
		frame.getContentPane().add(alertLabel);


		btnNewButton_1 = new JButton("회원 가입");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id=idField.getText().strip();
				String pw= String.valueOf(passwordField.getPassword());
				String pwcheck=String.valueOf(passwordField_1.getPassword());
				String name=nameField.getText().strip();
				Integer age=Integer.parseInt(ageField.getText());
				ArrayList<String> user_info=null;
				try {
					user_info= (ArrayList<String>) userControl.user_info(idField.getText());
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				if(user_info!=null){
					alertLabel.setText("중복된 아이디입니다..");
				}
				else if(id==null || id.length()==0){
					alertLabel.setText("아이디를 입력하세요.");
					return;
				}
				else if(!pw.equals(pwcheck)){
					alertLabel.setText("비밀번호가 다릅니다.");
					return;
				}
				else if(name==null || name.length()==0){
					alertLabel.setText("이름을 입력해주세요.");
					return;
				}
				else if(age==null || age<0){
					alertLabel.setText("나이를 확인해주세요.");
					return;
				}

				try {
					userControl.save(id,pw,name,age);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			}
		});
		btnNewButton_1.setBounds(126, 317, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("취소");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginForm();
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(235, 317, 97, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		frame.setVisible(true);
		
	}
}