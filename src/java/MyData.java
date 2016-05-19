import java.sql.*;
public class MyData {
    public static Connection connect() throws Exception{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:EShopDsn");
        return con;
    }
}
