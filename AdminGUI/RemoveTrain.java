package AdminGUI;

import userGUI.LogInFrame;
//import entitylist.*;
//import entity.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import java.io.*;
import java.util.Arrays;

public class RemoveTrain extends JFrame implements ActionListener
{
	private JLabel label1,label2;
	private JPanel panel;
	private JButton button1,button2;
	Font font1=new Font("cambria",Font.BOLD+Font.ITALIC,30);
	Font font2=new Font("cambria",Font.BOLD,19);
	Font font3=new Font("cambria",Font.BOLD,15);
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTextField text1,text2,text3;
	public String tId[];
	
	private LogInFrame login;
	private UpdateIntercityTrainInfoFrame updateIntercityTrainInfo;
	private AdminHomePageFrame adminhome;
	
	public RemoveTrain(UpdateIntercityTrainInfoFrame updateIntercityTrainInfo,LogInFrame login,AdminHomePageFrame adminhome)
	{
		super("Bangladesh Railway");
		this.updateIntercityTrainInfo=updateIntercityTrainInfo;
		this.login=login;
		this.adminhome=adminhome;
		tId = new String[100000];
		initializeForm();
		createTable();
		
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
		
		label1=new JLabel("Remove InterCity Train Information");
		label1.setBounds(80,15,500,70);
		label1.setFont(font1);
		
		button1=new JButton("Sing Out");
		button1.setBounds(760,25,100,30);
		button1.setFont(font3);
		button1.addActionListener(this);
		
		
		label2 = new JLabel("Available All Train In BD");
		label2.setBounds(270,200,400,30);
		label2.setFont(new Font("cambria",Font.BOLD,20));
		//label2.setVisible(false);
		label2.setForeground(new Color(2,11,33));
		
		button2=new JButton("Delete");
		button2.setBounds(400,570,110,30);
		button2.setFont(font3);
		button2.addActionListener(this);
		
		
		panel.add(label1);
		panel.add(label2);
		panel.add(button1);
		panel.add(button2);
		panel.add(logo);
		this.add(panel);
		
		
	}
	
	public void createTable()
	{
		
		model= new DefaultTableModel();
        table=new JTable(model);
       	 scroll=new JScrollPane(table);
		scroll.setBounds(270,250,370,300);
		
		model.addColumn("Train ID");
		model.addColumn("Train Name");
		
		table.setFont(new Font("cambria",Font.BOLD,10));
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(255,153,51));
		table.getTableHeader().setFont(font3);
		table.setRowHeight(30);
		//table.setEnabled(false);
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Train_Info");
            while (rs.next()){
				
				model.addRow(new Object[]{
				rs.getInt(1),
				rs.getString(2)
		    });
			}
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
	    panel.add(scroll);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button1)
	    {
			this.setVisible(false);
			login.setVisible(true);
		}
		
		else if(e.getSource()==button2)
	    {
			if(table.getSelectedRows().length > 0)
			{
			int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm Delete Train?");
			if(option == JOptionPane.YES_OPTION){
			int selectedRows[] = table.getSelectedRows(); 
				Arrays.sort(selectedRows);
				 
				 if(selectedRows!=null){
					for(int i=selectedRows.length-1; i>=0 ;i--){
						String trainID = table.getModel().
										getValueAt(selectedRows[i], 0).
									toString();
						for(int j=0; j<tId.length; j++)
				   {
					if(tId[j]==null)
					{
						tId[j]=trainID;
						//System.out.println(tId[j]);
						break;
					}
					
				   }
				   
					}
				 }
				
				deleteAdminOperateTrainInfo();
				deleteTRInfo();
				deleteTrainInfo();
				JOptionPane.showMessageDialog(this,"Delete Successfull");
				this.setVisible(false);
				UpdateIntercityTrainInfoFrame uitif = new UpdateIntercityTrainInfoFrame(adminhome,login);
			}
		}
		
		else
		{
			JOptionPane.showMessageDialog(this,"Please Selected Any Rows of The Above Table","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
	
	public void deleteTrainInfo()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<tId.length; i++){
						if(tId[i]!=null){
					int T_Id = Integer.parseInt(tId[i]);
            PreparedStatement stmt = con.prepareStatement("delete from Train_Info where T_Id=?");
            stmt.setInt(1, T_Id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
            }
		}
        }
         con.close();		
		}catch (Exception e) {
            System.out.println(e);
        }
		
	}
	
	public void deleteTRInfo()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<tId.length; i++){
						if(tId[i]!=null){
					int T_Id = Integer.parseInt(tId[i]);
            PreparedStatement stmt = con.prepareStatement("delete from TR_Info where T_Id=?");
            stmt.setInt(1, T_Id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
            }
		}
        }
         con.close();		
		}catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void deleteAdminOperateTrainInfo()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					for(int i=0; i<tId.length; i++){
						if(tId[i]!=null){
					int T_Id = Integer.parseInt(tId[i]);
            PreparedStatement stmt = con.prepareStatement("delete from Admin_Operate_Train where T_Id=?");
            stmt.setInt(1, T_Id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Delete Successful!!!");
            }
		}
        }
         con.close();		
		}catch (Exception e) {
            System.out.println(e);
        }
	}
	
	/*public static void main(String [] args)
	{
		new RemoveTrain();
	}*/
	
}