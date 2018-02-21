import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RetrieveBLOBDataFromMySQL {

    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DBUtil.getConnection(DBType.MYSQLDBLOB);

        String sql = "SELECT photo FROM EmployeeLOB WHERE eid = 1";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Blob imgBlob = rs.getBlob("photo");
            FileOutputStream fos = new FileOutputStream("/home/ryan/copyofsynthwavebackground.jpg");

            fos.write(imgBlob.getBytes(1, (int)imgBlob.length()));

            fos.flush();
            fos.close();

            System.out.println("Photo of Employee 1 has been downloaded to home directory"
                    + " successfully.");
        }
        else {
            System.out.println("Employee Record Not Found.");
        }

        rs.close();
        ps.close();
        conn.close();
    }
}
