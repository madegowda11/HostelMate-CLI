import java.sql.*;
import java.util.Scanner;

public class RoomModule {

    public static void addRoom() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Room Number: ");
            int roomNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Room Type: ");
            String type = sc.nextLine();

            System.out.print("Capacity: ");
            int capacity = sc.nextInt();

            System.out.print("Monthly Rent: ");
            double rent = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO rooms VALUES(?,?,?,?,?)");

            ps.setInt(1, roomNo);
            ps.setString(2, type);
            ps.setInt(3, capacity);
            ps.setInt(4, 0);
            ps.setDouble(5, rent);

            ps.executeUpdate();

            System.out.println("Room Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}