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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RepairPnl extends JPanel {
	
	private JTable table;
	private JTextField repairNum;
	private JTextField cost;
	private JComboBox comboVanCode;
	private JComboBox repairType;
	private JDateChooser repairDate;
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;



	/**
	 * Create the panel.
	 */
	public RepairPnl() {
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		
		JLabel lblHome = new JLabel("Repair");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHome.setBounds(21, 16, 77, 25);
		add(lblHome);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 394, 1027, 244);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Repair Code", "Repair Type", "Repair Date", "Repair Cost", "Van Code"
				}
			));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				repairNum.setText(model.getValueAt(i, 0).toString());
				String insurance = model.getValueAt(i, 1).toString();
				switch(insurance) {
				case "Air Conditioning Service":
					repairType.setSelectedIndex(0);
					break;
				case "Clutches":
					repairType.setSelectedIndex(1);
					break;
				case "Brakes":
					repairType.setSelectedIndex(2);
					break;	
				case "Cam Belts":
					repairType.setSelectedIndex(3);
					break;			
				}
				try {
					int srow = table.getSelectedRow();
					Date date = new SimpleDateFormat ("yyyy-MM-dd").parse((String)model.getValueAt(srow, 2));
					repairDate.setDate(date);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
				}
				cost.setText(model.getValueAt(i, 3).toString());
				String vanCode = model.getValueAt(i, 4).toString();
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

			}
		});
		scrollPane.setViewportView(table);
		show_Repair();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(89, 63, 805, 244);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 783, 222);
		panel_1.add(panel);
		panel.setLayout(null);
		
		repairNum = new JTextField();
		repairNum.setColumns(10);
		repairNum.setBounds(206, 35, 149, 29);
		panel.add(repairNum);
		
		JLabel lblRepairNumber = new JLabel("Repair Number");
		lblRepairNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRepairNumber.setBounds(32, 33, 108, 30);
		panel.add(lblRepairNumber);
		
		JLabel lblVanCode = new JLabel("Van Code");
		lblVanCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblVanCode.setBounds(32, 78, 101, 30);
		panel.add(lblVanCode);
		
		JLabel lblRepairType = new JLabel("Repair Type");
		lblRepairType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRepairType.setBounds(32, 121, 101, 30);
		panel.add(lblRepairType);
		
		JLabel lblRepairDate = new JLabel("Repair Date");
		lblRepairDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRepairDate.setBounds(435, 33, 96, 30);
		panel.add(lblRepairDate);
		
		cost = new JTextField();
		cost.setColumns(10);
		cost.setBounds(609, 79, 149, 29);
		panel.add(cost);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCost.setBounds(435, 77, 96, 30);
		panel.add(lblCost);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "INSERT INTO vanrental.repair (Repair_Code, Repair_Type, Rep_Date, Rep_Cost, Van_Code)VALUES(?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(repairNum.getText()));
					String repType;
					repType= repairType.getSelectedItem().toString();
					pst.setString(2, repType);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(repairDate.getDate());
					pst.setString(3,date);
					pst.setString(4, cost.getText());
					String vanCode;
					vanCode= comboVanCode.getSelectedItem().toString();
					pst.setString(5, vanCode);
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Insert Successfully");
					show_Repair();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(374, 177, 89, 33);
		panel.add(button);
		
		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.repair SET Repair_Type=?, Rep_Date=?, Rep_Cost=?, Van_Code=? where Repair_Code=?";
					pst = con.prepareStatement(sql);
					String repType;
					repType= comboVanCode.getSelectedItem().toString();
					pst.setString(1, repType);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(repairDate.getDate());
					pst.setString(2,date);
					pst.setString(3, cost.getText());
					pst.setInt(4, Integer.parseInt(repairNum.getText()));
					String vanCode;
					vanCode= comboVanCode.getSelectedItem().toString();
					pst.setString(5, vanCode);
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					show_Repair();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(473, 177, 89, 33);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "DELETE from vanrental.repair where Repair_Code=?";
					pst = con.prepareStatement(sql);
					
					pst.setInt(1, Integer.parseInt(repairNum.getText()));
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					show_Repair();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_2.setBounds(572, 177, 89, 33);
		panel.add(button_2);
		
		repairDate = new JDateChooser();
		repairDate.setBounds(609, 33, 149, 30);
		panel.add(repairDate);
		
		repairType = new JComboBox();
		repairType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		repairType.setModel(new DefaultComboBoxModel(new String[] {"Air Conditioning Service", "Clutches", "Brakes", "Cam Belts"}));
		repairType.setBounds(206, 122, 149, 29);
		panel.add(repairType);
		
		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repairNum.setText("");
				cost.setText("");
				comboVanCode.setSelectedIndex(0);
				repairType.setSelectedIndex(0);
				repairDate.setDate(new Date());
				}
		});
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(671, 177, 89, 33);
		panel.add(button_3);
		
		 comboVanCode = new JComboBox();
		comboVanCode.setModel(new DefaultComboBoxModel(new String[] {"1234", "1235", "1236", "1237", "1238"}));
		comboVanCode.setBounds(206, 80, 150, 29);
		panel.add(comboVanCode);
	}
	public void showTableData() {
		try {
			String sql = "select * from vanrental.repair";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
	
	catch(Exception ex) {
		ex.printStackTrace();
		
	}
	}
	public ArrayList<Repair> repairList(){
		ArrayList<Repair> repairList = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
			String sql = "select * from vanrental.repair";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			Repair repair;
			while(rs.next()) {
				repair = new Repair(rs.getInt("Repair_Code"), rs.getString("Repair_Type"), rs.getString("Rep_Date"), rs.getInt("Rep_Cost"), rs.getString("Van_Code"));
				repairList.add(repair);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return repairList;
	}
	public void show_Repair() {
		ArrayList<Repair> list = repairList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row= new Object[5];
		for(int i = 0; i<list.size();i++) {
			row[0]= list.get(i).getRepairCode();
			row[1]=list.get(i).getRepairType();
			row[2]=list.get(i).getRepDate();
			row[3]=list.get(i).getRepCost();
			row[4]= list.get(i).getVanCode();
			model.addRow(row);
		}
		
	}
}
