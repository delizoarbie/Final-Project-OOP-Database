import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReservationPnl extends JPanel {
	
	private JTable table;
	private JTextField reservationNum;
	private JTextField customerId;
	private JTextField fName;
	private Connection con = null;
	private PreparedStatement pst = null;
	private PreparedStatement pst1 = null;
	private ResultSet rs = null;
	private JTextField mName;
	private JTextField lName;
	private JDateChooser reservationDate;
	private JComboBox comboVanCode;
	private JLabel lblReservation;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblReservationNumber;
	private JLabel lblCustomerId ;
	private JLabel Fname;
	private JLabel lblVanCode;
	private JLabel lblReservationDate;
	private JLabel lblLastName;
	private JLabel MName;
	private JButton resetBtn;
	private JButton saveBtn;
	private JButton btnUpdate;
	private JButton btnCancel;
	
	
	/**
	 * Create the panel.
	 */
	public ReservationPnl() {
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		
		lblReservation = new JLabel("Reservation");
		scrollPane = new JScrollPane();
		table = new JTable();
		panel_1 = new JPanel();
		panel = new JPanel();
		lblReservationNumber = new JLabel("Reservation Number");
		lblCustomerId = new JLabel("Customer ID");
		lblReservationDate = new JLabel("Reservation Date");
		lblLastName = new JLabel("Last Name");
		Fname = new JLabel("First Name");
		MName = new JLabel("Middle Name");
		lblVanCode = new JLabel("Van Code");
		reservationNum = new JTextField();
		customerId = new JTextField();
		fName = new JTextField();
		mName = new JTextField();
		lName = new JTextField();
		btnUpdate = new JButton("Update");
		resetBtn = new JButton("Reset");
		
		btnCancel = new JButton("Cancel");
		comboVanCode = new JComboBox();
		reservationDate = new JDateChooser();
		saveBtn = new JButton("Save");
		
		createlblReservation();
		createScrollpane();
		createSelectTable();
		createPanelAdd(panel_1);
		createPanel2(panel_1, panel);
		lblReserveNum(panel, lblReservationNumber);
		createReserveTextField(panel);
		createCustomerIDText(panel);
		lblFname(panel, Fname);
		createfNameText(panel);
		lblReseveDate(panel, lblReservationDate);
		createUpdateBtn(panel, btnUpdate);
		createResetBtn();
		createCancelBtn(panel, btnCancel);
		lblVanCode(panel, lblVanCode);
		comboVanCode(panel);
		createreservationDate(panel);
		lblMname(panel, MName);
		createMnameText(panel);
		lblLname(panel, lblLastName);
		createlnameText(panel);
		createSavaBtn(panel, saveBtn);
		lblCustomerId(panel, lblCustomerId);
		

	}
	private void createResetBtn() {
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservationNum.setText("");
				customerId.setText("");
				fName.setText("");
				mName.setText("");
				lName.setText("");
				reservationDate.setDate(new Date());
				comboVanCode.setSelectedIndex(0);
				
			}
		});
		resetBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		resetBtn.setBounds(588, 192, 89, 33);
		panel.add(resetBtn);
	}
	private void createScrollpane() {
		scrollPane.setBounds(10, 345, 1027, 215);
		add(scrollPane);
	}
	private void createSavaBtn(JPanel panel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "INSERT INTO vanrental.reservation (Reservation_ID, ResDate, Van_Code) VALUES(?,?,?)";
					String sql1 = "insert into vanrental.customer (Customer_ID, Customer_FName, customer_MName, Customer_LName) values (?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql1);
					pst.setInt(1, Integer.parseInt(reservationNum.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(reservationDate.getDate());
					pst.setString(2,date);
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(3, vanCode);
					
					pst1.setInt(1, Integer.parseInt(customerId.getText()));
					pst1.setString(2, fName.getText());
					pst1.setString(3, mName.getText());
					pst1.setString(4, lName.getText());
					pst.executeUpdate();
					pst1.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Insert Successfully");
					showTableData();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(393, 192, 89, 33);
		panel.add(button);
	}
	private void createlnameText(JPanel panel) {
		lName.setColumns(10);
		lName.setBounds(416, 136, 115, 30);
		panel.add(lName);
	}
	private void lblLname(JPanel panel, JLabel lblLastName) {
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLastName.setBounds(311, 136, 89, 30);
		panel.add(lblLastName);
	}
	private void createMnameText(JPanel panel) {
		mName.setColumns(10);
		mName.setBounds(416, 90, 115, 35);
		panel.add(mName);
	}
	private void lblMname(JPanel panel, JLabel MName) {
		MName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		MName.setBounds(310, 90, 89, 30);
		panel.add(MName);
	}
	private void createreservationDate(JPanel panel) {
		reservationDate.setBounds(672, 40, 102, 30);
		panel.add(reservationDate);
	}
	private void comboVanCode(JPanel panel) {
		comboVanCode.setModel(new DefaultComboBoxModel(new String[] {"1234", "1235", "1236", "1237", "1238"}));
		comboVanCode.setBounds(166, 136, 115, 33);
		panel.add(comboVanCode);
	}
	private void lblVanCode(JPanel panel, JLabel lblVanCode) {
		lblVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblVanCode.setBounds(21, 136, 88, 30);
		panel.add(lblVanCode);
	}
	private void createCancelBtn(JPanel panel, JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.reservation where Reservation_ID=?";
					String sql2 = "DELETE from vanrental.customer where Customer_ID=?";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql2);
					
					pst.setInt(1, Integer.parseInt(reservationNum.getText()));
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
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnCancel.setBounds(685, 192, 89, 33);
		panel.add(btnCancel);
	}
	private void createUpdateBtn(JPanel panel, JButton button_1) {
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.reservation SET ResDate=?, Van_Code=? where Reservation_ID=?";
					String sql2 = "UPDATE vanrental.customer SET Customer_FName=?, customer_MName=?, Customer_LName=? where Customer_ID=?";
					pst = con.prepareStatement(sql);
					pst1 = con.prepareStatement(sql2);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(reservationDate.getDate());
					pst.setString(1,date);
					String vanCode;
					vanCode = comboVanCode.getSelectedItem().toString();
					pst.setString(2, vanCode);
					pst.setInt(3, Integer.parseInt(reservationNum.getText()));
					
					
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
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(489, 192, 89, 33);
		panel.add(button_1);
	}
	private void lblReseveDate(JPanel panel, JLabel lblReservationDate) {
		lblReservationDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblReservationDate.setBounds(557, 40, 105, 30);
		panel.add(lblReservationDate);
	}
	private void createfNameText(JPanel panel) {
		fName.setColumns(10);
		fName.setBounds(416, 41, 115, 35);
		panel.add(fName);
	}
	private void lblFname(JPanel panel, JLabel Fname) {
		Fname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Fname.setBounds(311, 40, 88, 30);
		panel.add(Fname);
	}
	private void createCustomerIDText(JPanel panel) {
		customerId.setColumns(10);
		customerId.setBounds(166, 92, 115, 33);
		panel.add(customerId);
	}
	private void lblCustomerId(JPanel panel, JLabel lblCustomerId) {
		lblCustomerId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCustomerId.setBounds(21, 95, 88, 30);
		panel.add(lblCustomerId);
	}
	private void createReserveTextField(JPanel panel) {
		reservationNum.setColumns(10);
		reservationNum.setBounds(166, 41, 115, 35);
		panel.add(reservationNum);
	}
	private void lblReserveNum(JPanel panel, JLabel lblReservationNumber) {
		lblReservationNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblReservationNumber.setBounds(21, 40, 122, 30);
		panel.add(lblReservationNumber);
	}
	private void createPanel2(JPanel panel_1, JPanel panel) {
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 784, 236);
		panel_1.add(panel);
		panel.setLayout(null);
	}
	private void createPanelAdd(JPanel panel_1) {
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(113, 54, 807, 261);
		add(panel_1);
		panel_1.setLayout(null);
	}
	private void createSelectTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				reservationNum.setText(model.getValueAt(i, 0).toString());
			
				String vanCode = model.getValueAt(i, 2).toString();
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
				customerId.setText(model.getValueAt(i, 3).toString());
				fName.setText(model.getValueAt(i, 4).toString());
				mName.setText(model.getValueAt(i, 5).toString());
				lName.setText(model.getValueAt(i, 6).toString());
				
				
			}
		});
		scrollPane.setViewportView(table);
		showTableData();
	}
	private void createlblReservation() {
		lblReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReservation.setBounds(20, 11, 99, 28);
		add(lblReservation);
	}
	public void showTableData() {
		try {
			String sql = "select Reservation_ID, ResDate, Van_Code, Customer_ID, Customer_FName, Customer_MName, Customer_LName from reservation JOIN customer where reservation.Reservation_ID = customer.Customer_ID";
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
