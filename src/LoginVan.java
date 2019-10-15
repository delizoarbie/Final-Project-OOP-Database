import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginVan extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginVan frame = new LoginVan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginVan() {
		
		setUndecorated(true);
		setBounds(100, 100, 326, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 325, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUser.setBounds(33, 57, 78, 35);
		panel.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPass.setBounds(33, 103, 78, 35);
		panel.add(lblPass);
		
		user = new JTextField();
		user.setBounds(143, 59, 141, 35);
		panel.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(143, 105, 141, 35);
		panel.add(pass);
		
		JButton btnLoginVan = new JButton("Login");
		btnLoginVan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					
					Statement stmt = con.createStatement();
					String sql = "Select * from employee where Employee_Username ='" + user.getText()+"'and Employee_Password ='"+pass.getText().toString()+ "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Login sucessfully");
						Main main = new Main();
						main.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username and password");
					con.close();
					}
				} 
				catch (Exception e) {System.out.print(e);}
			}

		});
		btnLoginVan.setForeground(Color.WHITE);
		btnLoginVan.setBackground(new Color(0, 153, 153));
		btnLoginVan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLoginVan.setBounds(164, 161, 95, 28);
		panel.add(btnLoginVan);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBounds(0, 0, 343, 41);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("    USER LOGIN");
		lblNewLabel.setBounds(80, 0, 148, 43);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(0, 0, 204));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
