import java.sql.*;

public class ReportModule {

    public static void viewRooms() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM rooms");

            System.out.println("Room | Type | Capacity | Occupied | Rent");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("room_no") + " " +
                        rs.getString("type") + " " +
                        rs.getInt("capacity") + " " +
                        rs.getInt("occupied") + " " +
                        rs.getDouble("rent")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unpaidStudents() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT s.student_id,s.name FROM students s LEFT JOIN payments p ON s.student_id=p.student_id WHERE p.id IS NULL");

            System.out.println("Students With No Payment");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " " +
                        rs.getString("name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
