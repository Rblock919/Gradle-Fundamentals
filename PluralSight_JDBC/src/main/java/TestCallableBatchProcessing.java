import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TestCallableBatchProcessing {

    public static void main(String[] args) {
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);

                CallableStatement callableStatement = conn
                        .prepareCall("{call AddNewEmployee(?,?,?,?,?)}");
                )
        {
            String option;

            do {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter Employee # : ");
                int empno = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Employee Name : ");
                String ename = scanner.nextLine();
                System.out.print("Enter Email : ");
                String email = scanner.nextLine();
                System.out.print("Enter HireDate (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();

                callableStatement.setInt(1, empno);
                callableStatement.setString(2, ename);
                callableStatement.setString(3, email);
                callableStatement.setString(4, date);
                callableStatement.setDouble(5, salary);

                callableStatement.addBatch();

                scanner.nextLine();

                System.out.print("Do You Wish To Add Another Record? (yes/no): ");
                option = scanner.nextLine();

            } while (option.equals("yes"));


            int []updateCounts = callableStatement.executeBatch();

            System.out.println("Total Records Updated: " + updateCounts.length);
        }
        catch (SQLException ex) {
            DBUtil.showErrorMessage(ex);
        }
    }
}
