package AdminGUI;

import userGUI.LogInFrame;
//import entitylist.*;
//import entity.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import java.io.*;
import java.util.Arrays;

public class AddNewTrain extends JFrame implements ActionListener
{
	private JLabel label1,label2,label3,label4,label5;
	private JPanel panel;
	private JButton button1,button2,button3;
	Font font1=new Font("cambria",Font.BOLD+Font.ITALIC,35);
	Font font2=new Font("cambria",Font.BOLD,19);
	Font font3=new Font("cambria",Font.BOLD,15);
	private JTable table1;
	private DefaultTableModel model1;
	private JTable table2;
	private DefaultTableModel model2;
	private JScrollPane scroll1,scroll2;
	private JTextField text1,text2;
	public String availableTrains [];
	public String allTrainId [];
	public String storingTrainwithRouteNo [];
	
	private LogInFrame login;
	private UpdateIntercityTrainInfoFrame updateIntercityTrainInfo;
	private AdminHomePageFrame adminhome;
	
	public AddNewTrain(UpdateIntercityTrainInfoFrame updateIntercityTrainInfo,LogInFrame login,AdminHomePageFrame adminhome)
	{
		super("Bangladesh Railway");
		this.updateIntercityTrainInfo=updateIntercityTrainInfo;
		this.login=login;
		this.adminhome=adminhome;
		availableTrains = new String [100000];
		allTrainId = new String [100000];
		storingTrainwithRouteNo = new String [100000];
		initializeForm();
		createTable1();
		createTable2();
		
		this.setVisible(true);
	}
	public void initializeForm()
	{
		
		this.setSize(900,720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setLayout(null);
		
		panel=new JPanel();
		panel.setBounds(0,0,900,720);
		panel.setLayout(null);
		//panel1.setOpaque(false);
		panel.setBackground(new Color(255,255,255));
		
		ImageIcon img=new ImageIcon("./userGUI/Resources/LOGO2.png");
		JLabel logo=new JLabel("",img,JLabel.CENTER);
		logo.setBounds(20,15,60,60);
		
		label1=new JLabel("Add New InterCity Train ");
		label1.setBounds(80,15,500,70);
		label1.setFont(font1);
		
		button1=new JButton("Sing Out");
		button1.setBounds(760,25,100,30);
		button1.setFont(font3);
		button1.addActionListener(this);
		
		label2 = new JLabel("New Train Name: ");
		label2.setBounds(85,100,200,30);
		label2.setFont(font2);
		label2.setForeground(new Color(2,11,33));
		
		label3 = new JLabel("New Train I'd: ");
		label3.setBounds(550,100,200,30);
		label3.setFont(font2);
		label3.setForeground(new Color(2,11,33));
		
		button2=new JButton("Next>>");
		button2.setBounds(367,200,100,30);
		button2.setFont(font3);
		button2.addActionListener(this);
		
		label4 = new JLabel("Existing Train In BD");
		label4.setBounds(550,280,300,35);
		label4.setFont(new Font("cambria",Font.BOLD,25));
		label4.setForeground(new Color(2,11,33));
		
		label5 = new JLabel("Connected Route");
		label5.setBounds(85,280,200,35);
		label5.setFont(new Font("cambria",Font.BOLD,25));
		label5.setVisible(false);
		label5.setForeground(new Color(2,11,33));
		
		text1 = new JTextField();
        text1.setBounds(85,137,200,45);
        text1.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        text1.setForeground(Color.BLACK);
        text1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		text1.setForeground(new Color(232,72,104));
		text1.setHorizontalAlignment(JTextField.CENTER);
		text1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		//text1.setEditable(false);
        text1.setCaretColor(Color.BLACK);
        //text1.setBorder(null);
        //text1.setOpaque(false);
		
		
		text2 = new JTextField();
        text2.setBounds(550,137,200,45);
        text2.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        text2.setForeground(Color.BLACK);
        text2.setForeground(new Color(232,72,104));
		text2.setHorizontalAlignment(JTextField.CENTER);
		text2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		//text2.setEditable(false);
        text2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        text2.setCaretColor(Color.BLACK);
		
		button3=new JButton("Confirm");
		button3.setBounds(407,632,100,30);
		button3.setFont(font3);
		button3.setVisible(false);
		button3.addActionListener(this);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(text1);
		panel.add(text2);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(logo);
		this.add(panel);
		
		
	}
	
	public void createTable1()
	{
		
		model1= new DefaultTableModel();
        table1=new JTable(model1);
       	 scroll1=new JScrollPane(table1);
		scroll1.setBounds(550,320,280,300);
		
		model1.addColumn("Train-ID");
		model1.addColumn("Train-Name");
		
		table1.setFont(new Font("cambria",Font.BOLD,10));
		table1.setBackground(new Color(174,247,255));
		table1.setSelectionBackground(new Color(255,153,51));
		table1.getTableHeader().setFont(font2);
		table1.setRowHeight(30);
		table1.setEnabled(false);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Train_Info");
            while (rs.next()){
				for(int i=0; i<availableTrains.length; i++)
				{
					if(availableTrains[i]==null)
					{
						availableTrains[i]= rs.getString(2);
						break;
					}
				}
				
				for(int i=0; i<allTrainId.length; i++)
				{
					if(allTrainId[i]==null)
					{
						allTrainId[i]= String.valueOf(rs.getInt(1));
						break;
					}
				}
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
	
	public void createTable2()
	{
		
		model2= new DefaultTableModel();
        table2=new JTable(model2);
       	 scroll2=new JScrollPane(table2);
		scroll2.setBounds(85,320,280,300);
		scroll2.setVisible(false);
		
		model2.addColumn("Route No");
		model2.addColumn("Route Information");
		
		table2.setFont(new Font("cambria",Font.BOLD,10));
		table2.setBackground(new Color(174,247,255));
		table2.setSelectionBackground(new Color(255,153,51));
		table2.getTableHeader().setFont(font2);
		table2.setRowHeight(30);
		//table2.setEnabled(false);
		//table2.setEditable(false);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Route_Info");
            while (rs.next()){
				
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
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button2)
	    {
			if(!text1.getText().equals("") && !text2.getText().equals(""))
			{
				boolean flag1 = true;
				boolean flag2 = true;
				for(int i=0; i<availableTrains.length; i++)
				{
					if(availableTrains[i]!=null && text1.getText().equals(availableTrains[i]))
					{
						flag1 = false; 
						break;
                    }
			}
			for(int i=0; i<allTrainId.length; i++)
				{
					if(allTrainId[i]!=null && text2.getText().equals(allTrainId[i]))
					{
						flag2 = false; 
						break;
                    }
			}
			if(flag1 && flag2)
			{
				
			int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm New Train Launch?");
			if(option == JOptionPane.YES_OPTION){
				
					System.out.println(text2.getText()+"-"+text1.getText());
					JOptionPane.showMessageDialog(this,"Successfully Add New Train");
					button1.setVisible(false);
					text1.setEditable(false);
					text2.setEditable(false);
					scroll2.setVisible(true);
					button3.setVisible(true);
					label5.setVisible(true);;
			}
			
			}	
                else if(!flag1 && !flag2)
				{
					JOptionPane.showMessageDialog(this,"Your entering Train and Train Id already exist","Error",JOptionPane.ERROR_MESSAGE);
				}
                				
				else if(!flag1)
				{
					JOptionPane.showMessageDialog(this,"Your entering Train already exist","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!flag2)
				{
					JOptionPane.showMessageDialog(this,"Your entering Train ID already exist","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(text1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Please Enter the New Train Name","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			else if(text2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Please Enter the New Train ID","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource()==button1)
		{
			
		}
		
		else if(e.getSource()==button3)
		{
			if(table2.getSelectedRows().length > 0)
			{
				
			int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm Connecting Stations?");
			if(option == JOptionPane.YES_OPTION){
                
				int selectedRows[] = table2.getSelectedRows(); 
				Arrays.sort(selectedRows);
				
				if(selectedRows!=null){
					for(int i=selectedRows.length-1; i>=0 ;i--){
						String route_no = table2.getModel().
										getValueAt(selectedRows[i], 0).
									toString();
						for(int j=0; j<storingTrainwithRouteNo.length; j++)
				   {
					if(storingTrainwithRouteNo[j]==null)
					{
						storingTrainwithRouteNo[j]=text2.getText()+"-"+route_no;
						System.out.println(storingTrainwithRouteNo[j]);
						break;
					}
					
				   }
				   
			 }
		}
	}
	JOptionPane.showMessageDialog(this,"Successfully Connection Done");
	table2.setEnabled(false);
	button3.setVisible(false);
	addTrainInfo();
	addAdminOperateTrain();
	addTrainRouteInfo();
    UpdateIntercityTrainInfoFrame uitinfo = new UpdateIntercityTrainInfoFrame(adminhome,login);
	this.setVisible(false);
  }
         else
       {
	  JOptionPane.showMessageDialog(this,"Please Select 1st Connected Route","Error",JOptionPane.ERROR_MESSAGE);
       }
	}
}
	public void addTrainInfo()
	 {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					int T_Id = Integer.parseInt(text2.getText());
					String T_Name = text1.getText();
					
            PreparedStatement stmt = con.prepareStatement("insert into Train_Info (T_Id,T_Name) values (?, ?)");
            stmt.setInt(1,T_Id);
            stmt.setString(2,T_Name);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Create Successful!!!");
            }
		con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	 
	 }
	 
	 public void addAdminOperateTrain()
	 {
		 try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
							int T_Id =Integer.parseInt(text2.getText());
							String T_Name =text1.getText();
							String A_Email =login.adminEmail;
            PreparedStatement stmt = con.prepareStatement("insert into Admin_Operate_Train (T_Id,T_Name,A_Email) values (?, ?,?)");
            stmt.setInt(1,T_Id);
            stmt.setString(2,T_Name);
            stmt.setString(3,A_Email);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Create Successful!!!");
            }
			
		con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	 }
	 
	 public void addTrainRouteInfo()
	 {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<storingTrainwithRouteNo.length; i++){
						if(storingTrainwithRouteNo[i]!=null){
							String stp [] = storingTrainwithRouteNo[i].split("-");
							int T_Id =Integer.parseInt(stp[0]);
							int R_Id = Integer.parseInt(stp[1]);
            PreparedStatement stmt = con.prepareStatement("insert into TR_Info (T_Id,R_Id) values (?, ?)");
            stmt.setInt(1,T_Id);
            stmt.setInt(2,R_Id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Create Successful!!!");
            }
		}
		}
		con.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
	 }	
	
	
}