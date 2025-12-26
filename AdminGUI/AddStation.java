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
import java.util.Scanner;
import java.util.Arrays;

public class AddStation extends JFrame implements ActionListener
{
	private JLabel label1,label2,label3,label4,
	label5,label6,label7,label8,label9,label10,label11;
	private JPanel panel;
	private JButton button1,button2,button3,
	button4,button5,button6,button7,button8,
	button9,button10,button11,button12;
	private JTextField text1,text2,text3;
	
	private JTable table1,table2,table3;
	private DefaultTableModel model1,model2,model3;
	private JScrollPane scroll1,scroll2,scroll3;
	Font font1=new Font("cambria",Font.BOLD+Font.ITALIC,25);
	Font font2=new Font("cambria",Font.BOLD,20);
	
	private UpdateIntercityTrainInfoFrame updateinter;
	private LogInFrame login;
	private AdminHomePageFrame adminhome;
	
	int table1FirstSelectedRow=0;
	int select1 = 0;
	int select2 = 0;
	int select3 = 0;
	int select5 = 0;
	String newStation="";
	String fromStation ="";
	String toStation = "";
	String fromStation1 ="";
	String toStation1 = "";
	static int num1=0;
	static int num2=0;
	static String storelastRouteId="";
	static String storelastRouteIdforIndentify="";
	static String storeTicketforIndentify="";
	static int count = 1;
	static int count1 = 1;
	static int count2 = 1;
	static int count3 = 1;
	static int count4 = 1;
	static int countdown = 0;
	
	String storingStation [];
	String storingConnectingStation [];
	String storingConnectingStationwithRouteid [];//1
	String storingTrainidwithRouteid [];//2
	String storingTrainTicketPricewithRouteNo [];//3
	
