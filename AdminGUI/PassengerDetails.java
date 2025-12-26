package AdminGUI;

import userGUI.LogInFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class PassengerDetails extends JFrame implements ActionListener 
{
	private JTable table1;
	private JTable table2;
	private DefaultTableModel model1,model2;
	private JScrollPane scroll1,scroll2;
	JButton button1,button2,button3;
	JPanel panel;
	private AdminHomePageFrame adminhome;
	private LogInFrame login;
	
	public PassengerDetails(AdminHomePageFrame adminhome,LogInFrame login)
	{
		super("Bangladesh Railway");
		this.adminhome=adminhome;
		this.login=login;
		this.setSize(1000,600);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false	);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel =new  JPanel();
		panel.setSize(1000,600);
		//panel.setBackground(1000,600);
		panel.setLayout(null);
		
		
		createTable1();
        this.add(panel);
        this.setVisible(true);
		
		JLabel lable1 = new JLabel("Select the Passenger");
		lable1.setBounds(200,50,300, 35);
		lable1.setFont(new Font("cambria",Font.BOLD,30));
		panel.add(lable1);
		
		JLabel lable2 = new JLabel("Passenger info who has more balance than the selected passenger");
		lable2.setBounds(50,307,800,35);
		lable2.setFont(new Font("cambria",Font.BOLD,25));
		panel.add(lable2);
		
		button1 = new JButton("Check");
		button1.setBounds(600,30,120,50);
		button1.setFont(new Font("cambria",Font.BOLD,25));
		button1.setBackground(new Color(0,255,51));
		button1.setFocusable(false);
		button1.addActionListener(this);
		panel.add(button1);
		
		button2 = new JButton("Back");
		button2.setBounds(20,30,120,50);
		button2.setFont(new Font("cambria",Font.BOLD,25));
		button2.setBackground(new Color(174,247,255));
		button2.setFocusable(false);
		button2.addActionListener(this);
		panel.add(button2);
		
		button3 = new JButton("Sign-Out");
		button3.setBounds(800,30,120,50);
		button3.setFont(new Font("cambria",Font.BOLD,20));
		button3.setBackground(new Color(174,247,255));
		button3.setFocusable(false);
		button3.addActionListener(this);
		panel.add(button3);
		
	}
	
	public void createTable1()
	{
		model1= new DefaultTableModel();
        table1=new JTable(model1);
       	scroll1=new JScrollPane(table1);
		scroll1.setBounds(50,100,900,200);
		
		model1.addColumn("EMAIL");
		model1.addColumn("NAME");
		model1.addColumn("NID");
		model1.addColumn("PASSWORD");
		model1.addColumn("SQ_QUESTION");
		model1.addColumn("BALANCE");
		model1.addColumn("PHONE");
		
		table1.setFont(new Font("cambria",Font.BOLD,14));
		table1.setBackground(new Color(174,247,255));
		table1.setSelectionBackground(new Color(65,131,163));
		table1.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,18));
		table1.setRowHeight(25);
		table1.getColumnModel().getColumn(0).setPreferredWidth(140); 
        table1.getColumnModel().getColumn(1).setPreferredWidth(120);
        table1.getColumnModel().getColumn(2).setPreferredWidth(80);
        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
        table1.getColumnModel().getColumn(4).setPreferredWidth(120);
        table1.getColumnModel().getColumn(5).setPreferredWidth(80);
        table1.getColumnModel().getColumn(6).setPreferredWidth(80);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BANGLADESHRAILWAY", "BANGLADESHRAILWAY");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from passenger_details");
            while (rs.next()){
				model1.addRow(new Object[]{
					rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getFloat(6),
					rs.getInt(7),
				});
			}
            con.close();
        } 
		catch (Exception e) {
            System.out.println(e);
        }
	    panel.add(scroll1);
	}
	
	
	public void createTable2(String selectedRoute1)
	{
		model2= new DefaultTableModel();
        table2=new JTable(model2);
       	scroll2=new JScrollPane(table2);
		scroll2.setBounds(250,350,450,200);
		
		model2.addColumn("Passenger Name");
		model2.addColumn("Passenger NID");
		
		table2.setFont(new Font("cambria",Font.BOLD,18));
		table2.setBackground(new Color(174,247,255));
		table2.setSelectionBackground(new Color(65,131,163));
		table2.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,23));
		table2.setRowHeight(25);
		table2.getColumnModel().getColumn(0).setPreferredWidth(200); 
        table2.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		String query = "select  P_NAME,P_NID from Passenger_Details where balance > (select balance from Passenger_Details where P_name = ?)";

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe", "BANGLADESHRAILWAY", "BANGLADESHRAILWAY");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, selectedRoute1);

    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
        model2.addRow(new Object[]{
            rs.getString(1),
            rs.getInt(2)
        });
    }
    con.close();
} catch (Exception e) {
    System.out.println(e);
}

	    panel.add(scroll2);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button1)
		{
			String selectedRow = table1.getModel().getValueAt(table1.getSelectedRow(),1).toString();
			System.out.println(selectedRow);
			createTable2(selectedRow);
		}
		
		else if(e.getSource()==button3)
		{
			this.setVisible(false);
			login.setVisible(true);
		}
		
		else if(e.getSource()==button2)
		{
			this.setVisible(false);
			adminhome.setVisible(true);
		}
	}
	
}