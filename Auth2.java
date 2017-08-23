package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import java.sql.*;
class Auth2 implements ActionListener,ItemListener
{
    
    JFrame jf;
    JLabel l1,l2,l3,l4;
    JButton b1,b2,b3;
     JTextField tf;
    JPanel p1,p2;
    Choice c1;
    String path,exten;
    JRadioButton r1,r2,r3,r4;
    String pic1="pic/";
    String pic2="pic/";
    String pic3="pic/";
    String pic4="pic/";
    ButtonGroup bg;
    int k=0,i;
    public Font f = new Font("Times new roman" , Font.BOLD , 16);    
    public Font f1 = new Font("Times new roman", Font.BOLD , 28);
    public Font f2 = new Font("Times new roman", Font.BOLD , 18);
    public Font f3 = new Font("Engravers MT", Font.BOLD , 15);
    String num;
    public Auth2(String p)
    {

jf=new JFrame();
num=p;
        //jf1.setVisible(false);
        int randomPin   =(int)(Math.random()*9000)+1000;
        String otp  =String.valueOf(randomPin);
        int n=Integer.parseInt(otp);
        int sum=n;
        while(sum!=0)
        {
            sum=0;
            while(n!=0)
            {
            int d=n%10;
            sum=sum+d;
            n=n/10;
            }
            if(sum>9)
                n=sum;
            else
                break;
        }
        
        
        k=sum/2;
        if(k==0)
            k=1;
        String pi="";
        System.out.println(k);
        try
        {
        Dbconnect db=new Dbconnect();
        Connection con=db.getConnect();
        PreparedStatement stmt=con.prepareStatement("select *from carddetails where cardno=?");
                stmt.setString(1,p);
                ResultSet res=stmt.executeQuery();
                if(res.next())
                {
                    pi=res.getString(k+5);
                }
                System.out.println(pi);
        }
        catch(Exception e)
        {
            
        }
        File folder = new File("pic/");
        File[] listOfFiles = folder.listFiles();

    for ( i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println(listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println(listOfFiles[i].getName());
      }
    }
    Random rnum = new Random();
    String s2[]=new String[4];
            for( i=0;i<4;i++)
            {
                     if(i==k)
                         s2[i]="pic/"+pi;
                    else
                        s2[i]="pic/"+listOfFiles[rnum.nextInt(32)].getName();

                System.out.println(s2[i]);
            }
            
        //pic1=pic1+"orange.jpg";
        
        Container con=jf.getContentPane();
        ImageIcon ic1=new ImageIcon(s2[0]);
        ImageIcon ic2=new ImageIcon(s2[1]);
        ImageIcon ic3=new ImageIcon(s2[2]);
        ImageIcon ic4=new ImageIcon(s2[3]);
        Image img=ic1.getImage();
        //Image nic=img.getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
        ic1=getScaled(ic1);
        ic1=getScaled(ic1);
        ic2=getScaled(ic2);
        ic3=getScaled(ic3);
        ic4=getScaled(ic4);
        l1=new JLabel("Choose Your Registered Image");
        l2=new JLabel(ic2);
        b1=new JButton("OK");
        b2=new JButton("OK");
        b3=new JButton("CANCEL");
        p1=new JPanel();
        p2=new JPanel();
        r1=new JRadioButton("1",ic1);
        r2=new JRadioButton(ic2);
        r3=new JRadioButton(ic3);
        r4=new JRadioButton(ic4);
        bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2); 
        bg.add(r3);
        bg.add(r4); 
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        p1.setSize(500,100);
        p2.setBounds(0,100,600,100);
        r1.setBounds(50,100,200,100);
        r2.setBounds(250,100,200,100);
        r3.setBounds(50,250,200,100);
        r4.setBounds(250,250,200,100);
        b2.setBounds(50,400,100,90);
        b3.setBounds(270,400,100,90);
        jf.setTitle("Multifactor Authentication:: Level-2");
        jf.setVisible(true);
        jf.setSize(500,600);
        jf.setLocation(200,80);
        jf.setLayout(null);
        l1.setBounds(0,0,900,100);
        con.add(b2);
        con.add(b3);
        con.add(r1);
        con.add(r2);
        con.add(r3);
        con.add(r4);
        jf.setDefaultLookAndFeelDecorated(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        r1.addItemListener(this);
        r2.addItemListener(this);
        r3.addItemListener(this);
        r4.addItemListener(this);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent win) {
                System.exit(0);
            }
        });
           
    }
    public static ImageIcon getScaled(ImageIcon ic1)
    {
        Image img=ic1.getImage();
        Image nic=img.getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
        ic1=new ImageIcon(nic);
        return ic1;
    }
public void actionPerformed(ActionEvent ae)
    {
        String label=ae.getActionCommand();
        String l=c1.getSelectedItem();
        if(label.equals("Generate OTP"))
        {
        int randomPin   =(int)(Math.random()*9000)+1000;
        String otp  =String.valueOf(randomPin);
        JOptionPane.showMessageDialog(null,"Your OTP is "+otp); 

        }
else
        {
        JOptionPane.showInputDialog("What is your Favourite Food "); 
        }
    }        
 
        public void itemStateChanged(ItemEvent ie)
        {
            //AbstractButton l=c1.getSelectedItem();
            int p=0;
                        if(r1.isSelected())
                                                    p=1;
                            //JOptionPane.showMessageDialog(null,"You Selected 1");
                        else if(r2.isSelected())
                            p=2;
                            //JOptionPane.showMessageDialog(null,"You Selected 1");
                        else if(r3.isSelected())
                            p=3;
                            //JOptionPane.showMessageDialog(null,"You Selected 1");
                        else if(r4.isSelected())
                            p=4;
                            //JOptionPane.showMessageDialog(null,"You Selected 1");
                        if(k!=4){
                        if(p==k+1)
                            {
                                jf.setVisible(false);
                                new Auth3(num);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Invalid Object Selected");
                        System.exit(0);
                        
        }
                        }
        else if (k==4){
              if(p==k)
                {
                    jf.setVisible(false);
                    new Auth3(num);
                }
                else
                    JOptionPane.showMessageDialog(null,"Invalid Object Selected");
            
}
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
        Auth2 hm= new Auth2(num);
    }
}	