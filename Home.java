package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import java.sql.*;
class Home implements ActionListener,ItemListener
{
	static String cardno;
	JFrame jf;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3;
 	JTextField tf;
	JPanel p1,p2;
	Choice c1;
	String path,exten;
	public Font f = new Font("Times new roman" , Font.BOLD , 16);	
	public Font f1 = new Font("Times new roman", Font.BOLD , 28);
	public Font f2 = new Font("Times new roman", Font.BOLD , 18);
	public Font f3 = new Font("Engravers MT", Font.BOLD , 15);
	public Home()
	{
		jf=new JFrame();
		Container con=jf.getContentPane();
	    ImageIcon ic1=new ImageIcon("title.jpg");
		ImageIcon ic2=new ImageIcon("imgg.jpg");
		l1=new JLabel(ic1);
		l2=new JLabel(ic2);
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
        b1=new JButton("SUBMIT");
		b2=new JButton("CLEAR");
		b3=new JButton("CLOSE");
		p1=new JPanel();
		p2=new JPanel();
		c1.setFont(f2);
		b1.setFont(f2);
		b2.setFont(f2);
		b3.setFont(f2);
		p1.setSize(700,100);
		p2.setBounds(0,100,600,100);
		jf.show();
		jf.setVisible(true);
		jf.setSize(695,600);
		jf.setLocation(200,80);
		jf.setLayout(null);
		//l1.setBounds(0,0,900,100);
		l2.setBounds(0,0,700,400);
		//l4.setBounds(100,410,300,30);
		//c1.setBounds(340,410,100,30);
		l3.setBounds(80,460,400,30);
		tf.setBounds(340,460,200,30);
		b1.setBounds(560,460,100,30);
		b2.setBounds(280,520,100,30);
		b3.setBounds(420,520,100,30);
		//con.add(l1);
		//con.add(p1);
		con.add(l3);
		con.add(tf);
		con.add(l4);
		//con.add(c1);
		con.add(b1);
		con.add(b2);
		con.add(l2);
		con.add(b3);
		c1.addItemListener(this);
		jf.setDefaultLookAndFeelDecorated(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
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
		try{
		if(label.equals("SUBMIT"))
		{
			
			if(p.equals("")||p.length()<16||p.length()>16)
                        {
				JOptionPane.showMessageDialog(null,"Please Enter a Valid Card Number");
				
                        }
                        
			else
			{
				cardno=p;
				Dbconnect db=new Dbconnect();
				Connection con=db.getConnect();
				PreparedStatement stmt=con.prepareStatement("select name,accno from carddetails where cardno=?");
				stmt.setString(1,p);
				ResultSet res=stmt.executeQuery();
				if(res.next())
				{
					jf.setVisible(false);
								new Auth1();
				}
				else				
				JOptionPane.showMessageDialog(null,"Your Card is not Registered with Bank");
				//}
			}
				
		}
                
		else if(label.equals("CLEAR"))
		{
			
			tf.setText("");
			
		}
		else if(label.equals("CLOSE"))
		{
			System.exit(0);
		}
		}
		catch(Exception e)
		{
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
			Home hm= new Home();
	}
}
	
	