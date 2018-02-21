import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCLOBDataToMySQL {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Connection conn = DBUtil.getConnection(DBType.MYSQLDBLOB);
        PreparedStatement ps = null;

        String sql = "UPDATE EmployeeLOB SET resume = ? WHERE eid = 1";
        ps = conn.prepareStatement(sql);

        String resumeFile = "/home/ryan/Resume.txt";
        File file = new File(resumeFile);
        FileReader reader = new FileReader(file);

        ps.setCharacterStream(1, reader, (int)file.length());

        ps.executeUpdate();

        System.out.println("Resume Updated Successfully...");
        ps.close();
        conn.close();
    }
}
