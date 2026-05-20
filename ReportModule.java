import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportModule {

public static void viewRooms() {

    try {

        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM rooms";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n=================================================================");
        System.out.printf("%-10s %-15s %-12s %-12s %-12s\n",
                "ROOM", "TYPE", "CAPACITY", "OCCUPIED", "RENT");

        System.out.println("=================================================================");

        while (rs.next()) {

            System.out.printf("%-10d %-15s %-12d %-12d %-12.2f\n",
                    rs.getInt("room_no"),
                    rs.getString("type"),
                    rs.getInt("capacity"),
                    rs.getInt("occupied"),
                    rs.getDouble("rent"));
        }

        System.out.println("=================================================================");

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}
public static void allocatedStudents() {

    try {

        Connection con = DBConnection.getConnection();

        String query = "SELECT s.student_id, s.name, a.room_no " +
                       "FROM students s " +
                       "JOIN allocations a ON s.student_id = a.student_id";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n=================================================");
        System.out.printf("%-15s %-20s %-10s\n",
                "STUDENT ID", "NAME", "ROOM");

        System.out.println("=================================================");

        while(rs.next()) {

            System.out.printf("%-15d %-20s %-10d\n",
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getInt("room_no"));
        }

        System.out.println("=================================================");

    } catch(Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}
public static void notAllocatedStudents() {

    try {

        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM students " +
                       "WHERE student_id NOT IN " +
                       "(SELECT student_id FROM allocations)";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n=======================================================");
        System.out.printf("%-15s %-20s %-15s\n",
                "STUDENT ID", "NAME", "COURSE");

        System.out.println("=======================================================");

        while(rs.next()) {

            System.out.printf("%-15d %-20s %-15s\n",
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("course"));
        }

        System.out.println("=======================================================");

    } catch(Exception e) {

        System.out.println("Error: " + e.getMessage());
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
    public static void viewStudents() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==============================================================");
            System.out.printf("%-10s %-15s %-15s %-10s %-15s\n",
                    "ID", "NAME", "COURSE", "YEAR", "CONTACT");
            System.out.println("==============================================================");

            while (rs.next()) {

                System.out.printf("%-10d %-15s %-15s %-10d %-15s\n",
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getInt("year"),
                        rs.getString("contact"));
            }

            System.out.println("==============================================================");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
