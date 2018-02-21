import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveDBMetadata {

    public static void main(String[] args) throws SQLException, IOException {
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
            )
        {
            DatabaseMetaData dmbd = conn.getMetaData();

            System.out.println("Driver Name: " + dmbd.getDriverName());
            System.out.println("Driver Version: " + dmbd.getDriverVersion());
            System.out.println("Logged in User: " + dmbd.getUserName());
            System.out.println("Database Product Name: " + dmbd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dmbd.getDatabaseProductVersion());

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String schemaTypes[] = { "TABLE" };
            ResultSet rs = dmbd.getTables(catalog, schemaPattern, tableNamePattern, schemaTypes);
            System.out.println("\nTables");
            System.out.println("==============");
            while ( rs.next() ){
                System.out.println( rs.getString("TABLE_NAME") );
            }

            String columnNamePattern = null;
            rs = dmbd.getColumns(catalog, schemaPattern, "Students", columnNamePattern);
            System.out.println("\nColumn Details for Students table: ");
            System.out.println("==================================");
            while ( rs.next() ) {
                System.out.println( rs.getString("COLUMN_NAME") );
            }

            rs.close();
        } catch (SQLException ex){
            DBUtil.showErrorMessage(ex);
        }
    }
}