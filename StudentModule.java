import java.sql.*;
import java.util.Scanner;

public class StudentModule {

    public static void registerStudent() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Course: ");
            String course = sc.nextLine();

            System.out.print("Year: ");
            int year = sc.nextInt();
            sc.nextLine();

            System.out.print("Contact: ");
            String contact = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students VALUES(?,?,?,?,?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setInt(4, year);
            ps.setString(5, contact);

            ps.executeUpdate();

            System.out.println("Student Registered Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}