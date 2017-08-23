package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import java.sql.*;
class Auth3 implements ActionListener,ItemListener
{
	
	JFrame jf;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3;
 	JPasswordField tf;
	JPanel p1,p2;
	Choice c1;
	String path,exten;
	JRadioButton r1,r2,r3,r4;
	String num;
	public Font f = new Font("Times new roman" , Font.BOLD , 16);	
	public Font f1 = new Font("Times new roman", Font.BOLD , 28);
	public Font f2 = new Font("Times new roman", Font.BOLD , 18);
	public Font f3 = new Font("Engravers MT", Font.BOLD , 15);
	public Auth3(String no)
	{
		jf=new JFrame();
		num=no;
		Container con=jf.getContentPane();
	    ImageIcon ic1=new ImageIcon("1.jpg");
		ImageIcon ic2=new ImageIcon("12.jpg");
		ImageIcon ic3=new ImageIcon("31.jpg");
		ImageIcon ic4=new ImageIcon("61.jpg");
		l1=new JLabel("Enter Your ATM PIN Number");
		tf=new JPasswordField(25);
		
		
		b2=new JButton("SUBMIT");
		b3=new JButton("CANCEL");
		p1=new JPanel();
		p2=new JPanel();
		
		l1.setFont(f1);
		b2.setFont(f2);
		b3.setFont(f2);
		p1.setSize(700,100);
		p2.setBounds(0,100,600,100);
		l1.setBounds(50,80,300,100);
		tf.setBounds(50,80,300,50);
		b2.setBounds(50,180,100,50);
		b3.setBounds(300,180,100,50);
		jf.show();
		jf.setVisible(true);
		jf.setSize(455,300);
		jf.setLocation(200,80);
		jf.setLayout(null);
		l1.setBounds(0,0,900,100);
		con.add(b2);
		con.add(b3);
		con.add(l1);
		con.add(tf);
		jf.setDefaultLookAndFeelDecorated(true);
		//b1.addActionListener(this);
		//b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		//r1.addActionListener(this);
		//r2.addActionListener(this);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent win) {
				System.exit(0);
			}
		});
       	
	}
public void actionPerformed(ActionEvent ae)
	{
		String lab=ae.getActionCommand();
		@SuppressWarnings("deprecation")
		String pin=tf.getText();
		if(lab.equals("SUBMIT"))
		{
if(pin.equals("")||pin.length()<4||pin.length()>4)

			JOptionPane.showMessageDialog(null,"Please enter a valid Pin");
		
		else
		{
			String nam;
			try
		{
				int pi=Integer.parseInt(pin);
				int pii=0;
		Dbconnect db=new Dbconnect();
		Connection con=db.getConnect();
		PreparedStatement stmt=con.prepareStatement("select *from carddetails where cardno=?");
				stmt.setString(1,num);
				ResultSet res=stmt.executeQuery();
				//System.out.println(con);
				if(res.next())
				{
					
					pii=res.getInt(10);
					System.out.println("Valid"+pii);
				}
				if(pi==pii){
					nam=res.getString(2);
					System.out.println("Valid");
					if(lab.equals("SUBMIT"))
					JOptionPane.showMessageDialog(null,"welcome "+ nam);
					 if(lab.equals("CANCEL"))
						System.exit(0);
				}
				
				else
JOptionPane.showMessageDialog(null,"Invalid Pin Number");
		}
		catch(Exception e)
		{
			
		}
		}
		}
		

	}
		
		    
 
		public void itemStateChanged(ItemEvent ie)
		{
			String l=c1.getSelectedItem();
			

				//JOptionPane.showMessageDialog(null,"You Selected"+l);
		}
		public static void main(String a[])
	{
		try
		{
			
		         UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				 
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		String no=Home.cardno;
		String num=no;
			Auth3 hm= new Auth3(num);
	}
}
