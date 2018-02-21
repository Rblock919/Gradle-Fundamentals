import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class TestCallableOut {

    public static void main(String[] args) {


        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                CallableStatement callableStatement = conn.
                        prepareCall("{ call getSalary(?,?) }");
                Scanner scanner = new Scanner(System.in);
                )
        {
            System.out.print("Enter Employee ID #: ");
            int eid = Integer.parseInt(scanner.nextLine());

            callableStatement.setInt(1, eid);

            callableStatement.registerOutParameter(2, Types.DOUBLE);

            callableStatement.execute();

            double empSal = callableStatement.getDouble(2);

            System.out.println("Salary of Employee w/ ID #" + eid + ": " + empSal);

        } catch (SQLException ex) {
            DBUtil.showErrorMessage(ex);
        }
    }
}