	public AddStation(UpdateIntercityTrainInfoFrame updateinter,LogInFrame login,AdminHomePageFrame adminhome)
	{
		super("Bangladesh Railway");
		this.updateinter=updateinter;
		this.login=login;
		this.adminhome=adminhome;
		storingStation = new String [100000];
		storingConnectingStation = new String [100000];
		storingConnectingStationwithRouteid = new String [100000];
		storingTrainidwithRouteid = new String [100000];
		storingTrainTicketPricewithRouteNo = new String [100000];
		initializeForm();
		storeStation();
		createTable1();
		
		this.setVisible(true);
	}
	public void initializeForm()
	{
		
		this.setSize(900,720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		panel=new JPanel();
		panel.setBounds(0,0,900,720);
		panel.setLayout(null);
		//panel.setOpaque(false);
		panel.setBackground(new Color(255,255,255));
		
		ImageIcon img=new ImageIcon("./userGUI/Resources/LOGO2.png");
		JLabel logo=new JLabel("",img,JLabel.CENTER);
		logo.setBounds(20,15,60,60);
		
		label1=new JLabel("InterCity New Station Setup");
		label1.setBounds(80,15,500,50);
		label1.setFont(font1);
		
		button11=new JButton("<Back");
		button11.setBounds(640,25,80,30);
		button11.setFont(new Font("cambria",Font.BOLD,15));
		button11.addActionListener(this);
		
		button1=new JButton("Sing Out");
		button1.setBounds(750,25,100,30);
		button1.setFont(new Font("cambria",Font.BOLD,15));
		button1.addActionListener(this);
		
		label2=new JLabel("Add New Station");
		label2.setBounds(60,100,150,20);
		label2.setFont(font2);
		
		text1 = new JTextField();
		text1.setBounds(60,135,210,35);
		text1.setFont(font2);
		text1.setForeground(new Color(29,61,87));
		text1.setHorizontalAlignment(JTextField.CENTER);
		text1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		label3=new JLabel("Add Connecting Station");
		label3.setBounds(500,100,300,23);
		label3.setFont(font2);
		
		button12=new JButton("Confirm");
		button12.setBounds(640,285,100,30);
		button12.setFont(new Font("cambria",Font.BOLD,15));
		button12.addActionListener(this);
		
		label4=new JLabel("Add Route-NO: ");
		label4.setBounds(60,190,250,23);
		label4.setFont(font2);
		
		label5=new JLabel();
		label5.setBounds(60,220,250,28);
		label5.setFont(font2);
		
		text2 = new JTextField();
		text2.setBounds(60,260,210,35);
		text2.setFont(font2);
		text2.setForeground(new Color(232,72,104));
		text2.setHorizontalAlignment(JTextField.CENTER);
		text2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		button2=new JButton("update");
		button2.setBounds(180,310,90,33);
		button2.setFont(new Font("cambria",Font.BOLD,15));
		button2.addActionListener(this);	
		
		
		
		label6=new JLabel("Connected Train");
		label6.setBounds(60,380,250,23);
		label6.setFont(font2);
		
		
		label7=new JLabel();
		label7.setBounds(60,414,250,28);
		label7.setFont(font2);
		
		button4=new JButton("update");
		button4.setBounds(300,615,90,33);
		button4.setFont(new Font("cambria",Font.BOLD,15));
		button4.addActionListener(this);
		
		text3 = new JTextField();
		text3.setBounds(600,475,210,35);
		text3.setFont(font2);
		text3.setForeground(new Color(29,61,87));
		text3.setHorizontalAlignment(JTextField.CENTER);
		text3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		text3.setVisible(false);;
		
		button6=new JButton("next");
		button6.setBounds(630,535,65,33);
		button6.setFont(new Font("cambria",Font.BOLD,15));
		button6.setVisible(false);
		button6.addActionListener(this);
		
		button7=new JButton("update");
		button7.setBounds(720,535,90,33);
		button7.setFont(new Font("cambria",Font.BOLD,15));
		button7.setVisible(false);
		button7.addActionListener(this);
		
		label8=new JLabel("Ticket Price Setup");
		label8.setBounds(520,375,200,32);
		label8.setFont(font2);
		label8.setVisible(false);
		
		label9=new JLabel("");
		label9.setBounds(520,420,240,28);
		label9.setFont(font2);
		
		label10=new JLabel("price(TK):");
		label10.setBounds(490,475,170,30);
		label10.setFont(new Font("cambria",Font.BOLD,20));
		label10.setVisible(false);
		
		
		panel.add(logo);
		panel.add(label1);
		panel.add(button1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(label7);
		panel.add(label8);
		panel.add(label9);
		panel.add(label10);
		panel.add(button2);
		panel.add(button4);
		panel.add(button6);
		panel.add(button7);
		panel.add(button11);
		panel.add(button12);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		this.add(panel);
		
	}
	public void storeStation()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select R_Info from Route_Info");
            while (rs.next()){
				boolean flag1 = true;
				String station [] = rs.getString(1).split("-");
				//System.out.println(station[0]);
				for(int i=0; i<storingStation.length; i++)
				{
					if(storingStation[i]!=null && station[0].equals(storingStation[i]))
					{
						flag1 = false;
						break;
					}
				}
				if(flag1)
				{
					for(int i=0; i<storingStation.length; i++)
				{
					if(storingStation[i]==null)
					{
						storingStation[i]=station[0];
						System.out.println(storingStation[i]);
						break;
					}
				}
				}
						
			}
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void storeStationwithRouteId(String stationwithid)
	{
		for(int i=0; i<storingConnectingStationwithRouteid.length; i++)
		{
			if(storingConnectingStationwithRouteid[i]==null)
			{
				storingConnectingStationwithRouteid[i]=stationwithid;
				System.out.println(storingConnectingStationwithRouteid[i]);
				break;
			}
		}
	}
	
	public void createTable1()
	{
		model1= new DefaultTableModel();
        table1=new JTable(model1);
       	scroll1=new JScrollPane(table1);
		scroll1.setBounds(500,132,250,140);
		
		model1.addColumn("Available Station");
		
		table1.setFont(new Font("cambria",Font.BOLD,18));
		table1.setBackground(new Color(174,247,255));
		table1.setSelectionBackground(new Color(65,131,163));
		table1.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,23));
		table1.setRowHeight(25);
		//table1.addActionListener(this);
		//table1.setEnabled(false);
		//table1.setHorizontalAlignment(new GridBagConstraints());
		
		for(int i=0; i<storingStation.length; i++)
		{
			if(storingStation[i]!=null)
			{
		   model1.addRow(new Object[]{
				storingStation[i]
		    });
		
		}
		}
	    panel.add(scroll1);
		System.out.println("\n\n\n");
	}
	public void createTable2()
	{
		model2= new DefaultTableModel();
        table2=new JTable(model2);
       	scroll2=new JScrollPane(table2);
		scroll2.setBounds(60,453,350,145);
		
		model2.addColumn("Train_ID");
		model2.addColumn("Available Train In BD");
		
		table2.setFont(new Font("cambria",Font.BOLD,18));
		table2.setBackground(new Color(174,247,255));
		table2.setSelectionBackground(new Color(65,131,163));
		table2.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,19));
		table2.setRowHeight(25);
		//table2.setEnabled(false);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Train_Info");
            while (rs.next()){
				
				model2.addRow(new Object[]{
				rs.getInt(1),
				rs.getString(2)
		    });
			}
            con.close();

        } catch (Exception epx) {
            System.out.println(epx);
        }
		panel.add(scroll2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource()==button1)
		{
			
		}
		
		else if(e.getSource()==button11)
		{
			
		}
		
		else if(e.getSource()==button12)
		{
			if(!text1.getText().equals("") && table1.getSelectedRows().length > 0 && select1<=0)
			{
				boolean flag = true;
				for(int j=0; j<storingStation.length; j++)
				{
					if(storingStation[j]!=null && text1.getText().equals(storingStation[j]))
					{
						flag = false; 
						break;
                    }
			}
			if(flag)
			{
				
			int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm Connecting Stations?");
			if(option == JOptionPane.YES_OPTION){
				select1++;
                
				int selectedRows[] = table1.getSelectedRows(); 
				Arrays.sort(selectedRows);
                 table1FirstSelectedRow =selectedRows[0];
				if(selectedRows!=null){
					for(int i=selectedRows.length-1; i>=0 ;i--){
						String station = table1.getModel().
										getValueAt(selectedRows[i], 0).
									toString();
								num1++;
						for(int j=0; j<storingConnectingStation.length; j++)
				   {
					if(storingConnectingStation[j]==null)
					{
						storingConnectingStation[j]=text1.getText()+"-"+station+"-"+num1;
						System.out.println(storingConnectingStation[j]);
						break;
					}
					
				   }
				   
			 }
					
					JOptionPane.showMessageDialog(this,"Successfully Added Connecting Stations");
					table1.setEnabled(false);
					button12.setEnabled(false);
					text1.setEditable(false);
				for(int i=0; i<storingConnectingStation.length; i++)
				{
                   if(storingConnectingStation[i]!=null)
				   {
					   String sp[]=storingConnectingStation[i].split("-");
					    if(Integer.parseInt(sp[2])==count)
				     {
					   count++;
					   label5.setText(sp[0]+"-"+sp[1]);
					   break;
					
				    }
					   
				   }
                }
			}
			
			}

			}			
				else
				{
					JOptionPane.showMessageDialog(this,"Your entering Station already exist","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		else if(select1>0)
		{
			JOptionPane.showMessageDialog(this,"You Already Selected Connecting Stations","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(!(table1.getSelectedRows().length>=0))
		{
			JOptionPane.showMessageDialog(this,"You must have to Select Connecting Stations","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(text1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"You must have to Add New Stations","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		}
		
		
		
		else if(e.getSource()==button2)
		{
			if(!text1.getText().equals("") && !text2.getText().equals("") && select1>0)
			{
				
                for(int i=0; i<storingConnectingStation.length; i++)
				{
                   if(storingConnectingStation[i]!=null)
				   {
					   String sp[]=storingConnectingStation[i].split("-");
					   toStation =sp[1];
					    if(Integer.parseInt(sp[2])==count)
				     {
						 String tempstr1 = text2.getText()+"-"+label5.getText()+"-"+count3;
						 count3++;
						 storeStationwithRouteId(tempstr1);
						 String spl[] = label5.getText().split("-");
						 String tempstr2 = String.valueOf(Integer.parseInt(text2.getText())+1)+"-"+spl[1]+"-"+spl[0]+"-"+count3;
						 count3++;
						 storeStationwithRouteId(tempstr2);
					     count++;
					     label5.setText(sp[0]+"-"+sp[1]);
					     break;
					
				    }
					   
				   }
			}
			if(table1.getModel().getValueAt(table1FirstSelectedRow,0).toString().equals(toStation))
			{
				if(countdown==0)
				{
					countdown++;
					for(int i=0; i<storingConnectingStation.length; i++)
				{
                   if(storingConnectingStation[i]!=null)
				   {
					   String text =text2.getText();
					   int tempnum =Integer.parseInt(text)+1340;
					   String tempid = String.valueOf(tempnum);
						 String tempstr1 = tempid+"-"+label5.getText()+"-"+count3;
						 count3++;
						 storeStationwithRouteId(tempstr1);
						 String spl[] = label5.getText().split("-");
						 storelastRouteId = String.valueOf(Integer.parseInt(tempid)+1);
						 String tempstr2 = storelastRouteId +"-"+spl[1]+"-"+spl[0]+"-"+count3;
						 count3++;
						 storeStationwithRouteId(tempstr2);
						 text2.setText("");
						 label5.setText("");
						 text2.setEditable(false);
				         button2.setEnabled(false);
						 //count=1;
					     break;
					
				    }
					   
				   }
			}
			JOptionPane.showMessageDialog(this,"Successfully Added Connecting Routes");
				for(int i=0; i<storingConnectingStationwithRouteid.length; i++)
				{
                   if(storingConnectingStationwithRouteid[i]!=null)
				   {
					   String sp[]=storingConnectingStationwithRouteid[i].split("-");
					    if(Integer.parseInt(sp[3])==count2)
				     {
					   count2++;
					   label7.setText(sp[0]+": "+sp[1]+"-"+sp[2]);
					   break;
					
				    }
					   
				   }
                }
			createTable2();
			
		}
				
			}
			
		else if(select1==0)
		{
			JOptionPane.showMessageDialog(this,"You must have to Select Connecting Stations First","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(text2.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"You must have to Enter Route No","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(text1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"You must have to Add New Stations","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
		
		
		
		else if(e.getSource()==button4)
		{
			if(!text1.getText().equals("") && table2.getSelectedRows().length > 0 && select1>0)
			{
				
			int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm Connecting Stations?");
			if(option == JOptionPane.YES_OPTION){
                
				int selectedRows[] = table2.getSelectedRows(); 
				Arrays.sort(selectedRows);
				if(selectedRows!=null){
					for(int i=selectedRows.length-1; i>=0 ;i--){
						String trainId = table2.getModel().
										getValueAt(selectedRows[i], 0).
									toString();
						for(int j=0; j<storingTrainidwithRouteid.length; j++)
				   {
					if(storingTrainidwithRouteid[j]==null)
					{
						String str[] =label7.getText().split(":");
						storingTrainidwithRouteid[j]=str[0]+"-"+trainId;
						System.out.println(storingTrainidwithRouteid[j]);
						break;
					}
					
				   }
				   
			 }
		}
		
         if(storelastRouteId.equals(storelastRouteIdforIndentify))
		 {
			 label9.setVisible(true);
			 label10.setVisible(true);
			 label8.setVisible(true);
			 text3.setVisible(true);
			 text3.setFocusable(true);
			 button6.setVisible(true);
			 button7.setVisible(true);
			 button4.setVisible(false);
			 table2.setEnabled(false);
			 
             for(int i=0; i<storingConnectingStationwithRouteid.length; i++)
				{
                   if(storingConnectingStationwithRouteid[i]!=null)
				   {
					   String sp[]=storingConnectingStationwithRouteid[i].split("-");
					    if(Integer.parseInt(sp[3])==count4)
				     {
					   count4++;
					   label9.setText(sp[0]+": "+sp[1]+"-"+sp[2]);
					   break;
					
				    }
					   
				   }
                }
		 }			 
         JOptionPane.showMessageDialog(this,"Successfully Added Connecting Connecting Stations");
				for(int i=0; i<storingConnectingStationwithRouteid.length; i++)
				{
                   if(storingConnectingStationwithRouteid[i]!=null)
				   {
					   String sp[]=storingConnectingStationwithRouteid[i].split("-");
					   storelastRouteIdforIndentify=sp[0];
					    if(Integer.parseInt(sp[3])==count2)
				     {
					   count2++;
					   label7.setText(sp[0]+": "+sp[1]+"-"+sp[2]);
					   break;
					
				    }
					   
				   }
                }
			}
			
			}
		
		else if(select1==0)
		{
			JOptionPane.showMessageDialog(this,"You must have to Select Connecting Stations First","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(!(table2.getSelectedRows().length>=0))
		{
			JOptionPane.showMessageDialog(this,"You must have to Select Station's Train","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(text1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"You must have to Add New Stations","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	
		}
		
		else if(e.getSource()==button6)
		{
			for(int i=0; i<storingConnectingStationwithRouteid.length; i++)
				{
                   if(storingConnectingStationwithRouteid[i]!=null)
				   {
					   String sp[]=storingConnectingStationwithRouteid[i].split("-");
					    if(Integer.parseInt(sp[3])==count4)
				     {
					   count4++;
					   label9.setText(sp[0]+": "+sp[1]+"-"+sp[2]);
					   break;
					
				    }
					   
				   }
                }
			select5 = 0;
			if(storelastRouteId.equals(storeTicketforIndentify))
				   {
					   JOptionPane.showMessageDialog(this,"Station Adding Process Successfully Done","Successfull",JOptionPane.INFORMATION_MESSAGE);
					   addrouteInfo();
					   addTrainRouteInfo();
					   addTicketPriceSetup();
					   UpdateIntercityTrainInfoFrame upiti = new UpdateIntercityTrainInfoFrame(adminhome,login);
					   this.setVisible(false);
					   
				   }
		}
		
		
		
		else if(e.getSource()==button7)
		{
			if(select1>0 && !text3.getText().equals("") && select5==0)
			{
				select5++;
				
                 for(int i=0; i<storingTrainTicketPricewithRouteNo.length; i++)
				{
                   if(storingTrainTicketPricewithRouteNo[i]==null)
				   {
					   String stp[] = label9.getText().split(":");
					   storeTicketforIndentify = stp[0];
					 storingTrainTicketPricewithRouteNo[i]=stp[0]+"-"+text3.getText()+"-"+login.adminEmail;
					 System.out.println("\n"+storingTrainTicketPricewithRouteNo[i]);
					 break;
					
				    }
					   
				   }
			}
			else if(select5!=0)
			{
				JOptionPane.showMessageDialog(this,"You must have to NextButton for setting ticket price","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		}
     public void addrouteInfo()
	 {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<storingConnectingStationwithRouteid.length; i++){
						if(storingConnectingStationwithRouteid[i]!=null){
							String stp [] = storingConnectingStationwithRouteid[i].split("-");
							int R_Id =Integer.parseInt(stp[0]);
							String R_Info =stp[1]+"-"+stp[2];
            PreparedStatement stmt = con.prepareStatement("insert into Route_Info (R_Id,R_Info) values (?, ?)");
            stmt.setInt(1,R_Id);
            stmt.setString(2,R_Info);
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
	 
	 public void addTrainRouteInfo()
	 {
		 try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<storingTrainidwithRouteid.length; i++){
						if(storingTrainidwithRouteid[i]!=null){
							String stp [] = storingTrainidwithRouteid[i].split("-");
							int T_Id =Integer.parseInt(stp[1]);
							int R_Id =Integer.parseInt(stp[0]);
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
	 
	 public void addTicketPriceSetup()
	 {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<storingTrainTicketPricewithRouteNo.length; i++){
						if(storingTrainTicketPricewithRouteNo[i]!=null){
							String stp [] = storingTrainTicketPricewithRouteNo[i].split("-");
							int R_No =Integer.parseInt(stp[0]);
							float Price = Float.parseFloat(stp[1]);
							String A_Email =stp[2];
            PreparedStatement stmt = con.prepareStatement("insert into Admin_Manage_Ticket (R_No,Price,A_Email) values (?, ?,?)");
            stmt.setInt(1,R_No);
            stmt.setFloat(2,Price);
            stmt.setString(3,A_Email);
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