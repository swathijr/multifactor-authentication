package atm;

import java.sql.*;  
class Dbconnect{  
public Connection getConnect()  
//public static void main(String args[])
{
Connection con=null;
try{  
Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multifactor","root","password");  
return con;
//System.out.println(con);
}
catch(Exception e)
{
System.out.println(e);
}
return con;
}
}