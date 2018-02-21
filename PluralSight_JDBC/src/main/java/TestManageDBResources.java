import java.sql.*;

public class TestManageDBResources {


    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //conn = DriverManager.getConnection(dbUrl, username, password);

            conn = DBUtil.getConnection(DBType.MYSQLDB);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM table1");

            while (rs.next()){
                System.out.println("Name: " + rs.getString("name")
                        + ", Gender: " + rs.getString("sex"));
            }

            System.out.println("\nConnection to MySQL database established using DBUtil.");
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        } finally {

            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
                System.out.println("Connections to database closed.");
            }

        }



    }
}
