import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentPnl extends JPanel {
	private JTextField paymentNum;
	private JTextField amount;
	private JDateChooser paymentDate;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBox comboCategory;
	private JComboBox paymentType;
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private JTextField employeeID;
	private JLabel lblHome;
	private JLabel lblPaymentNumber;
	private JLabel lblAmount;
	private JLabel lblPaymentType;
	private JLabel lblPaymentDate;
	private JLabel labelCat;
	private JPanel panel_1 ;
	private JPanel panel;
	private JLabel lblPayment;
	/**
	 * Create the panel.
	 */
	public PaymentPnl() {
		lblPaymentDate = new JLabel("Payment Date");
		lblPaymentType = new JLabel("Payment Type");
		labelCat = new JLabel("Category");
		lblPaymentNumber = new JLabel("Payment Number");
		lblHome = new JLabel("Payment");
		paymentDate = new JDateChooser();
		paymentType = new JComboBox();
		comboCategory = new JComboBox();
		
		setBackground(Color.WHITE);
		setBounds(271, 34, 1047, 560);
		setLayout(null);
		
		
		
		createBackgroundPnl();
		
		paymentNum = new JTextField();
		paymentNum.setColumns(10);
		paymentNum.setBounds(195, 42, 149, 29);
		panel.add(paymentNum);
		
		
		amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(195, 83, 149, 29);
		panel.add(amount);
		
		
		lblPaymentType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPaymentType.setBounds(438, 40, 88, 30);
		panel.add(lblPaymentType);
		
		
		lblPaymentDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPaymentDate.setBounds(438, 81, 105, 30);
		panel.add(lblPaymentDate);
		
		JButton saveBtn = new JButton("Save");
		createSaveBtn(panel, saveBtn);
		
		JButton btnUpdate = new JButton("Update");
		createUpdateBtn(panel, btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		createDeleteBtn(panel, btnDelete);
		
		JButton btnReset = new JButton("Reset");
		createResetBtn(panel, btnReset);
		
		paymentDate.setBounds(612, 83, 149, 30);
		panel.add(paymentDate);
		
		
		paymentType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		paymentType.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Mastercard", "Check", "Visa"}));
		paymentType.setBounds(612, 40, 150, 30);
		panel.add(paymentType);
		
		
		labelCat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelCat.setBounds(21, 122, 88, 30);
		panel.add(labelCat);
		
		
		comboCategory.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboCategory.setModel(new DefaultComboBoxModel(new String[] {"Booking", "Reservation", "Repair", "Maintenance"}));
		comboCategory.setBounds(195, 122, 150, 30);
		panel.add(comboCategory);
		
		JLabel TransactionNum = new JLabel("Employee ID");
		TransactionNum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		TransactionNum.setBounds(438, 122, 126, 30);
		panel.add(TransactionNum);
		
		employeeID = new JTextField();
		scrollPane = new JScrollPane();
		employeeID.setColumns(10);
		employeeID.setBounds(613, 124, 149, 29);
		panel.add(employeeID);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblAmount.setBounds(21, 82, 88, 30);
		panel.add(lblAmount);
		
		lblPaymentNumber= new JLabel("Payment Number");
		lblPaymentNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPaymentNumber.setBounds(21, 41, 88, 30);
		panel.add(lblPaymentNumber);
		
		
		scrollPane.setBounds(10, 392, 1016, 157);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Payment Number", "Payment Type", "Payment Date", "Amount", "Category","Employee ID"
				}
			));
		
		lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPayment.setBounds(27, 25, 102, 27);
		add(lblPayment);
		show_Payment();
		createselectDataTable();
	}
	public void createBackgroundPnl() {
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(116, 78, 803, 252);
		add(panel_1);
		panel_1.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 783, 229);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
	}
	public void createSaveBtn(JPanel panel, JButton saveBtn) {
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "INSERT INTO vanrental.payment (Payment_No, Payment_Type, Employee_ID, Payment_Date, Amount, Category)"
							+"VALUES (?,?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(paymentNum.getText()));
					String payType;
					payType = paymentType.getSelectedItem().toString();
					pst.setString(2, payType);
					pst.setInt(3, Integer.parseInt(employeeID.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(paymentDate.getDate());
					pst.setString(4, date);
					pst.setString(5, amount.getText());
					String category;
					category = comboCategory.getSelectedItem().toString();
					pst.setString(6, category);
					
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Insert Successfully");
					show_Payment();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		saveBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		saveBtn.setBounds(374, 188, 89, 33);
		panel.add(saveBtn);
	}
	public void createUpdateBtn(JPanel panel, JButton btnUpdate) {
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
					String sql = "UPDATE vanrental.payment SET Payment_Type=?, Employee_ID=?, Payment_Date=?, Amount=?, Category=? where Payment_No=?";
					pst = con.prepareStatement(sql);

					String payType;
					payType = paymentType.getSelectedItem().toString();
					pst.setString(1, payType);
					pst.setInt(2, Integer.parseInt(employeeID.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(paymentDate.getDate());
					pst.setString(3, date);
					pst.setString(4, amount.getText());
					String category;
					category = comboCategory.getSelectedItem().toString();
					pst.setString(5, category);
					pst.setInt(6, Integer.parseInt(paymentNum.getText()));
					pst.executeUpdate();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					show_Payment();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnUpdate.setBounds(474, 188, 89, 33);
		panel.add(btnUpdate);
	}
	public void createResetBtn(JPanel panel, JButton btnReset) {
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentNum.setText("");
				amount.setText("");
				paymentDate.setDate(new Date());
				comboCategory.setSelectedIndex(0);
				paymentType.setSelectedIndex(0);
				employeeID.setText("");
			}
		});
		btnReset.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnReset.setBounds(573, 188, 89, 33);
		panel.add(btnReset);
	}
	public void createDeleteBtn(JPanel panel, JButton btnDelete) {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421");
				String sql = "DELETE from vanrental.payment where Payment_No=?";
				pst = con.prepareStatement(sql);
				
				pst.setInt(1, Integer.parseInt(paymentNum.getText()));
				
				pst.executeUpdate();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
				show_Payment();
				con.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDelete.setBounds(672, 188, 89, 33);
		panel.add(btnDelete);
	}
	public void createselectDataTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				paymentNum.setText(model.getValueAt(i, 0).toString());
				String payment = model.getValueAt(i, 1).toString();
				switch(payment) {
				case "Cash":
					paymentType.setSelectedIndex(0);
					break;
				case "Mastercard":
					paymentType.setSelectedIndex(1);
					break;
				case "Check":
					paymentType.setSelectedIndex(2);
					break;
				case "Visa":
					paymentType.setSelectedIndex(3);
					break;
				}
				try {
					int srow = table.getSelectedRow();
					Date date = new SimpleDateFormat ("yyyy-MM-dd").parse((String)model.getValueAt(srow, 2));
					paymentDate.setDate(date);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					
				}
				amount.setText(model.getValueAt(i, 3).toString());
				String category = model.getValueAt(i, 4).toString();
				switch(category) {
				case "Booking":
					comboCategory.setSelectedIndex(0);
					break;
				case "Reservation":
					comboCategory.setSelectedIndex(1);
					break;
				case "Repair":
					comboCategory.setSelectedIndex(2);
					break;
				case "Maintenance":
					comboCategory.setSelectedIndex(3);
					break;
				}
				employeeID.setText(model.getValueAt(i, 5).toString());
				
				
			}
		});
		//showTableData();
		scrollPane.setViewportView(table);
	}

	public ArrayList<Payment> repairList(){
		ArrayList<Payment> repairList = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanrental?useTimezone=true&serverTimezone=UTC","root","zaza0421"); 
			String sql = "select * from vanrental.payment";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			Payment repair;
			while(rs.next()) {
				repair = new Payment(rs.getInt("Payment_No"), rs.getString("Payment_Type"),rs.getString("Payment_Date"),rs.getInt("Amount"), rs.getString("Category"),rs.getInt("Employee_ID"),rs.getInt("Transaction_No"));
				repairList.add(repair);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return repairList;
	}
	public void show_Payment() {
		ArrayList<Payment> list = repairList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row= new Object[6];
		for(int i = 0; i<list.size();i++) {
			row[0]= list.get(i).getPaymentNo();
			row[1]=list.get(i).getPaymentType();
			row[2]=list.get(i).getPaymentDate();
			row[3]=list.get(i).getAmount();
			row[4]= list.get(i).getCategory();
			row[5]=list.get(i).getEmployeeID();
		
			model.addRow(row);
		}
		
	}
	
}
