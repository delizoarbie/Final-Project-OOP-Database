import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookPnl extends JPanel {
	private JPanel pnl;
	private JTable table;
	private JTextField bookingCode;
	private JTextField customerId;
	private JDateChooser startDate;
	private JDateChooser endDate;
	private JComboBox insurancePlan;
	private JComboBox numberPass;
	private JComboBox comboVanCode;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	PreparedStatement pst1 = null;
	private JPanel panel_1;
	private JLabel lblMiddleName;
	private JLabel lblLname;
	private JTextField fName;
	private JTextField mName;
	private JTextField lName;
	private JTextField phoneNum;
	private JLabel email;
	private JTextField txtemail;
	private JLabel address;
	private JTextField txtaddress;
	private JComboBox comGender;
	private JDateChooser dateChooser;
	private JButton btnReset;
	

	/**
	 * Create the panel.
	 */
	public BookPnl() {
	
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		createPnl();
		
		JLabel lblBook = new JLabel("Booking");
		lblBook.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblBook.setBounds(21, 11, 78, 26);
		add(lblBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 390, 1016, 170);
		add(scrollPane);
		btnSave = new JButton("Save");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		pnl = new JPanel();
		pnl.setBounds(10, 11, 810, 307);
		panel_1.add(pnl);
		pnl.setBackground(Color.WHITE);
		
		createSaveBtn(pnl, btnSave);
		createUpdateBtn(pnl, btnUpdate);
		createDeleteBtn(pnl, btnDelete);
		
		
		
		JLabel lblNewLabel = new JLabel("Booking Code");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(24, 39, 88, 30);
		pnl.add(lblNewLabel);
		
		bookingCode = new JTextField();
		bookingCode.setBounds(168, 43, 120, 29);
		pnl.add(bookingCode);
		bookingCode.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Date ");
		lblStartDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblStartDate.setBounds(24, 80, 79, 30);
		pnl.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEndDate.setBounds(24, 121, 79, 30);
		pnl.add(lblEndDate);
		
		customerId = new JTextField();
		customerId.setColumns(10);
		customerId.setBounds(431, 82, 107, 29);
		pnl.add(customerId);
		
		JLabel lblInsurancePlan = new JLabel("Insurance Plan");
		lblInsurancePlan(lblInsurancePlan);
		
		JLabel lblNumberOfPassenger = new JLabel("Number of Passenger");
		lblNumberPass(lblNumberOfPassenger);
		
		startDate = new JDateChooser();
		startDate.setBounds(168, 82, 120, 30);
		pnl.add(startDate);
		
		endDate = new JDateChooser();
		endDate.setBounds(168, 123, 120, 30);
		pnl.add(endDate);
		
		insurancePlan = new JComboBox();
		insurancePlan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		numberPass = new JComboBox();
		numberPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		createlblCusId(lblCustomerId);
		
		JLabel lblVanCode = new JLabel("Van Code");
		lblVanCode(lblVanCode);
		
		comboVanCode = new JComboBox();
		
		pnl.setLayout(null);
		table = new JTable();
		createTableSelect(scrollPane);
		createInsurancePlan();
		createNumberPass();
		createVanCode();
		
		showTableData();
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFirstName.setBounds(324, 121, 79, 30);
		pnl.add(lblFirstName);
		
		lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMiddleName.setBounds(325, 169, 89, 30);
		pnl.add(lblMiddleName);
		
		lblLname = new JLabel("Last Name");
		lblLname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLname.setBounds(325, 209, 89, 30);
		pnl.add(lblLname);
		
		fName = new JTextField();
		fName.setColumns(10);
		fName.setBounds(430, 121, 107, 30);
		pnl.add(fName);
		
		mName = new JTextField();
		mName.setColumns(10);
		mName.setBounds(431, 168, 107, 29);
		pnl.add(mName);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(431, 208, 107, 29);
		pnl.add(lName);
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(682, 163, 107, 29);
		pnl.add(phoneNum);
		
		JLabel lblPhoneNum = new JLabel("Phone Number");
		lblPhoneNum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPhoneNum.setBounds(576, 162, 89, 30);
		pnl.add(lblPhoneNum);
		
		JLabel Gender = new JLabel("Gender");
		Gender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Gender.setBounds(576, 210, 69, 29);
		pnl.add(Gender);
		
		comGender = new JComboBox();
		comGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comGender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comGender.setBounds(682, 209, 108, 30);
		pnl.add(comGender);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(682, 118, 107, 33);
		pnl.add(dateChooser);
		
		JLabel label = new JLabel("Birthdate");
		label.setBounds(576, 123, 69, 27);
		pnl.add(label);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		email = new JLabel("Email");
		email.setBounds(576, 80, 79, 30);
		pnl.add(email);
		email.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		txtemail = new JTextField();
		txtemail.setBounds(682, 77, 107, 29);
		pnl.add(txtemail);
		txtemail.setColumns(10);
		
		address = new JLabel("Address");
		address.setBounds(576, 39, 79, 30);
		pnl.add(address);
		address.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		txtaddress = new JTextField();
		txtaddress.setBounds(682, 39, 107, 29);
		pnl.add(txtaddress);
		txtaddress.setColumns(10);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookingCode.setText("");
				customerId.setText("");
				startDate.setDate(new Date());
				endDate.setDate(new Date());
				insurancePlan.setSelectedIndex(0);
				numberPass.setSelectedIndex(0);
				comboVanCode.setSelectedIndex(0);
				fName.setText("");
				mName.setText("");
				lName.setText("");
				phoneNum.setText("");
				txtemail.setText("");
				txtaddress.setText("");
				comGender.setSelectedIndex(0);
				dateChooser.setDate(new Date());

			}
		});
		btnReset.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnReset.setBounds(702, 263, 89, 33);
		pnl.add(btnReset);
		
		
	}


	private void createTableSelect(JScrollPane scrollPane) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				bookingCode.setText(model.getValueAt(i, 0).toString());
				
				String insurance = model.getValueAt(i, 3).toString();
				switch(insurance) {
				case "Collision Damage Waiver (CDW)":
					insurancePlan.setSelectedIndex(0);
					break;
				case "Supplemental Liabillity Protection (SLP)":
					insurancePlan.setSelectedIndex(1);
					break;
				case "Personal Accident Insurance (PAI)":
					insurancePlan.setSelectedIndex(2);
					break;	
				case "Personal Effects Coverage (PEC)":
					insurancePlan.setSelectedIndex(3);
					break;	
					
						
				}
				String numPass = model.getValueAt(i, 4).toString();
				switch(numPass) {
				case "1":
					numberPass.setSelectedIndex(0);
					break;
				case "2":
					numberPass.setSelectedIndex(1);
					break;
				case "3":
					numberPass.setSelectedIndex(2);
					break;	
				case "4":
					numberPass.setSelectedIndex(3);
					break;
				case "5":
					numberPass.setSelectedIndex(4);
					break;
				case "6":
					numberPass.setSelectedIndex(5);
					break;
				case "7":
					numberPass.setSelectedIndex(6);
					break;
				case "8":
					numberPass.setSelectedIndex(7);
					break;	
				case "9":
					numberPass.setSelectedIndex(8);
					break;
				case "10":
					numberPass.setSelectedIndex(9);
					break;
				case "11":
					numberPass.setSelectedIndex(10);
					break;
				case "12":
					numberPass.setSelectedIndex(11);
					break;
				}
				String vanCode = model.getValueAt(i, 5).toString();
				switch(vanCode) {
				case "1234":
					comboVanCode.setSelectedIndex(0);
					break;
				case "1235":
					comboVanCode.setSelectedIndex(1);
					break;
				case "1236":
					comboVanCode.setSelectedIndex(2);
					break;
				case "1237":
					comboVanCode.setSelectedIndex(3);
					break;
				case "1238":
					comboVanCode.setSelectedIndex(4);
					break;
				}
				customerId.setText(model.getValueAt(i, 6).toString());
				fName.setText(model.getValueAt(i, 7).toString());
				mName.setText(model.getValueAt(i, 8).toString());
				lName.setText(model.getValueAt(i, 9).toString());
				txtaddress.setText(model.getValueAt(i, 10).toString());
				txtemail.setText(model.getValueAt(i, 11).toString());	
				phoneNum.setText(model.getValueAt(i, 12).toString());	
				String gender = model.getValueAt(i, 13).toString();
				switch(gender) {
				case "Male":
					comGender.setSelectedIndex(0);
					break;
				case "Female":
					comGender.setSelectedIndex(1);
					break;	
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
	}
	

	private void lblInsurancePlan(JLabel lblInsurancePlan) {
		lblInsurancePlan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblInsurancePlan.setBounds(24, 209, 88, 30);
		pnl.add(lblInsurancePlan);
	}


	private void lblNumberPass(JLabel lblNumberOfPassenger) {
		lblNumberOfPassenger.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNumberOfPassenger.setBounds(24, 168, 134, 30);
		pnl.add(lblNumberOfPassenger);
	}


	private void createInsurancePlan() {
		insurancePlan.setModel(new DefaultComboBoxModel(new String[] {"Collision Damage Waiver (CDW)", "Supplemental Liabillity Protection (SLP)", "Personal Accident Insurance (PAI)", "Personal Effects Coverage (PEC)"}));
		insurancePlan.setBounds(171, 211, 117, 30);
		pnl.add(insurancePlan);
	}


	private void createNumberPass() {
		numberPass.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		numberPass.setBounds(170, 168, 117, 30);
		pnl.add(numberPass);
	}


	private void createlblCusId(JLabel lblCustomerId) {
		lblCustomerId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCustomerId.setBounds(324, 80, 79, 30);
		pnl.add(lblCustomerId);
	}


	private void lblVanCode(JLabel lblVanCode) {
		lblVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblVanCode.setBounds(324, 39, 79, 30); 
		pnl.add(lblVanCode);
	}


	private void createVanCode() {
		comboVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboVanCode.setModel(new DefaultComboBoxModel(new String[] {"1234", "1235", "1236", "1237", "1238"}));
		comboVanCode.setBounds(431, 39, 107, 30);
		pnl.add(comboVanCode);
		
		
		
	}


	private void createPnl() {
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(118, 38, 830, 329);
		add(panel_1);
		panel_1.setLayout(null);
		
		
	}


	private void createSaveBtn(JPanel panel, JButton btnSave) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
					String sql = "INSERT INTO vanrental.booking (Booking_ID, BookDstart, BookDend, BookInsurance, BookNumPass, Van_Code) VALUES(?,?,?,?,?,?)";
					String sql2 = "insert into vanrental.customer (Customer_ID, Customer_FName, Customer_MName, Customer_LName, Customer_Address, Customer_Email, Customer_PhoneNumber, Customer_Birthdate, Customer_Gender) values (?,?,?,?,?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql2);
					pst.setInt(1, Integer.parseInt(bookingCode.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(startDate.getDate());
					String date2 = sdf.format(endDate.getDate());
					pst.setString(2,date);
					pst.setString(3, date2);
					
					//for choosing insurance
					String insurance;
					insurance = insurancePlan.getSelectedItem().toString();
					pst.setString(4, insurance);
					
					//Number of passengers
					String pssngrs;
					pssngrs = numberPass.getSelectedItem().toString();
					pst.setString(5, pssngrs);
					

					//for choosing vanCode
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(6, vanCode);
					
					pst1.setInt(1, Integer.parseInt(customerId.getText()));
					pst1.setString(2, fName.getText());
					pst1.setString(3, mName.getText());
					pst1.setString(4, lName.getText());
					pst1.setString(5, txtaddress.getText());
					pst1.setString(6, txtemail.getText());
					pst1.setString(7, phoneNum.getText());
					String bday = sdf.format(dateChooser.getDate());
					pst1.setString(8,bday);
					String gender;
					gender = comGender.getSelectedItem().toString();
					pst1.setString(9, gender);
					pst.executeUpdate();
					pst1.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Insert Successfully");
					showTableData();
					con.close();
					
					
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSave.setBounds(405, 263, 89, 33);
		pnl.add(btnSave);
	}


	private void createUpdateBtn(JPanel panel, JButton btnUpdate) {
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.booking SET BookDstart=?, BookDend=?, BookInsurance=?, BookNumPass=?, Van_Code=? where Booking_ID=? ";
					String sql2 = "UPDATE vanrental.customer SET Customer_FName=?, customer_MName=?, Customer_LName=? where Customer_ID=?";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql2);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(startDate.getDate());
					String date2 = sdf.format(endDate.getDate());
					pst.setString(1,date);
					pst.setString(2, date2);
					
					//for choosing insurance
					String insurance;
					insurance = insurancePlan.getSelectedItem().toString();
					pst.setString(3, insurance);
					
					//Number of passengers
					String pssngrs;
					pssngrs = numberPass.getSelectedItem().toString();
					pst.setString(4, pssngrs);
					

					//for choosing vanCode
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(5, vanCode);
					pst.setInt(6, Integer.parseInt(bookingCode.getText()));
					
					pst1.setString(1, fName.getText());
					pst1.setString(2, mName.getText());
					pst1.setString(3, lName.getText());
					pst1.setInt(4, Integer.parseInt(customerId.getText()));
					pst.executeUpdate();
					pst1.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					showTableData();
					con.close();

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnUpdate.setBounds(504, 263, 89, 33);
		pnl.add(btnUpdate);
	}


	private void createDeleteBtn(JPanel panel, JButton btnDelete) {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.booking where Booking_ID=?";
					String sql2 = "DELETE from vanrental.customer where Customer_ID=?";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql2);
					
					pst.setInt(1, Integer.parseInt(bookingCode.getText()));
					pst1.setInt(1, Integer.parseInt(customerId.getText()));
					pst.executeUpdate();
					pst1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					showTableData();
					con.close();
				
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDelete.setBounds(603, 263, 89, 33);
		pnl.add(btnDelete);
	}
	
	public void showTableData() {
		try {
			String sql = "select Booking_ID, BookDstart, BookDend, BookInsurance, BookNumPass, Van_Code,customer.Customer_ID, Customer_FName, Customer_MName, Customer_LName,  Customer_Address, Customer_Email, Customer_PhoneNumber, Customer_Birthdate, Customer_Gender FROM booking JOIN customer ON booking.Booking_ID= customer.Customer_ID ";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		}
	
	catch(Exception ex) {
		ex.printStackTrace();
		
	}
	}
}
