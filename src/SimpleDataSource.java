import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleDataSource 
{
 private static String url;
 private static String username;
 private static String password;
 
 public static Connection getConnection() throws SQLException
 {
  String server = "jdbc:mysql://140.119.19.73:9306/";
  String database = "MG03";
  String config= "?useUnicode=true&characterEncoding=utf8";
  url = server + database + config;
  username = "MG03";
  password = "rtpu6s";
  return DriverManager.getConnection(url, username, password);
 }
}
