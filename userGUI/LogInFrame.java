package userGUI;

import AdminGUI.AdminHomePageFrame;
//import files.FileIO;
//import entitylist.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.*;
import java.util.Scanner;



public class LogInFrame extends JFrame implements ActionListener
{
	private JPanel panel1,panel2;
	private JButton button1,button2,button3;
	private JTextField text;
	private JPasswordField password;
	private JLabel label1,label2,label3;
	Font font1=new Font("cambria",Font.BOLD+Font.ITALIC,25);
	Font font2=new Font("cambria",Font.BOLD,15);
    public static String emailId="";
	public static String name="";
	public static String phone="";
	public static String balance="";
	private String pass="";
	private Cursor cursor=new Cursor(Cursor.HAND_CURSOR);
	
	//private static String userphone="",pass="";
	public static String adminEmail="";
	
	public LogInFrame(int a)
	{
		
	}
	public LogInFrame()
	{
		super(" Bangladesh Railway e-Ticket System ");
		intializeForm();
		
		 this.setVisible(true);
	}
	
	public void intializeForm()
	{
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		/*ImageIcon imag=new ImageIcon("");
		Image img=imag.getImage();
		this.setIconImage(img);*/
		
		
		panel1=new JPanel();
		panel1.setBounds(0,0,800,600);
	    panel1.setLayout(null);
		panel1.setOpaque(false);
		panel1.setBackground(new Color(255,255,255));
		
		panel2=new JPanel();
		panel2.setBounds(40,250,200,250);
	    panel2.setLayout(null);
		panel2.setBackground(new Color(170,179,176));
		
		ImageIcon img=new ImageIcon("./userGUI/Resources/LOGO2.png");
		JLabel logo=new JLabel("",img,JLabel.CENTER);
		logo.setBounds(20,50,60,60);
		
		label1=new JLabel(" Bangladesh Railway e-Ticket System ");
		label1.setBounds(80,30,450,100);
		label1.setFont(font1);
		
		label2=new JLabel("E-mail");
		label2.setBounds(10,10,60,30);
		label2.setFont(font2);
		
		label3=new JLabel("Password");
		label3.setBounds(10,85,100,30);
		label3.setFont(font2);
		
		text=new JTextField();
		text.setBounds(10,45,150,30);
		text.setFont(font2);
		text.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					password.requestFocus();
					
				}
				
			}
			
		});
		
		password=new JPasswordField();
		password.setBounds(10,120,150,30);
		password.setEchoChar('*');
		password.setFont(font2);
		
		button1=new JButton("Sign In");
		button1.setBounds(10,160,90,30);
		button1.setFont(font2);
		button1.setBackground(new Color(137,201,237));
		button1.addActionListener(this);
		button1.setCursor(cursor);
		
		button2=new JButton("Sign Up");
		button2.setBounds(105,160,90,30);
		button2.setFont(font2);
		button2.setBackground(new Color(137,201,237));
		button2.addActionListener(this);
		button2.setCursor(cursor);
		
		button3=new JButton("Forget Password?");
		button3.setBounds(20,200,160,30);
		button3.setFont(font2);
		button3.setBackground(new Color(137,201,237));	
		button3.addActionListener(this);
		button3.setCursor(cursor);
		
		ImageIcon image=new ImageIcon("./userGUI/Resources/LogIn.png");
		JLabel background=new JLabel("",image,JLabel.CENTER);
		background.setBounds(260,200,540,400);
		
		
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(text);
		panel2.add(password);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel1.add(panel2);
		panel1.add(label1);
		panel1.add(logo);
		panel1.add(background);
		this.add(panel1);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{

		if(button1==e.getSource())
		{
			boolean flag1= false;
			boolean flag2= false;
			try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Admin_Info");
            while (rs.next()){
                if(rs.getString(1).equals(text.getText()) && rs.getString(2).equals(new String(password.getPassword())))
				{
					flag1 = true;
					adminEmail=rs.getString(1);
					break;
				}
            }
			 con.close();
			}
		catch (Exception ex)
		{
           System.out.println(ex);
        }
			
		if(!flag1)
		{
            try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Passenger_details");
            while (rs.next()){
                if(rs.getString(1).equals(text.getText()) && rs.getString(4).equals(new String(password.getPassword())))
				{
					flag2 = true;
					break;
				}
			}
			 con.close();
            } 
		catch (Exception ex)
		{
           System.out.println(ex);
        }
		}			
			
		if(flag1)
		{
          	AdminHomePageFrame adminhome = new AdminHomePageFrame(this);
			this.setVisible(false);
		}
        else if(flag2)
        {
            this.setVisible(false);
		}
      else if(!flag1 && !flag2)
        {
            JOptionPane.showMessageDialog(this,"Information Not Match","Warning",JOptionPane.WARNING_MESSAGE);
		}		
		
		}
		if(button2==e.getSource())
		{
			SignUpFrame suf=new SignUpFrame(this);
			this.setVisible(false);
		}
		if(button3==e.getSource())
		{
			
		}
	}
	
		
}