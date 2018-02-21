import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionPooling {

    public static void main(String[] args) throws SQLException {

        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

        ds.setDatabaseName("");
        ds.setServerName("");
        ds.setPort();
        ds.setUser("");
        ds.setPassword("");

        PooledConnection pconn = ds.getPooledConnection();

        Connection conn = pconn.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Students");

        System.out.println("---------------------------------------");

        while (rs.next()){

            System.out.println("\tStudent Enrollment Number: " + rs.getInt("RollNumber"));
            System.out.println("\tStudent Name: " + rs.getString("StudentName"));
            System.out.println("\tCourse Id: " + rs.getInt("CourseId"));
            System.out.println("\tFees: " + rs.getDouble("Fees"));
            System.out.println("\tPercentage: " + rs.getDouble("Percentage"));
            System.out.println("---------------------------------------");
        }

        rs.close();
        stmt.close();
        conn.close();
        pconn.close();
    }
}
