
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
//import java.sql.Date;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeePnl extends JPanel {
	private JTextField textFNameEmp;
	private JTextField textMnameEmp;
	private JTextField textLnameEmp;
	private JTextField textEmailEmp;
	private JTextField textPosEmp;
	private JTextField textPhoEmp;
	private JTextField txtEmpID;
	private JTextField addressEmp;
	private JDateChooser empBdate;
	private JPanel panel;
	private JTable table;
	private JTextField textUsername;
	private JPasswordField passEmp;
	private JComboBox comboBoxGenEmp;
	private JLabel lblPhoneNumberEmp;
	private JLabel lblAdmin;
	private JLabel lblEmpFname;
	private JLabel lblEmpMidName;
	private JLabel lblLastNameEmp;
	private JLabel lblEmpGen;
	private JLabel lblPositionEmp;
	private JLabel lblBirthdateEmp;
	private JLabel lblEmailEmp;
	private JButton btnSaveEmp;
	private JButton btnDeleteEmp;
	private JButton button;
	private JButton btnUpdateEmp;
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private JLabel lblSearchId;
	private JTextField searchID;
	
	/**
	 * Create the panel.
	 */
	public EmployeePnl() {
		btnUpdateEmp = new JButton("Update");
		btnDeleteEmp = new JButton("Delete");
		button = new JButton("Reset");
		empBdate = new JDateChooser();
		textMnameEmp = new JTextField();
		textLnameEmp = new JTextField();
		textEmailEmp = new JTextField();
		textPosEmp = new JTextField();
		textPhoEmp = new JTextField();
		lblAdmin = new JLabel("Employee");
		lblEmpFname = new JLabel("First Name");
		table = new JTable();
		btnSaveEmp = new JButton("Save");
		comboBoxGenEmp = new JComboBox();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Staff Id", "First Name", "Middle Name", "Last Name", "email", "Birthday", "Address", "Number", "Gender", "Username", "Password", "Position"
			}
		));
		
		createSelectTable();
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
				
		show_Employee();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(126, 48, 808, 308);
		add(panel_1);
		panel_1.setLayout(null);
		panel = new JPanel();
		panel.setBounds(10, 11, 788, 286);
		panel_1.add(panel);
		
		
		empBdate.setBounds(631, 64, 125, 30);
		panel.add(empBdate);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		textFNameEmp = new JTextField();
		textFNameEmp.setBounds(138, 108, 125, 33);
		panel.add(textFNameEmp);
		textFNameEmp.setColumns(10);
		
		createlblName();
		
		
		lblLastNameEmp = new JLabel("Last Name");
		createlblLnameEmp(lblLastNameEmp);
	
		
		lblEmpGen = new JLabel("Gender");
		createlblGenEmp(lblEmpGen);
		
		lblPositionEmp = new JLabel("Position");
		createlblPosEmp(lblPositionEmp);
		
		lblBirthdateEmp = new JLabel("Birthdate");
		createlblBdateEmp(lblBirthdateEmp);
		
		lblPhoneNumberEmp = new JLabel("Phone Number");
		createlblNumEmp(lblPhoneNumberEmp);
		
		
		comboBoxGenEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBoxGenEmp.setEditable(true);
		createComboGenEmp(comboBoxGenEmp);
		
		lblEmailEmp = new JLabel("Email");
		createlblEmailEmp(lblEmailEmp);
		
	
		
		createBtnSave(comboBoxGenEmp, btnSaveEmp);
		
		
		createUpdateBtn(btnUpdateEmp);
		
		
		btnDeleteEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.employee where Employee_ID=?";
					pst = con.prepareStatement(sql);
					
					pst.setInt(1, Integer.parseInt(txtEmpID.getText()));
					
					pst.executeUpdate();

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					show_Employee();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeleteEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteEmp.setBounds(572, 242, 89, 33);
		panel.add(btnDeleteEmp);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmployeeId.setBounds(26, 71, 89, 23);
		panel.add(lblEmployeeId);
		
		txtEmpID = new JTextField();
		txtEmpID.setColumns(10);
		txtEmpID.setBounds(138, 64, 125, 33);
		panel.add(txtEmpID);
		
		JLabel lblAddressEmp = new JLabel("Address");
		lblAddressEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblAddressEmp.setBounds(531, 105, 69, 30);
		panel.add(lblAddressEmp);
		
		addressEmp = new JTextField();
		addressEmp.setColumns(10);
		addressEmp.setBounds(631, 105, 125, 30);
		panel.add(addressEmp);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUsername.setBounds(290, 68, 69, 26);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPassword.setBounds(290, 111, 69, 24);
		panel.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(379, 64, 125, 33);
		panel.add(textUsername);
		
		passEmp = new JPasswordField();
		passEmp.setBounds(379, 108, 125, 32);
		panel.add(passEmp);
		createTextMnameEmo();
		createTextLnameEmp();
		createTextEmailEmp();
		createTextPosEmp();
		createTextPhoEmp();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 367, 1027, 193);
		add(scrollPane);
		
		
		scrollPane.setViewportView(table);

		
		createResetBtn(button);
		
		lblSearchId = new JLabel("Search ID");
		lblSearchId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSearchId.setBounds(26, 29, 89, 23);
		panel.add(lblSearchId);
		
		searchID = new JTextField();
		searchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql= "select * from vanrental.employee  WHERE Employee_ID =?";
					pst = con.prepareStatement(sql);
					pst.setString(1, searchID.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						String setID = rs.getString("Employee_ID");
						txtEmpID.setText(setID);
						
						String setfName = rs.getString("Employee_FName");
						textFNameEmp.setText(setfName);
						String setmName = rs.getString("Employee_MName");
						textMnameEmp.setText(setmName);
						String setlName = rs.getString("Employee_LName");
						textLnameEmp.setText(setlName);
						String setEmail = rs.getString("Employee_Email");
						textEmailEmp.setText(setEmail);
						empBdate.setDate(rs.getDate("Employee_Birthdate"));
						String setAdd = rs.getString("Employee_Address");
						addressEmp.setText(setAdd);
						String setPhone = rs.getString("Employee_PhoneNum");
						textPhoEmp.setText(setPhone);
						String setGender = rs.getString("Employee_Gender");
						switch(setGender) {
						case "Male":
							comboBoxGenEmp.setSelectedIndex(0);
							break;
						case "Female":
							comboBoxGenEmp.setSelectedIndex(1);
							break;
								
						}

						String setUse = rs.getString("Employee_Username");
						textUsername.setText(setUse);
						String setPass = rs.getString("Employee_Password");
						passEmp.setText(setPass);
						String setPos = rs.getString("Employee_Position");
						textPosEmp.setText(setPos);
						
						
						
						
						
						con.close();
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		searchID.setColumns(10);
		searchID.setBounds(138, 22, 125, 33);
		panel.add(searchID);

	}
	public void createUpdateBtn(JButton btnUpdateEmp) {
		btnUpdateEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.employee SET  Employee_FName=?, Employee_MName=? , Employee_LName=?, Employee_Gender=?, Employee_Position=?, Employee_Birthdate=?, Employee_PhoneNum=?, Employee_Email=?, Employee_Password=?, Employee_Address=?, Employee_Username=? where Employee_ID=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, textFNameEmp.getText());
					pst.setString(2,textMnameEmp.getText());
					pst.setString(3, textLnameEmp.getText());
					String gender;
					gender = comboBoxGenEmp.getSelectedItem().toString();
					pst.setString(4, gender);
					pst.setString(5, textPosEmp.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(empBdate.getDate());
					pst.setString(6, date);
					pst.setInt(7, Integer.parseInt(textPhoEmp.getText()));
					pst.setString(8, textEmailEmp.getText());
					pst.setString(9, passEmp.getText().toString());
					pst.setString(10, addressEmp.getText());
					pst.setString(11, textUsername.getText());
					pst.setInt(12, Integer.parseInt(txtEmpID.getText()));
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					show_Employee();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
		});
		btnUpdateEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnUpdateEmp.setBounds(473, 242, 89, 33);
		panel.add(btnUpdateEmp);
	}
	public void createResetBtn(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFNameEmp.setText("");
				textMnameEmp.setText("");
				textLnameEmp.setText("");
				textEmailEmp.setText("");
				textPosEmp.setText("");
				textPhoEmp.setText("");
				txtEmpID.setText("");
				addressEmp.setText("");
				textUsername.setText("");
				passEmp.setText("");
				empBdate.setDate(new Date());
				comboBoxGenEmp.setSelectedIndex(0);
				
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(671, 242, 89, 33);
		panel.add(button);
	}
	public void createlblName() {
		lblEmpMidName = new JLabel("Middle Name");
		lblEmpMidName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmpMidName.setBounds(26, 158, 89, 26);
		panel.add(lblEmpMidName);
		
		lblAdmin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblAdmin.setBounds(31, 11, 119, 28);
		add(lblAdmin);
		
		lblEmpFname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmpFname.setBounds(26, 110, 69, 26);
		panel.add(lblEmpFname);
	}
	public void createSelectTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				txtEmpID.setText(model.getValueAt(i, 0).toString());
				textFNameEmp.setText(model.getValueAt(i, 1).toString());
				textMnameEmp.setText(model.getValueAt(i, 2).toString());
				textLnameEmp.setText(model.getValueAt(i, 3).toString());
				textEmailEmp.setText(model.getValueAt(i, 4).toString());

				try {
					int srow = table.getSelectedRow();
					Date date = new SimpleDateFormat ("yyyy-MM-dd").parse((String)model.getValueAt(srow, 5));
					empBdate.setDate(date);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
				}
				addressEmp.setText(model.getValueAt(i, 6).toString());
				textPhoEmp.setText(model.getValueAt(i, 7).toString());
				String gender1 = model.getValueAt(i, 8).toString();
					switch(gender1) {
					case "Male":
						comboBoxGenEmp.setSelectedIndex(0);
						break;
					case "Female":
						comboBoxGenEmp.setSelectedIndex(1);
						break;
							
					}
				textUsername.setText(model.getValueAt(i, 9).toString());
				passEmp.setText(model.getValueAt(i, 10).toString());
				textPosEmp.setText(model.getValueAt(i, 11).toString());
				
				
				
				
				
				
			}
		});
	}
	public void createBtnSave(JComboBox comboBoxGenEmp, JButton btnSaveEmp) {
		btnSaveEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
					String sql = "insert into vanrental.employee(Employee_ID, Employee_FName, Employee_MName , Employee_LName, Employee_Gender, Employee_Position, Employee_Birthdate, Employee_PhoneNum, Employee_Email, Employee_Password, Employee_Address, Employee_Username)"
							+"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(txtEmpID.getText()));
					pst.setString(2, textFNameEmp.getText());
					pst.setString(3,textMnameEmp.getText());
					pst.setString(4, textLnameEmp.getText());
					String gender;
					gender = comboBoxGenEmp.getSelectedItem().toString();
					pst.setString(5, gender);
					pst.setString(6, textPosEmp.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(empBdate.getDate());
					pst.setString(7, date);
					pst.setInt(8, Integer.parseInt(textPhoEmp.getText()));
					pst.setString(9, textEmailEmp.getText());
					pst.setString(10, passEmp.getText().toString());
					pst.setString(11, addressEmp.getText());
					pst.setString(12, textUsername.getText());
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Inserted Successfully");
					show_Employee();
					con.close();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
					
				}
				
			}
		});
		btnSaveEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSaveEmp.setBounds(374, 242, 89, 33);
		panel.add(btnSaveEmp);
	}
	public void createlblEmailEmp(JLabel lblEmailEmp) {
		lblEmailEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmailEmp.setBounds(290, 201, 58, 30);
		panel.add(lblEmailEmp);
	}
	public void createComboGenEmp(JComboBox comboBoxGenEmp) {
		comboBoxGenEmp.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBoxGenEmp.setBounds(631, 148, 125, 36);
		panel.add(comboBoxGenEmp);
	}
	public void createlblNumEmp(JLabel lblPhoneNumberEmp) {
		lblPhoneNumberEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPhoneNumberEmp.setBounds(527, 199, 96, 30);
		panel.add(lblPhoneNumberEmp);
	}
	public void createlblBdateEmp(JLabel lblBirthdateEmp) {
		lblBirthdateEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBirthdateEmp.setBounds(531, 68, 69, 26);
		panel.add(lblBirthdateEmp);
	}
	public void createlblPosEmp(JLabel lblPositionEmp) {
		lblPositionEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPositionEmp.setBounds(290, 156, 69, 26);
		panel.add(lblPositionEmp);
	}
	public void createlblGenEmp(JLabel lblEmpGen) {
		lblEmpGen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmpGen.setBounds(531, 152, 69, 23);
		panel.add(lblEmpGen);
	}
	public void createTextPhoEmp() {
		textPhoEmp.setColumns(10);
		textPhoEmp.setBounds(631, 196, 125, 33);
		panel.add(textPhoEmp);
		
	}
	public void createTextPosEmp() {
		textPosEmp.setColumns(10);
		textPosEmp.setBounds(379, 151, 125, 33);
		panel.add(textPosEmp);
	}
	public void createTextEmailEmp() {
		textEmailEmp.setColumns(10);
		textEmailEmp.setBounds(379, 196, 125, 33);
		panel.add(textEmailEmp);
	}
	public void createTextLnameEmp() {
		textLnameEmp.setColumns(10);
		textLnameEmp.setBounds(138, 195, 125, 30);
		panel.add(textLnameEmp);
	}
	public void createlblLnameEmp(JLabel lblLastNameEmp) {
		lblLastNameEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLastNameEmp.setBounds(26, 196, 69, 26);
		panel.add(lblLastNameEmp);
	}
	public void createTextMnameEmo() {
		textMnameEmp.setColumns(10);
		textMnameEmp.setBounds(138, 152, 125, 33);
		panel.add(textMnameEmp);
	}
	public ArrayList<Employee> employeeList(){
		ArrayList<Employee> employeeList = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
			String sql = "select * from vanrental.employee";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			Employee employee;
			while(rs.next()) {
				employee = new Employee(rs.getInt("Employee_ID"), rs.getString("Employee_FName"), rs.getString("Employee_MName"),rs.getString("Employee_LName"), rs.getString("Employee_Email"), rs.getString("Employee_Birthdate"),
						rs.getString("Employee_Address"), rs.getInt("Employee_PhoneNum"), rs.getString("Employee_Gender"), rs.getString("Employee_Username"),rs.getString("Employee_Password"),rs.getString("Employee_Position"));
				employeeList.add(employee);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return employeeList;
		
	}
	public void show_Employee() {
		ArrayList<Employee> list = employeeList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row= new Object[12];
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
			row[9]= list.get(i).getUsername();
			row[10]=list.get(i).getPass();
			row[11]=list.get(i).getPosition();
			model.addRow(row);
		}
		
	}
}
