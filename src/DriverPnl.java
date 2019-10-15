import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DriverPnl extends JPanel {
	
	private JTable table;
	private JTextField fName;
	private JTextField mName;
	private JTextField lName;
	private JTextField email;
	private JTextField driverId;
	private JTextField phoneNum;
	private JTextField address;
	private JComboBox comboVanCode;
	private JComboBox comboInsurance;
	private JComboBox genderCom;
	private JDateChooser bdate;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField GuardianName;
	private JTextField GuardianNum;
	private JTextField searchID;

	/**
	 * Create the panel.
	 */
	public DriverPnl() {
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		
		JLabel lblDriver = new JLabel("Driver");
		lblDriver.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblDriver.setBounds(10, 11, 64, 27);
		add(lblDriver);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 385, 1027, 175);
		add(scrollPane);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Driver Id", "First Name", "Middle Name", "Last Name", "email", "Birthday", "Address", "Number", "Gender", "Guardian Number", "Guardian Name", "Van code", "Insurance"
				}
			));
			
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				driverId.setText(model.getValueAt(i, 0).toString());
				fName.setText(model.getValueAt(i, 1).toString());
				mName.setText(model.getValueAt(i, 2).toString());
				lName.setText(model.getValueAt(i, 3).toString());
				email.setText(model.getValueAt(i, 4).toString());

				try {
					int srow = table.getSelectedRow();
					Date date = new SimpleDateFormat ("yyyy-MM-dd").parse((String)model.getValueAt(srow, 5));
					bdate.setDate(date);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
				}
				address.setText(model.getValueAt(i, 6).toString());
				phoneNum.setText(model.getValueAt(i, 7).toString());
				String gender1 = model.getValueAt(i,8).toString();
				switch(gender1) {
				case "Male":
					genderCom.setSelectedIndex(0);
					break;
				case "Female":
					genderCom.setSelectedIndex(1);
					break;
						
				}
				GuardianNum.setText(model.getValueAt(i, 9).toString());
				GuardianName.setText(model.getValueAt(i, 10).toString());
				String vanCode = model.getValueAt(i,11).toString();
				switch(vanCode) {
				case "123":
					comboVanCode.setSelectedIndex(0);
					break;
				case "1234":
					comboVanCode.setSelectedIndex(1);
					break;
				case "12345":
					comboVanCode.setSelectedIndex(2);
					break;	
				case "123456":
					comboVanCode.setSelectedIndex(3);
					break;
				case "1234567":
					comboVanCode.setSelectedIndex(4);
					break;
				}	

				String insurance = model.getValueAt(i, 12).toString();
				switch(insurance) {
				case "Proggressive":
					comboInsurance.setSelectedIndex(0);
					break;
				case "Allstate":
					comboInsurance.setSelectedIndex(1);
					break;
				case "Bristol West":
					comboInsurance.setSelectedIndex(2);
					break;
						
				}
								
			}
		});
		show_Driver();
		//showTableData();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(129, 51, 804, 303);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 781, 280);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("First Name");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label.setBounds(24, 98, 69, 23);
		panel.add(label);
		
		fName = new JTextField();
		fName.setColumns(10);
		fName.setBounds(124, 94, 114, 26);
		panel.add(fName);
		
		JLabel label_1 = new JLabel("Middle Name");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_1.setBounds(24, 132, 89, 23);
		panel.add(label_1);
		
		mName = new JTextField();
		mName.setColumns(10);
		mName.setBounds(124, 131, 114, 26);
		panel.add(mName);
		
		JLabel label_2 = new JLabel("Last Name");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_2.setBounds(24, 166, 69, 23);
		panel.add(label_2);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(124, 165, 114, 26);
		panel.add(lName);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(373, 126, 114, 26);
		panel.add(email);
		
		driverId = new JTextField();
		driverId.setColumns(10);
		driverId.setBounds(124, 57, 116, 26);
		panel.add(driverId);
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(373, 89, 114, 26);
		panel.add(phoneNum);
		
		JLabel label_3 = new JLabel("Gender");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_3.setBounds(24, 200, 69, 23);
		panel.add(label_3);
		
		JLabel lblDriverid = new JLabel("Driver_ID");
		lblDriverid.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDriverid.setBounds(24, 64, 69, 23);
		panel.add(lblDriverid);
		
		JLabel label_5 = new JLabel("Birthdate");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_5.setBounds(267, 60, 69, 23);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Phone Number");
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_6.setBounds(267, 98, 96, 23);
		panel.add(label_6);
		
		genderCom = new JComboBox();
		genderCom.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		genderCom.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderCom.setBounds(124, 199, 114, 26);
		panel.add(genderCom);
		
		JLabel label_7 = new JLabel("Email");
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_7.setBounds(267, 132, 69, 23);
		panel.add(label_7);
		
		JButton saveBtn = new JButton("Save");
		createSaveBtn(panel, saveBtn);
		
		JButton UpdateBtn = new JButton("Update");
		UpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.driver SET DriverFname =?, DriverMname =?, DriverLname =?, Address =? ,DrivePhoneNum =?, DriverEmail =?, Driver_Bdate=?, Insurance=?,Guardian_name=?, Guardian_Pnum=?,Van_Code=?, driver_gender=? WHERE Driver_ID =?";
					pst = con.prepareStatement(sql);
					pst.setString(1, fName.getText());
					pst.setString(2, mName.getText());
					pst.setString(3, lName.getText());
					pst.setString(4, address.getText());
				    pst.setInt(5, Integer.parseInt(phoneNum.getText()));
				    pst.setString(6, email.getText());
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(bdate.getDate());
					pst.setString(7, date);
				    String insurance;
					insurance = comboInsurance.getSelectedItem().toString();
					pst.setString(8, insurance);
					pst.setString(9, GuardianName.getText());
					pst.setInt(10, Integer.parseInt(phoneNum.getText()));
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(11, vanCode);
					String gender;
					gender = genderCom.getSelectedItem().toString();
					pst.setString(12, gender);
					pst.setInt(13, Integer.parseInt(driverId.getText()));
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					show_Driver();
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		UpdateBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		UpdateBtn.setBounds(472, 236, 89, 33);
		panel.add(UpdateBtn);
		
		JLabel label_8 = new JLabel("Address");
		label_8.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_8.setBounds(267, 166, 69, 23);
		panel.add(label_8);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(373, 160, 114, 26);
		panel.add(address);
		
		bdate = new JDateChooser();
		bdate.setBounds(373, 52, 114, 26);
		panel.add(bdate);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.driver  WHERE Driver_ID =?";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(driverId.getText()));
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					show_Driver();
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		});
		deleteBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		deleteBtn.setBounds(571, 236, 89, 33);
		panel.add(deleteBtn);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchID.setText("");
				fName.setText("");
				mName.setText("");
				lName.setText("");
				email.setText("");
				driverId.setText("");
				phoneNum.setText("");
				address.setText("");
				comboVanCode.setSelectedIndex(0);
				comboInsurance.setSelectedIndex(0);
				genderCom.setSelectedIndex(0);
				bdate.setDate(new Date());
				GuardianName.setText("");
				GuardianNum.setText("");
			}
		});
		resetBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		resetBtn.setBounds(670, 236, 89, 33);
		panel.add(resetBtn);
		
		JLabel lblInsurance = new JLabel("Insurance");
		lblInsurance.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblInsurance.setBounds(267, 200, 69, 23);
		panel.add(lblInsurance);
		
		comboInsurance = new JComboBox();
		comboInsurance.setModel(new DefaultComboBoxModel(new String[] {"Progressive", "Allstate", "Bristol West"}));
		comboInsurance.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboInsurance.setBounds(373, 197, 114, 26);
		panel.add(comboInsurance);
		
		JLabel lblGuardianNumber = new JLabel("Guardian Name");
		lblGuardianNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGuardianNumber.setBounds(519, 57, 105, 23);
		panel.add(lblGuardianNumber);
		
		JLabel label_4 = new JLabel("Guardian Number");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_4.setBounds(519, 95, 105, 23);
		panel.add(label_4);
		
		JLabel lblVanCode = new JLabel("Van Code");
		lblVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblVanCode.setBounds(519, 134, 105, 23);
		panel.add(lblVanCode);
		
		comboVanCode = new JComboBox();
		comboVanCode.setModel(new DefaultComboBoxModel(new String[] {"123", "1234", "12345", "123456", "1234567"}));
		comboVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboVanCode.setBounds(642, 130, 115, 26);
		panel.add(comboVanCode);
		
		GuardianName = new JTextField();
		GuardianName.setColumns(10);
		GuardianName.setBounds(643, 52, 114, 26);
		panel.add(GuardianName);
		
		GuardianNum = new JTextField();
		GuardianNum.setColumns(10);
		GuardianNum.setBounds(643, 92, 114, 26);
		panel.add(GuardianNum);
		
		JLabel lblSearchid = new JLabel("Search_ID");
		lblSearchid.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSearchid.setBounds(22, 23, 69, 23);
		panel.add(lblSearchid);
		
		searchID = new JTextField();
		searchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql= "select * from vanrental.driver WHERE Driver_ID =?";
					pst = con.prepareStatement(sql);
					pst.setString(1, searchID.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						String setID = rs.getString("Driver_ID");
						driverId.setText(setID);
						
						String setfName = rs.getString("DriverFname");
						fName.setText(setfName);
						String setmName = rs.getString("DriverMname");
						mName.setText(setmName);
						String setlName = rs.getString("DriverLname");
						lName.setText(setlName);
						String setadd = rs.getString("Address");
						address.setText(setadd);
						String setPhoneNum = rs.getString("DrivePhoneNum");
						phoneNum.setText(setPhoneNum);
						String setemail = rs.getString("DriverEmail");
						email.setText(setemail);
						bdate.setDate(rs.getDate("Driver_Bdate"));
						String setInsurance = rs.getString("Insurance");
						switch(setInsurance) {
						case "Proggressive":
							comboInsurance.setSelectedIndex(0);
							break;
						case "Allstate":
							comboInsurance.setSelectedIndex(1);
							break;
						case "Bristol West":
							comboInsurance.setSelectedIndex(2);
							break;
								
						}
						String setguardianName = rs.getString("Guardian_name");
						GuardianName.setText(setguardianName);
						String setguardianNum = rs.getString("Guardian_Pnum");
						GuardianNum.setText(setguardianNum);
						String setVanCode = rs.getString("Van_Code");
						switch(setVanCode) {
						case "123":
							comboVanCode.setSelectedIndex(0);
							break;
						case "1234":
							comboVanCode.setSelectedIndex(1);
							break;
						case "12345":
							comboVanCode.setSelectedIndex(2);
							break;	
						case "123456":
							comboVanCode.setSelectedIndex(3);
							break;
						case "1234567":
							comboVanCode.setSelectedIndex(4);
							break;
						}	
						String setGender = rs.getString("driver_gender");
						switch(setGender) {
						case "Male":
							genderCom.setSelectedIndex(0);
							break;
						case "Female":
							genderCom.setSelectedIndex(1);
							break;
								
						}
						//con.close();

						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		searchID.setColumns(10);
		searchID.setBounds(122, 16, 116, 26);
		panel.add(searchID);
		

	}
	

	private void createSaveBtn(JPanel panel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "INSERT INTO vanrental.driver(Driver_ID, DriverFname, DriverMname, DriverLname, Address, DrivePhoneNum, DriverEmail, Driver_Bdate, Insurance,Guardian_name, Guardian_Pnum,Van_Code, driver_gender)"
							+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(driverId.getText()));
					pst.setString(2, fName.getText());
					pst.setString(3, mName.getText());
					pst.setString(4, lName.getText());
					pst.setString(5, address.getText());
				    pst.setInt(6, Integer.parseInt(phoneNum.getText()));
				    pst.setString(7, email.getText());
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(bdate.getDate());
					pst.setString(8, date);
				    String insurance;
					insurance = comboInsurance.getSelectedItem().toString();
					pst.setString(9, insurance);
					pst.setString(10, GuardianName.getText());
					pst.setInt(11, Integer.parseInt(GuardianNum.getText()));
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(12, vanCode);
					String gender;
					gender = genderCom.getSelectedItem().toString();
					pst.setString(13, gender);
			
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Insert Successfully");
					show_Driver();
					
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(376, 236, 89, 33);
		panel.add(button);
	}
	public ArrayList<Driver> driverList(){
		ArrayList<Driver> driverList = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
			String sql = "select * from vanrental.driver";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			Driver driver;
			while(rs.next()) {
				driver =new Driver (rs.getInt("Driver_ID"), rs.getString("DriverFname"), rs.getString("DriverMname"),rs.getString("DriverLname"), rs.getString("DriverEmail"), rs.getString("Driver_Bdate"),
						rs.getString("Address"), rs.getInt("DrivePhoneNum"), rs.getString("driver_gender"), rs.getInt("Guardian_Pnum"),rs.getString("Guardian_name"),rs.getString("Van_Code"),rs.getString("Insurance"));
				driverList.add(driver);
				
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return driverList;
		
	}
	public void show_Driver() {
		ArrayList<Driver> list = driverList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row= new Object[13];
		for(int i = 0; i<list.size();i++) {
			row[0]= list.get(i).getID();
			row[1]=list.get(i).getfName();
			row[2]=list.get(i).getmName();
			row[3]= list.get(i).getlName();
			row[4]=list.get(i).getEmail();
			row[5]=list.get(i).getBirthday();
			row[6]= list.get(i).getAddress();
			row[7]=list.get(i).getNumber();
			row[8]=list.get(i).getGender();
			row[9]= list.get(i).getGuardianNum();
			row[10]=list.get(i).getGuardianName();
			row[11]=list.get(i).getVanCode();
			row[12]=list.get(i).getInsurance();
			model.addRow(row);
		}
		
	}
}