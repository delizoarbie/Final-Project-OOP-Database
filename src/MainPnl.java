import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class MainPnl extends JPanel {
	
	private JLayeredPane layeredPane;
	private JPanel sideMenu;
	private JButton btnHome;
	private JButton btnBook;
	private JButton btnDriver;
	private JButton btnEmployee;
	private JButton btnReservation;
	private JButton btnRepair;
	private JButton btnPayment;
	private JButton btnMaintenance;
	private JButton btnLogout;
	private JButton exitBtn;
	private BookPnl book;
	private ReservationPnl reservation;
	private DriverPnl driver;
	private EmployeePnl employee;
	private RepairPnl repair;
	private MaintenancePnl maintenance;
	private PaymentPnl payment;
	private JPanel Home;
	private JButton homeReserveBtn;
	private JButton homeBookBtn;
	private JButton homeAdminBtn;
	private JButton homeDriverBtn;
	private JButton homeMaintenanceBtn;
	private JButton homePaymentBtn;
	private JButton homeRepairBtn;
	
	

	/**
	 * Create the panel.
	 */
	public MainPnl() {
		setBackground(Color.WHITE);
		setBounds(271, 34, 1343, 651);
		setLayout(null);
		Home = new JPanel();
		layeredPane = new JLayeredPane();
		book = new BookPnl();
		reservation = new ReservationPnl();
		driver = new DriverPnl();
		employee = new EmployeePnl();
		repair = new RepairPnl();
		maintenance = new MaintenancePnl();
		payment = new PaymentPnl();
		sideMenu = new JPanel();
		btnHome = new JButton("   Home");
		btnHome.setHorizontalAlignment(SwingConstants.LEADING);
		btnBook = new JButton("   Book");
		btnBook.setHorizontalAlignment(SwingConstants.LEADING);
		btnDriver = new JButton("   Driver");
		btnDriver.setHorizontalAlignment(SwingConstants.LEADING);
		btnEmployee = new JButton("   Employee");
		btnEmployee.setHorizontalAlignment(SwingConstants.LEADING);
		btnReservation = new JButton("   Reservation");
		btnReservation.setHorizontalAlignment(SwingConstants.LEADING);
		btnRepair = new JButton("   Repair");
		btnRepair.setHorizontalAlignment(SwingConstants.LEADING);
		btnPayment = new JButton("   Payment");
		btnPayment.setHorizontalAlignment(SwingConstants.LEADING);
		btnMaintenance = new JButton("   Maintenance");
		btnMaintenance.setHorizontalAlignment(SwingConstants.LEADING);
		btnLogout = new JButton("   Log-out");
		btnLogout.setHorizontalAlignment(SwingConstants.LEADING);
		exitBtn = new JButton("X");
		
		
		createLayeredPane(layeredPane);
		addToLayer();
		createSideMenuPnl();
		createHomeBtn(sideMenu, btnHome);
		createBookBtn(sideMenu, btnBook);
		createDriverBtn(sideMenu, btnDriver);
		createAdminBtn(sideMenu, btnEmployee);
		createReservationBtn(sideMenu, btnReservation);
		createRepairBtn(sideMenu, btnRepair);
		createPaymentBtn(btnPayment);
		createMaintenanceBtn(sideMenu, btnMaintenance);
		createLogOutBtn(sideMenu, btnLogout);
		createExitBtn(exitBtn);
		
		
		Home.setBackground(Color.WHITE);
		Home.setLayout(null);
		
		homeReserveBtn = new JButton("");
		createHomeReserveBtn();
		
		homeAdminBtn = new JButton("");
		createHomeAdminBtn();
		
		homeDriverBtn = new JButton("");
		createHomeDriverBtn();
		
		homeMaintenanceBtn = new JButton("");
		createHomeMaintenanceBtn();
		
		homePaymentBtn = new JButton("");
		cretaeHomePaymentBtn();
		
		homeRepairBtn = new JButton("");
		createHomeRepairBtn();
		
		homeBookBtn = new JButton("");
		createHomeBookBtn();
		
		JLabel label = new JLabel("Home");
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		label.setBounds(38, 40, 68, 24);
		Home.add(label);
	
	}
	/**
	 * this method will switch the panel
	 * @param panel
	 */
	public void switchPanel(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	/**
	 * 
	 * @param layeredPane
	 */
	private void createLayeredPane(JLayeredPane layeredPane) {
		layeredPane.setBounds(271, 34, 1046, 565);
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void addToLayer() {

		layeredPane.add(Home, "name_298386865503779");
		layeredPane.add(book);
		layeredPane.add(reservation);
		layeredPane.add(driver);
		layeredPane.add(employee);
		layeredPane.add(repair);
		layeredPane.add(maintenance);
		layeredPane.add(payment);
	}
	private void createHomeRepairBtn() {
		Image img = new ImageIcon(this.getClass().getResource("/tools (1).png")).getImage();
		homeRepairBtn.setIcon(new ImageIcon(img));
		homeRepairBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(repair);
			}
		});
		homeRepairBtn.setBackground(new Color(0, 153, 153));
		homeRepairBtn.setBounds(209, 392, 192, 173);
		Home.add(homeRepairBtn);
	}
	private void cretaeHomePaymentBtn() {
		Image img = new ImageIcon(this.getClass().getResource("/cash.png")).getImage();
		homePaymentBtn.setIcon(new ImageIcon(img));
		homePaymentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(payment);
			}
		});
		homePaymentBtn.setBackground(new Color(0, 153, 153));
		homePaymentBtn.setBounds(451, 392, 192, 173);
		Home.add(homePaymentBtn);
	}
	private void createHomeMaintenanceBtn() {
		Image img = new ImageIcon(this.getClass().getResource("/maintenance (1).png")).getImage();
		homeMaintenanceBtn.setIcon(new ImageIcon(img));
		homeMaintenanceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(maintenance);
			}
		});
		homeMaintenanceBtn.setBackground(new Color(0, 153, 153));
		homeMaintenanceBtn.setBounds(680, 392, 192, 173);
		Home.add(homeMaintenanceBtn);
	}
	private void createHomeDriverBtn() {
		Image img2 = new ImageIcon(this.getClass().getResource("/driver (1).png")).getImage();
		homeDriverBtn.setIcon(new ImageIcon(img2));
		homeDriverBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(driver);
			}
		});
		homeDriverBtn.setBackground(new Color(0, 153, 153));
		homeDriverBtn.setBounds(775, 181, 192, 173);
		Home.add(homeDriverBtn);
	}
	private void createHomeReserveBtn() {
		Image img = new ImageIcon(this.getClass().getResource("/reserved.png")).getImage();
		homeReserveBtn.setIcon(new ImageIcon(img));
		homeReserveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(reservation);
			}
		});
		homeReserveBtn.setBackground(new Color(0, 153, 153));
		homeReserveBtn.setBounds(114, 181, 192, 173);
		Home.add(homeReserveBtn);
		
	}
	private void createHomeAdminBtn() {
		Image img2 = new ImageIcon(this.getClass().getResource("/team (1).png")).getImage();
		homeAdminBtn.setIcon(new ImageIcon(img2));
		homeAdminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(employee);
			}
		});
		homeAdminBtn.setBackground(new Color(0, 153, 153));
		homeAdminBtn.setBounds(555, 181, 192, 173);
		Home.add(homeAdminBtn);
	}
	
	private void createHomeBookBtn() {
		Image img = new ImageIcon(this.getClass().getResource("/driving-license.png")).getImage();
		homeBookBtn.setIcon(new ImageIcon(img));
		homeBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(book);
			}
		});
		homeBookBtn.setBackground(new Color(0, 153, 153));
		homeBookBtn.setBounds(334, 181, 192, 173);
		Home.add(homeBookBtn);
	}
	
	/**
	 * create the side menu panel
	 */
	private void createSideMenuPnl() {
		sideMenu.setForeground(Color.WHITE);
		sideMenu.setBackground(new Color(51, 153, 153));
		sideMenu.setBounds(2, 0, 259, 649);
		add(sideMenu);
		sideMenu.setLayout(null);
	}
	
	
	/**
	 * Creating home button 
	 * And action when the user click the button
	 * @param sideMenu
	 * @param btnHome
	 */

	private void createHomeBtn(JPanel sideMenu, JButton btnHome) {
		Image img = new ImageIcon(this.getClass().getResource("/homepage.png")).getImage();
		btnHome.setIcon(new ImageIcon(img));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(Home);
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(0, 153, 153));
		btnHome.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnHome.setBounds(0, 228, 259, 48);
		sideMenu.add(btnHome);
	}
	
	/**
	 * Creating the Booking button for the customer
	 * And action when the user click the button
	 * @param sideMenu
	 * @param btnBook
	 */
	private void createBookBtn(JPanel sideMenu, JButton btnBook) {
		Image img = new ImageIcon(this.getClass().getResource("/book.png")).getImage();
		btnBook.setIcon(new ImageIcon(img));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(book);
			}
		});
		btnBook.setForeground(Color.WHITE);
		btnBook.setBackground(new Color(0, 153, 153));
		btnBook.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnBook.setBounds(0, 321, 259, 48);
		sideMenu.add(btnBook);
	}
	
	/**
	 * Create Driver button 
	 * And the action
	 * @param sideMenu
	 * @param btnDriver
	 */

	private void createDriverBtn(JPanel sideMenu, JButton btnDriver) {
		Image img = new ImageIcon(this.getClass().getResource("/driver.png")).getImage();
		btnDriver.setIcon(new ImageIcon(img));
		btnDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(driver);
			}
		});
		btnDriver.setForeground(Color.WHITE);
		btnDriver.setBackground(new Color(0, 153, 153));
		btnDriver.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnDriver.setBounds(0, 416, 259, 48);
		sideMenu.add(btnDriver);
	}
	
	/**
	 * Create Admin button
	 * and action when the user click the button
	 * @param sideMenu
	 * @param btnAdmin
	 */

	private void createAdminBtn(JPanel sideMenu, JButton btnAdmin) {
		Image img = new ImageIcon(this.getClass().getResource("/team.png")).getImage();
		btnAdmin.setIcon(new ImageIcon(img));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(employee);
			}
		});
		btnAdmin.setForeground(Color.WHITE);
		btnAdmin.setBackground(new Color(0, 153, 153));
		btnAdmin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnAdmin.setBounds(0, 367, 259, 48);
		sideMenu.add(btnAdmin);
	}
    
	/**
	 * create reservation button 
	 * and action when the user click the button
	 * @param sideMenu
	 * @param btnReservation
	 */
	private void createReservationBtn(JPanel sideMenu, JButton btnReservation) {
		Image img = new ImageIcon(this.getClass().getResource("/reservation.png")).getImage();
		btnReservation.setIcon(new ImageIcon(img));
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(reservation);
			}
		});
		btnReservation.setForeground(Color.WHITE);
		btnReservation.setBackground(new Color(0, 153, 153));
		btnReservation.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnReservation.setBounds(0, 275, 259, 48);
		sideMenu.add(btnReservation);
	}
	
	/**
	 * Create repair button
	 * and action when the user click the button
	 * @param sideMenu
	 * @param btnRepair
	 */
	private void createRepairBtn(JPanel sideMenu, JButton btnRepair) {
		Image img = new ImageIcon(this.getClass().getResource("/tools.png")).getImage();
		btnRepair.setIcon(new ImageIcon(img));
		btnRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(repair);
			}
		});
		btnRepair.setForeground(Color.WHITE);
		btnRepair.setBackground(new Color(0, 153, 153));
		btnRepair.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnRepair.setBounds(0, 460, 259, 48);
		sideMenu.add(btnRepair);
	}
	
	/**
	 * create maintenance button 
	 * and action when the user click the button
	 * @param sideMenu
	 * @param button
	 */

	private void createMaintenanceBtn(JPanel sideMenu, JButton button) {
		Image img = new ImageIcon(this.getClass().getResource("/maintenance.png")).getImage();
		btnMaintenance.setIcon(new ImageIcon(img));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(maintenance);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(0, 153, 153));
		button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		button.setBounds(0, 553, 259, 48);
		sideMenu.add(button);
	}
	
	/**
	 * create payment button 
	 * and the function the user choose the button
	 * @param btnPayment
	 */

	private void createPaymentBtn(JButton btnPayment) {
		Image img = new ImageIcon(this.getClass().getResource("/cash-payment.png")).getImage();
		btnPayment.setIcon(new ImageIcon(img));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(payment);
			}
		});
		btnPayment.setForeground(Color.WHITE);
		btnPayment.setBackground(new Color(0, 153, 153));
		btnPayment.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnPayment.setBounds(0, 506, 259, 48);
		sideMenu.add(btnPayment);
	}
	
	/**
	 * create the log-out button
	 * and action when the user choose the button
	 * @param sideMenu
	 * @param btnLogout
	 */
	private void createLogOutBtn(JPanel sideMenu, JButton btnLogout) {
		Image img = new ImageIcon(this.getClass().getResource("/exit (1).png")).getImage();
		btnLogout.setIcon(new ImageIcon(img));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(0, 153, 153));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnLogout.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnLogout.setBounds(0, 601, 259, 48);
		sideMenu.add(btnLogout);
	}
	
	/**
	 * create exit button
	 * @param exitBtn
	 */
	private void createExitBtn(JButton exitBtn) {
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setForeground(new Color(0, 51, 153));
		exitBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setBounds(1266, 0, 51, 23);
		add(exitBtn);
	}
}
