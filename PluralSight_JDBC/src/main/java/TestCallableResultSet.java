import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestCallableResultSet {

    public static void main(String[] args) throws SQLException {

        try
                (
                        Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                        CallableStatement callableStatement = conn.
                            prepareCall("{call GetStudentsByCourseId(?)}");
                        Scanner scanner = new Scanner(System.in);
                ) {
            System.out.print("Enter Course Id: ");
            int cid = Integer.parseInt(scanner.nextLine());

            callableStatement.setInt(1, cid);
            ResultSet rs = callableStatement.executeQuery();

            int resultNo = 1;

            while (rs.next()){

                System.out.println("--------------- Student " + resultNo
                        + " ---------------");
                resultNo++;

                System.out.println("Student Enrollment Number: " + rs.getInt("RollNumber"));
                System.out.println("Student Name: " + rs.getString("StudentName"));
                System.out.println("Course Id: " + rs.getInt("CourseId"));
                System.out.println("Fees: " + rs.getDouble("Fees"));
                System.out.println("Percentage: " + rs.getDouble("Percentage"));

            }

        } catch (SQLException ex) {
            DBUtil.showErrorMessage(ex);
        }
    }
}
