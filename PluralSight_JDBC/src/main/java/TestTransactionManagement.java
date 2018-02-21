import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestTransactionManagement {

    public static void main(String[] args) throws SQLException {

        try {
            Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            Scanner sc = new Scanner(System.in);

            System.out.println("PSBank Transactions");
            System.out.println("-----------------------");
            System.out.println("Enter From Account # :");
            int fromAcno = Integer.parseInt(sc.nextLine());
            System.out.println("Enter To Account # : ");
            int toAcno = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Amount to Transfer : " );
            double amount = Double.parseDouble(sc.nextLine());

            String withdrawSQL = "Update PSBANK set AMOUNT = AMOUNT - ? where ACNO = ?";
            ps = conn.prepareStatement(withdrawSQL);
            ps.setDouble(1, amount);
            ps.setInt(2, fromAcno);
            ps.executeUpdate();

            String despositSQL = "Update PSBANK set AMOUNT = AMOUNT + ? where ACNO = ?";
            ps = conn.prepareStatement(despositSQL);
            ps.setDouble(1,amount);
            ps.setInt(2, toAcno);
            ps.executeUpdate();

            String sql = "Select AMOUNT From PSBANK where ACNO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fromAcno);
            ResultSet rs = ps.executeQuery();

            double balanceAmount = 0;
            if (rs.next()){
                balanceAmount = rs.getDouble("Amount");
            }

            if (balanceAmount >= 5000){
                conn.commit();
                System.out.println("Amount Transferred Successfully.");
            } else {
                conn.rollback();
                System.out.println("Insufficient Funds : " + balanceAmount + ". Transaction(s) Rolled Back.");
            }

            sc.close();
            ps.close();
            conn.close();

        } catch (SQLException ex){
            DBUtil.showErrorMessage(ex);
        }
    }
}
