import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class RetrieveCLOBDataFromMySQL {

    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DBUtil.getConnection(DBType.MYSQLDBLOB);

        String sql = "SELECT resume FROM EmployeeLOB WHERE eid = 1";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            Clob resume = rs.getClob("Resume");
            Reader data = resume.getCharacterStream();

            int i;
            String resumeDetails = "";
            while ( (i = data.read()) != -1){
                resumeDetails += ((char) i);
            }
            System.out.println("Resume Details for Employee #1: ");
            System.out.println(resumeDetails);
        } else {
            System.err.println("No record found for Employee with ID: 1");
        }

        rs.close();
        ps.close();
        conn.close();
    }
}
