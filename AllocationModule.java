import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AllocationModule {

    public static void allocateRoom() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Room Number: ");
            int roomNo = sc.nextInt();

            PreparedStatement checkStudent = con.prepareStatement(
                    "SELECT * FROM allocations WHERE student_id=? AND status='ACTIVE'");

            checkStudent.setInt(1, studentId);
            ResultSet rs1 = checkStudent.executeQuery();

            if (rs1.next()) {
                System.out.println("Student already allocated a room");
                return;
            }

            PreparedStatement checkRoom = con.prepareStatement(
                    "SELECT capacity,occupied FROM rooms WHERE room_no=?");

            checkRoom.setInt(1, roomNo);
            ResultSet rs = checkRoom.executeQuery();

            if (rs.next()) {

                int capacity = rs.getInt("capacity");
                int occupied = rs.getInt("occupied");

                if (occupied >= capacity) {
                    System.out.println("Room Full");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO allocations(student_id,room_no,checkin_date,status) VALUES(?,?,CURDATE(),'ACTIVE')");

                ps.setInt(1, studentId);
                ps.setInt(2, roomNo);
                ps.executeUpdate();

                PreparedStatement update = con.prepareStatement(
                        "UPDATE rooms SET occupied=occupied+1 WHERE room_no=?");

                update.setInt(1, roomNo);
                update.executeUpdate();

                System.out.println("Room Allocated Successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void vacateRoom() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Student ID: ");
            int studentId = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT room_no FROM allocations WHERE student_id=? AND status='ACTIVE'");

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int roomNo = rs.getInt("room_no");

                PreparedStatement updateAlloc = con.prepareStatement(
                        "UPDATE allocations SET status='INACTIVE', checkout_date=CURDATE() WHERE student_id=? AND status='ACTIVE'");

                updateAlloc.setInt(1, studentId);
                updateAlloc.executeUpdate();

                PreparedStatement updateRoom = con.prepareStatement(
                        "UPDATE rooms SET occupied=occupied-1 WHERE room_no=?");

                updateRoom.setInt(1, roomNo);
                updateRoom.executeUpdate();

                System.out.println("Room Vacated Successfully");

            } else {

                System.out.println("Student not allocated to any room");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}