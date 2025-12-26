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

public class UpdateTicketPrice extends JFrame implements ActionListener
{
	private JLabel label1,label2,label3,label4,label5;
	private JPanel panel;
	private JButton button1,button2,button3;
	Font font1=new Font("cambria",Font.BOLD+Font.ITALIC,30);
	Font font2=new Font("cambria",Font.BOLD,19);
	Font font3=new Font("cambria",Font.BOLD,15);
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTextField text1,text2,text3;
	public int row;
	
	private LogInFrame login;
	private UpdateIntercityTrainInfoFrame updateIntercityTrainInfo;
	private AdminHomePageFrame adminhome;
	
	public UpdateTicketPrice(UpdateIntercityTrainInfoFrame updateIntercityTrainInfo,LogInFrame login,AdminHomePageFrame adminhome)
	{
		super("Bangladesh Railway");
		this.updateIntercityTrainInfo=updateIntercityTrainInfo;
		this.login=login;
		this.adminhome=adminhome;
		
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
		
		label1=new JLabel("Update InterCity Train Ticket Price");
		label1.setBounds(80,15,500,70);
		label1.setFont(font1);
		
		button1=new JButton("Sing Out");
		button1.setBounds(760,25,100,30);
		button1.setFont(font3);
		button1.addActionListener(this);
		
		label2 = new JLabel("Rout No : ");
		label2.setBounds(85,100,200,30);
		label2.setFont(font2);
		label2.setForeground(new Color(2,11,33));
		
		text1 = new JTextField();
        text1.setBounds(85,137,200,45);
        text1.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        text1.setForeground(Color.BLACK);
        text1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		text1.setForeground(new Color(232,72,104));
		text1.setHorizontalAlignment(JTextField.CENTER);
		text1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		text1.setEditable(false);
        text1.setCaretColor(Color.BLACK);
        //text1.setBorder(null);
        //text1.setOpaque(false);
		
		label3 = new JLabel("Current Ticket Price : ");
		label3.setBounds(550,100,200,30);
		label3.setFont(font2);
		label3.setForeground(new Color(2,11,33));
		
		text2 = new JTextField();
        text2.setBounds(550,137,200,45);
        text2.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        text2.setForeground(Color.BLACK);
        text2.setForeground(new Color(232,72,104));
		text2.setHorizontalAlignment(JTextField.CENTER);
		text2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		text2.setEditable(false);
        text2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        text2.setCaretColor(Color.BLACK);
		
		label4 = new JLabel("Update Ticket Price: ");
		label4.setBounds(550,280,300,35);
		label4.setFont(new Font("cambria",Font.BOLD,25));
		label4.setForeground(new Color(2,11,33));
		
		text3 = new JTextField();
        text3.setBounds(550,325,200,45);
        text3.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        text3.setForeground(Color.BLACK);
        text3.setForeground(new Color(232,72,104));
		text3.setHorizontalAlignment(JTextField.CENTER);
		text3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		//text3.setEditable(false);
        text3.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        text3.setCaretColor(Color.BLACK);
		
		
		button2=new JButton("update>>");
		button2.setBounds(367,200,110,30);
		button2.setFont(font3);
		button2.addActionListener(this);
		
		
		label5 = new JLabel("BD All Train Route Ticket Price");
		label5.setBounds(85,300,300,35);
		label5.setFont(new Font("cambria",Font.BOLD,18));
		//label5.setVisible(false);
		label5.setForeground(new Color(2,11,33));
		
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
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
		scroll.setBounds(85,345,370,300);
		
		model.addColumn("Route-ID");
		model.addColumn("Route Information");
		model.addColumn("Ticket Price");
		
		table.setFont(new Font("cambria",Font.BOLD,10));
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(255,153,51));
		table.getTableHeader().setFont(font3);
		table.setRowHeight(30);
		//table.setEnabled(false);
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				try{
				 row = table.getSelectedRow();
				String routeNo= table.getModel().getValueAt(row,0).toString();
				String price = table.getModel().getValueAt(row,2).toString();
				text1.setText(routeNo);
				text2.setText(price);
				}
				catch(Exception exp)
				{
					
				}
				
			}
		});
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select ri.R_Id,ri.R_Info,amt.price from Route_Info ri,Admin_Manage_ticket amt where ri.R_Id=amt.R_No");
            while (rs.next()){
				
				model.addRow(new Object[]{
				rs.getInt(1),
				rs.getString(2),
				rs.getFloat(3)
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
			if(!text1.getText().equals("") && !text3.getText().equals("") && !text2.getText().equals("") && table.getSelectedRows().length > 0)
			{
				int option = JOptionPane.showConfirmDialog(this,"Are you sure to Confirm Update Price?");
			if(option == JOptionPane.YES_OPTION){
				model.setValueAt(text1.getText(),row,0);
		        model.setValueAt(text3.getText(),row,2);
				updateAdminManageTicket();
				updateTicketInfo();
				JOptionPane.showMessageDialog(this,"Update Successfull");
				this.setVisible(false);
				UpdateIntercityTrainInfoFrame uitif = new UpdateIntercityTrainInfoFrame(adminhome,login);
			}
			}
		}
	}
	
	public void updateAdminManageTicket()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					float price = Float.parseFloat(text3.getText());
					int R_No = Integer.parseInt(text1.getText());
            PreparedStatement stmt = con.prepareStatement("update Admin_Manage_ticket set price = ? where R_No = ?");
            stmt.setFloat(1, price);
            stmt.setInt(2, R_No);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Update Successful!!!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void updateTicketInfo()
	{
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "BangladeshRailway", "BangladeshRailway");
					float price = Float.parseFloat(text3.getText());
					int R_No = Integer.parseInt(text1.getText());
            PreparedStatement stmt = con.prepareStatement("update Ticket_Info set price = ? where R_No = ?");
            stmt.setFloat(1, price);
            stmt.setInt(2, R_No);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Update Successful!!!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
}