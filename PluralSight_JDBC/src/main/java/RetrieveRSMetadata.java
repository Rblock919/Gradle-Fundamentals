import java.io.IOException;
import java.sql.*;

public class RetrieveRSMetadata {

    public static void main(String[] args) throws SQLException, IOException {

        try(
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
                )
        {
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            String format = "%-50s%-25s%-20s%-20s\n";
            System.out.format(format, "Column Name", "Column Type", "Is Nullable", "Is AutoIncrement");
            System.out.format(format,"-----------", "-----------", "-----------", "----------------");
            for (int i = 1; i <= columnCount; i++){
                System.out.format(format,rsmd.getColumnName(i), rsmd.getColumnType(i), rsmd.isNullable(i), rsmd.isAutoIncrement(i));
            }

            System.out.println("\nTotal Columns: " + columnCount);
        } catch (SQLException ex) {
            DBUtil.showErrorMessage(ex);
        }
    }
}
