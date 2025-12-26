package AdminGUI;

import userGUI.LogInFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class traininfo extends JFrame implements ActionListener 
{
       
	   private JTable table1;
	   private JTable table2;
	   private JTable table3;
	   private DefaultTableModel model1,model2,model3;
	   private JScrollPane scroll1,scroll2,scroll3;
	   JButton button1,button2,button3;
	   JPanel panel;
	   
	   private LogInFrame login;
	private UpdateIntercityTrainInfoFrame updateIntercityTrainInfo;
	private AdminHomePageFrame adminhome;
	
	public	traininfo(UpdateIntercityTrainInfoFrame updateIntercityTrainInfo,LogInFrame login,AdminHomePageFrame adminhome) {
	
		super("Bangladesh Railway");
		this.updateIntercityTrainInfo=updateIntercityTrainInfo;
		this.login=login;
		this.adminhome=adminhome;
		
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
		
		JLabel lable1 = new JLabel("Select Train Route");
		lable1.setBounds(80,100,300, 30);
		lable1.setFont(new Font("cambria",Font.BOLD,30));
		panel.add(lable1);
		
		JLabel lable2 = new JLabel("Train Info of Selected Route");
		lable2.setBounds(500,15,500, 30);
		lable2.setFont(new Font("cambria",Font.BOLD,30));
		panel.add(lable2);
		
		JLabel lable3 = new JLabel("Train Info of Selected Route (Less Price)");
		lable3.setBounds(500,280,500, 30);
		lable3.setFont(new Font("cambria",Font.BOLD,25));
		panel.add(lable3);
		
		button1 = new JButton("Check");
		button1.setBounds(200,400,120,50);
		button1.setFont(new Font("cambria",Font.BOLD,25));
		button1.setBackground(new Color(0,255,51));
		button1.setFocusable(false);
		button1.addActionListener(this);
		panel.add(button1);
		
		button2 = new JButton("Back");
		button2.setBounds(100,50,120,50);
		button2.setFont(new Font("cambria",Font.BOLD,25));
		button2.setBackground(new Color(174,247,255));
		button2.setFocusable(false);
		button2.addActionListener(this);
		panel.add(button2);
		
		button3 = new JButton("Sign-Out");
		button3.setBounds(300,50,120,50);
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
		scroll1.setBounds(80,150,350,200);
		
		model1.addColumn("Route ID");
		model1.addColumn("Route Information");
		
		table1.setFont(new Font("cambria",Font.BOLD,18));
		table1.setBackground(new Color(174,247,255));
		table1.setSelectionBackground(new Color(65,131,163));
		table1.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,23));
		table1.setRowHeight(25);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100); 
        table1.getColumnModel().getColumn(1).setPreferredWidth(250);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BANGLADESHRAILWAY", "BANGLADESHRAILWAY");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Route_Info");
            while (rs.next()){
				model1.addRow(new Object[]{
					rs.getInt(1),
					rs.getString(2)
				});
			}
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
	    panel.add(scroll1);
	}
	public void createTable2(String selectedRoute1,String selectedRoute2)
	{
		model2= new DefaultTableModel();
        table2=new JTable(model2);
       	scroll2=new JScrollPane(table2);
		scroll2.setBounds(500,65,450,200);
		
		model2.addColumn("Train ID");
		model2.addColumn("Train Name");
		
		table2.setFont(new Font("cambria",Font.BOLD,18));
		table2.setBackground(new Color(174,247,255));
		table2.setSelectionBackground(new Color(65,131,163));
		table2.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,23));
		table2.setRowHeight(25);
		table2.getColumnModel().getColumn(0).setPreferredWidth(150); 
        table2.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		String query = "SELECT * FROM Train_Info WHERE T_ID IN (SELECT T_ID FROM TR_Info WHERE R_ID = (SELECT R_ID FROM Route_Info WHERE R_Info = ?))";

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe", "BANGLADESHRAILWAY", "BANGLADESHRAILWAY");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, selectedRoute1 + "-" + selectedRoute2);

    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
        model2.addRow(new Object[]{
            rs.getInt(1),
            rs.getString(2)
        });
    }
    con.close();
} catch (Exception e) {
    System.out.println(e);
}

	    panel.add(scroll2);
	}
	
	public void createTable3(String selectedRoute1,String selectedRoute2)
	{
		model3= new DefaultTableModel();
        table3=new JTable(model3);
       	scroll3=new JScrollPane(table3);
		scroll3.setBounds(500,330,450,200);
		
		model3.addColumn("Train ID");
		model3.addColumn("Train Information");
		
		table3.setFont(new Font("cambria",Font.BOLD,18));
		table3.setBackground(new Color(174,247,255));
		table3.setSelectionBackground(new Color(65,131,163));
		table3.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,23));
		table3.setRowHeight(25);
		table3.getColumnModel().getColumn(0).setPreferredWidth(150); 
        table3.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		String query = "select * from Train_Info where T_ID in(select T_ID from TR_Info where R_ID in (select R_NO from Admin_Manage_Ticket where Price<(select price from Admin_Manage_Ticket where R_NO=(select R_ID from Route_Info where R_Info = ?))))";

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe", "BANGLADESHRAILWAY", "BANGLADESHRAILWAY");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, selectedRoute1 + "-" + selectedRoute2);

    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
        model3.addRow(new Object[]{
            rs.getInt(1),
            rs.getString(2)
        });
    }
    con.close();
} catch (Exception e) {
    System.out.println(e);
}

	    panel.add(scroll3);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button1)
		{
			String selectedRow = table1.getModel().getValueAt(table1.getSelectedRow(),1).toString();
			System.out.println(selectedRow);
			String sp[] = selectedRow.split("-");
			System.out.println(sp[0]);
			System.out.println(sp[1]);
			createTable2(sp[0],sp[1]);
			createTable3(sp[0],sp[1]);
		}
		
		else if(e.getSource()==button3)
		{
			this.setVisible(false);
			login.setVisible(true);
		}
		
		else if(e.getSource()==button2)
		{
			this.setVisible(false);
			updateIntercityTrainInfo.setVisible(true);
		}
	}

}
