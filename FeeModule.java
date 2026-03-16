import java.sql.*;
import java.util.Scanner;

public class FeeModule {

    public static void recordPayment() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Student ID: ");
            int id = sc.nextInt();

            System.out.print("Month: ");
            int month = sc.nextInt();

            System.out.print("Year: ");
            int year = sc.nextInt();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO payments(student_id,month,year,amount,status) VALUES(?,?,?,?,?)");

            ps.setInt(1, id);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setDouble(4, amount);
            ps.setString(5, "PAID");

            ps.executeUpdate();

            System.out.println("Payment Recorded");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
