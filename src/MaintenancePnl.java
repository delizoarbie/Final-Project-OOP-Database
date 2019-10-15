import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MaintenancePnl extends JPanel {
	private JTable table;
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private JTextField garageCode;
	private JTextField address;
	private JComboBox maintenanceType;
	private JComboBox vanCode ;
	private JDateChooser maintenanceDate ;
	private JTextField cost;
	private JLabel lblVanCode;
	private JLabel lblmainType;
	private JLabel lbldate;
	private JLabel lblCost; 
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1 ;
	private JLabel lblHome ;
	private JPanel panel_1 ;
	private JPanel panel;
	private JButton btnSave;
	private JButton btnUpdate ;
	private JButton btnReset;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	
	
	
	/**
	 * Create the panel.
	 */
	public MaintenancePnl() {
		table = new JTable();
		lblHome = new JLabel("Maintenace");
		lblVanCode = new JLabel("Van Code");
		lblmainType = new JLabel("Maintenance Type");
		lblCost = new JLabel("cost");
		lblNewLabel = new JLabel("Garage Code");
		lbldate = new JLabel("Maintenance Date");
		btnCancel = new JButton("Cancel");
		btnReset = new JButton("Reset");
		btnUpdate = new JButton("Update");
		btnSave = new JButton("Save");
		maintenanceType = new JComboBox();
		vanCode = new JComboBox();
		maintenanceDate = new JDateChooser();
		address = new JTextField();
		cost = new JTextField();
		
		panel_1 = new JPanel();
		panel = new JPanel();
		scrollPane = new JScrollPane();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Garage Code", "Maintenance Type", "Address", "Maintenance Date", "Cost", "Van Code"
				}
			));
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		scrollPane.setBounds(10, 303, 1027, 171);
		add(scrollPane);
		
		
		
		createSelectTable(scrollPane);
		creteBackgroundpnl();
		createLabels();
		
		createSaveBtn(btnSave);
		createUpdateBtn(btnUpdate);
		cretaeResetBtn(btnReset);
		createCancelBtn(btnCancel);
		createTxt_Date_Combo();
		
		show_Maintenance();
		
	}
	private void createTxt_Date_Combo() {
		garageCode = new JTextField();
		garageCode.setColumns(10);
		garageCode.setBounds(602, 31, 117, 32);
		panel.add(garageCode);
		
		address.setColumns(10);
		address.setBounds(602, 91, 117, 32);
		panel.add(address);
		
		maintenanceType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		maintenanceType.setModel(new DefaultComboBoxModel(new String[] {"Aircon", "Tires", "Change Oil"}));
		maintenanceType.setBounds(171, 89, 117, 32);
		panel.add(maintenanceType);
		
		vanCode.setModel(new DefaultComboBoxModel(new String[] {"1234", "1235", "1236", "1237", "1238"}));
		vanCode.setBounds(171, 32, 117, 28);
		panel.add(vanCode);
		
		maintenanceDate.setBounds(171, 148, 117, 26);
		panel.add(maintenanceDate);
			
		cost.setColumns(10);
		cost.setBounds(602, 130, 117, 32);
		panel.add(cost);
	}
	private void createSaveBtn(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql= "INSERT INTO vanrental.maintenance (Garage_Code,Main_Type,Address,Main_Date,Cost,vanCode)VALUES(?,?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(garageCode.getText()));
					String mainType;
					mainType = maintenanceType.getSelectedItem().toString();
					pst.setString(2, mainType);
					pst.setString(3, address.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(maintenanceDate.getDate());
					pst.setString(4,date);
					pst.setString(5, cost.getText());
					String van_Code;
					van_Code = vanCode.getSelectedItem().toString();
					pst.setString(6, van_Code);
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Inserted Successfully");
					show_Maintenance();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(338, 163, 89, 33);
		panel.add(button);
	}
	private void creteBackgroundpnl() {
		panel_1.setBackground(new Color(0, 102, 102));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(149, 58, 766, 234);
		add(panel_1);
		panel_1.setLayout(null);
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 16, 744, 207);
		panel_1.add(panel);
		panel.setLayout(null);
		
	}
	private void createLabels() {
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHome.setBounds(10, 11, 102, 27);
		add(lblHome);
		
		lblVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblVanCode.setBounds(33, 31, 72, 20);
		panel.add(lblVanCode);
		
		
		lblmainType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmainType.setBounds(33, 88, 117, 26);
		panel.add(lblmainType);
		
		lbldate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbldate.setBounds(35, 148, 115, 20);
		panel.add(lbldate);
		
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(473, 34, 84, 26);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(473, 94, 84, 26);
		panel.add(lblNewLabel_1);
		
		
		lblCost.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCost.setBounds(473, 126, 84, 26);
		panel.add(lblCost);
	}
	private void createCancelBtn(JButton button_3) {
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.maintenance where Garage_Code=?";
					pst = con.prepareStatement(sql);
					
					pst.setInt(1, Integer.parseInt(garageCode.getText()));
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					show_Maintenance();
					con.close();
				
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(630, 163, 89, 33);
		panel.add(button_3);
	}
	private void cretaeResetBtn(JButton button_2) {
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vanCode.setSelectedIndex(0);
				garageCode.setText("");
				maintenanceType.setSelectedIndex(0);
				address.setText("");
				cost.setText("");
				maintenanceDate.setDate(new Date());;	
			}
		});
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_2.setBounds(533, 163, 89, 33);
		panel.add(button_2);
	}
	private void createUpdateBtn(JButton button_1) {
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql= "UPDATE vanrental.maintenance SET Main_Type=?,Address=?,Main_Date=?,Cost=?,vanCode=? where Garage_Code=?";
					pst = con.prepareStatement(sql);
					
					String mainType;
					mainType = maintenanceType.getSelectedItem().toString();
					pst.setString(1, mainType);
					pst.setString(2, address.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(maintenanceDate.getDate());
					pst.setString(3,date);
					pst.setString(4, cost.getText());
					String van_Code;
					van_Code = vanCode.getSelectedItem().toString();
					pst.setString(5, van_Code);
					pst.setInt(6, Integer.parseInt(garageCode.getText()));
					
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					show_Maintenance();
					con.close();
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} 
				
			}
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(434, 163, 89, 33);
		panel.add(button_1);
	}
	private void createSelectTable(JScrollPane scrollPane) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				garageCode.setText(model.getValueAt(i, 0).toString());
				
				String mainType = model.getValueAt(i, 1).toString();
				switch(mainType) {
				case "1234":
					maintenanceType.setSelectedIndex(0);
					break;
				case "1235":
					maintenanceType.setSelectedIndex(1);
					break;
				case "1236":
					maintenanceType.setSelectedIndex(2);
					break;
				case "1237":
					maintenanceType.setSelectedIndex(3);
					break;
				case "1238":
					maintenanceType.setSelectedIndex(4);
					break;
				}
				address.setText(model.getValueAt(i, 2).toString());
				try {
					int srow = table.getSelectedRow();
					Date date = new SimpleDateFormat ("yyyy-MM-dd").parse((String)model.getValueAt(srow, 3));
					maintenanceDate.setDate(date);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
				}
				cost.setText(model.getValueAt(i, 4).toString());
				String van_Code = model.getValueAt(i, 5).toString();
				switch(van_Code) {
				case "1234":
					vanCode.setSelectedIndex(0);
					break;
				case "1235":
					vanCode.setSelectedIndex(1);
					break;
				case "1236":
					vanCode.setSelectedIndex(2);
					break;
				case "1237":
					vanCode.setSelectedIndex(3);
					break;
				case "1238":
					vanCode.setSelectedIndex(4);
					break;
				}
			}
		});
				scrollPane.setViewportView(table);
	}

	public ArrayList<Maintenance> maintenanceList(){
		ArrayList<Maintenance> maintenanceList = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
			String sql = "select * from vanrental.maintenance";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			Maintenance maintenance;
			while(rs.next()) {
				maintenance = new Maintenance(rs.getInt("Garage_Code"), rs.getString("Main_Type"), rs.getString("Address"),rs.getString("Main_Date"), rs.getInt("Cost"), rs.getString("vanCode"));
				maintenanceList.add(maintenance);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maintenanceList;
	}
	public void show_Maintenance() {
		ArrayList<Maintenance> list = maintenanceList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row= new Object[6];
		for(int i = 0; i<list.size();i++) {
			row[0]= list.get(i).getGarageCode();
			row[1]=list.get(i).getMainType();
			row[2]=list.get(i).getAddress();
			row[3]=list.get(i).getMainDate();
			row[4]= list.get(i).getCost();
			row[5]=list.get(i).getVanCode();
			
			model.addRow(row);
		}
		
	}

}
