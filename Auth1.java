package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
class Auth1 implements ActionListener,ItemListener
{
	String no=Home.cardno;
	String num=no;
	JFrame jf;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3;
 	JTextField tf;
	JPanel p1,p2;
	Choice c1;
	String path;
	static String exten;
	JRadioButton r1,r2;
	public Font f = new Font("Times new roman" , Font.BOLD , 16);	
	public Font f1 = new Font("Times new roman", Font.BOLD , 28);
	public Font f2 = new Font("Times new roman", Font.BOLD , 18);
	public Font f3 = new Font("Engravers MT", Font.BOLD , 15);
	public Auth1()
	{
		jf=new JFrame();
		Container con=jf.getContentPane();
	    ImageIcon ic1=new ImageIcon("title.jpg");
		ImageIcon ic2=new ImageIcon("imgg.jpg");
		l1=new JLabel("Welcome");
		l2=new JLabel(ic2);
		r1=new JRadioButton("Generate OTP");
        r2=new JRadioButton("Security Question");
		l3=new JLabel("Enter Card Number :");
		l3.setForeground(Color.BLACK);
		tf=new JTextField(25);
		l4=new JLabel("Select language :");
		l4.setForeground(Color.BLACK);
        c1=new Choice();
        c1.addItem("c");
		c1.addItem("cpp");
		c1.addItem("java");
		l3.setFont(f2);
		l4.setFont(f1);
        b1=new JButton("OK");
		b2=new JButton("OK");
		b3=new JButton("CANCEL");
		p1=new JPanel();
		p2=new JPanel();
		c1.setFont(f2);
		b1.setFont(f2);
		b2.setFont(f2);
		b3.setFont(f2);
		p1.setSize(500,100);
		p2.setBounds(0,100,600,100);
		jf.setTitle("Multifactor Authentication");
		jf.setVisible(true);
		jf.setSize(595,300);
		jf.setLocation(200,80);
		jf.setLayout(null);
		r1.setFont(f1);
		r2.setFont(f1);
		//l1.setBounds(0,0,900,100);
		r1.setBounds(10,10,200,50);
		//l4.setBounds(100,410,300,30);
		//c1.setBounds(340,410,100,30);
		r2.setBounds(10,100,400,50);
		//tf.setBounds(340,460,200,30);
		//b1.setBounds(560,460,100,30);
		b2.setBounds(80,190,100,30);
		b3.setBounds(320,190,100,30);
		//con.add(l1);
		//con.add(p1);
		//con.add(l3);
		//con.add(tf);
		//con.add(l4);
		//con.add(c1);
		con.add(b1);
		con.add(r1);
		con.add(r2);
		con.add(b2);
		con.add(l2);
		con.add(b3);
		c1.addItemListener(this);
		jf.setDefaultLookAndFeelDecorated(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		r1.addActionListener(this);
		r2.addActionListener(this);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent win) {
				System.exit(0);
			}
		});
       	
	}
public void actionPerformed(ActionEvent ae)
	{
		String label=ae.getActionCommand();
		String p=tf.getText();
						String l=c1.getSelectedItem();
			if(label.equals("Generate OTP"))
		{
		int randomPin   =(int)(Math.random()*9000)+1000;
		String otp  =String.valueOf(randomPin);
		System.out.println("Your OTP is "+otp);
		String nm=JOptionPane.showInputDialog("Enter the OTP  sent to your register mobile "); 
		if(nm.equals(otp))
			{
			jf.setVisible(false);
			new Auth2(num);
			}
		else
			JOptionPane.showMessageDialog(null,"Please enter the correct OTP ");

		}
else
		{
String answer;
String ques;
String ans;

	try{
	Dbconnect db=new Dbconnect();
	Connection con=db.getConnect();
	PreparedStatement stmt=con.prepareStatement("select sques,ans from carddetails where cardno=?");
	stmt.setString(1,no);
	ResultSet res=stmt.executeQuery();
	if(res.next())
	{
		ques=res.getString("sques");
		ans=res.getString("ans");
		System.out.println(ans);
		answer = JOptionPane.showInputDialog(ques); 
		
		System.out.println(answer);
		if(ans.equalsIgnoreCase(answer)){
		
				jf.setVisible(false);
				new Auth2(num);
		}
		else 
			JOptionPane.showMessageDialog(null,"Please enter the correct answer");

					
	}
	
	}
	catch(Exception e)
	{
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
		Auth1 hm= new Auth1();
	}
		
}
	
		
