import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBLOBDataToMySQL {

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = DBUtil.getConnection(DBType.MYSQLDBLOB);

        String sql = "UPDATE EmployeeLOB SET photo = ? WHERE eid = 1";

        PreparedStatement ps = conn.prepareStatement(sql);

        File file = new File("/hdd/Pictures/synthwavebackgound.jpg");

        FileInputStream fis = new FileInputStream(file);

        ps.setBinaryStream(1, fis, fis.available());

        int count = ps.executeUpdate();

        System.out.println("Total records updated: " + count);
        ps.close();
        conn.close();
    }
}
