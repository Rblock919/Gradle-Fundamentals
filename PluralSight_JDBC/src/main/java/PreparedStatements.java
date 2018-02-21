

import java.sql.*;
import java.util.Scanner;

public class PreparedStatements {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //conn = DriverManager.getConnection(dbUrl, username, password);

            conn = DBUtil.getConnection(DBType.MYSQLDB);

            /*--- Initial test ---
             String sql = "SELECT * FROM table1 WHERE sex = ?";
             ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
             prepareStatement(ps,'M');
             System.out.println("---------------------------------------------");
             prepareStatement(ps, 'F'); */

            /*--- Insert test ---
            String name;
            String sex;
            String sql = "INSERT INTO table1 (name, sex) VALUES (?,?)";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            name = scanner.nextLine();
            System.out.println("Enter Gender (M/F): ");
            sex = scanner.next();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, sex);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Record added successfully");
            } else {
                System.err.println("Error while adding input");
            }
            scanner.close(); */

            /*--- Update test ---
            String sql = "UPDATE table1 SET sex = ? WHERE name = ?";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name for new gender lol: ");
            String name = scanner.nextLine();
            System.out.println("Enter new gender lol: ");
            String sex = scanner.next();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sex);
            ps.setString(2, name);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println(name + " updated successfully");
            } else {
                System.err.println("Error while adding input");
            }
            scanner.close(); */

            //--- Remove test ---
            String sql ="DELETE FROM table1 WHERE name = ?";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name to be deleted: ");
            String name = scanner.nextLine();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println(name + " removed successfully");
            } else {
                System.err.println("Error while adding input");
            }
            scanner.close();

            System.out.println("\nConnection to MySQL database established using DBUtil.");

        } catch (SQLException e) {

            DBUtil.showErrorMessage(e);

        } finally {

            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if(conn != null){
                conn.close();
                System.out.println("Connections to database closed.");
            }

        }

    }

    private static void prepareStatement(PreparedStatement ps, char sex) throws SQLException {
        ResultSet rs;

        ps.setString(1, Character.toString(sex));

        rs = ps.executeQuery();

        while (rs.next()){
            System.out.println("Name: " + rs.getString("name")
                    + ", Gender: " + rs.getString("sex"));
        }

        if (rs != null){
            rs.close();
        }
    }

}
