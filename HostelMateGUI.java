import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HostelMateGUI extends JFrame implements ActionListener {

    JButton addRoomBtn, registerStudentBtn, allocateRoomBtn;
    JButton vacateRoomBtn, feeBtn, viewRoomBtn;
    JButton viewStudentBtn, exitBtn;

    JTextArea outputArea;

    public HostelMateGUI() {

        setTitle("HostelMate - Hostel Management System");
        setSize(900, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel title = new JLabel("HOSTELMATE MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(220, 20, 500, 40);
        add(title);

        addRoomBtn = new JButton("Add Room");
        addRoomBtn.setBounds(50, 100, 220, 40);
        add(addRoomBtn);

        registerStudentBtn = new JButton("Register Student");
        registerStudentBtn.setBounds(320, 100, 220, 40);
        add(registerStudentBtn);

        allocateRoomBtn = new JButton("Allocate Room");
        allocateRoomBtn.setBounds(590, 100, 220, 40);
        add(allocateRoomBtn);

        vacateRoomBtn = new JButton("Vacate Room");
        vacateRoomBtn.setBounds(50, 170, 220, 40);
        add(vacateRoomBtn);

        feeBtn = new JButton("Record Fee Payment");
        feeBtn.setBounds(320, 170, 220, 40);
        add(feeBtn);

        viewRoomBtn = new JButton("View Rooms");
        viewRoomBtn.setBounds(590, 170, 220, 40);
        add(viewRoomBtn);

        viewStudentBtn = new JButton("View Students");
        viewStudentBtn.setBounds(220, 240, 220, 40);
        add(viewStudentBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(500, 240, 220, 40);
        add(exitBtn);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane pane = new JScrollPane(outputArea);
        pane.setBounds(50, 320, 760, 200);
        add(pane);

        addRoomBtn.addActionListener(this);
        registerStudentBtn.addActionListener(this);
        allocateRoomBtn.addActionListener(this);
        vacateRoomBtn.addActionListener(this);
        feeBtn.addActionListener(this);
        viewRoomBtn.addActionListener(this);
        viewStudentBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // ADD ROOM
        if (e.getSource() == addRoomBtn) {

            try {

                String roomNo = JOptionPane.showInputDialog(this, "Enter Room Number:");
                String type = JOptionPane.showInputDialog(this, "Enter Room Type:");
                String capacity = JOptionPane.showInputDialog(this, "Enter Capacity:");
                String rent = JOptionPane.showInputDialog(this, "Enter Rent:");

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO rooms VALUES(?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(roomNo));
                ps.setString(2, type);
                ps.setInt(3, Integer.parseInt(capacity));
                ps.setInt(4, 0);
                ps.setDouble(5, Double.parseDouble(rent));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Room Added Successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // REGISTER STUDENT
        else if (e.getSource() == registerStudentBtn) {

            try {

                String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
                String name = JOptionPane.showInputDialog(this, "Enter Name:");
                String course = JOptionPane.showInputDialog(this, "Enter Course:");
                String year = JOptionPane.showInputDialog(this, "Enter Year:");
                String contact = JOptionPane.showInputDialog(this, "Enter Contact:");

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO students VALUES(?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(id));
                ps.setString(2, name);
                ps.setString(3, course);
                ps.setInt(4, Integer.parseInt(year));
                ps.setString(5, contact);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Student Registered Successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // ALLOCATE ROOM
        else if (e.getSource() == allocateRoomBtn) {

            try {

                String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");
                String roomNo = JOptionPane.showInputDialog(this, "Enter Room Number:");

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO allocations VALUES(?,?)";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(studentId));
                ps.setInt(2, Integer.parseInt(roomNo));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Room Allocated Successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // VACATE ROOM
        else if (e.getSource() == vacateRoomBtn) {

            try {

                String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");

                Connection con = DBConnection.getConnection();

                String query = "DELETE FROM allocations WHERE student_id=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(studentId));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Room Vacated Successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // RECORD PAYMENT
        else if (e.getSource() == feeBtn) {

            try {

                String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");
                String amount = JOptionPane.showInputDialog(this, "Enter Amount:");

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO payments VALUES(?,?)";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(studentId));
                ps.setDouble(2, Double.parseDouble(amount));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Payment Recorded Successfully!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // VIEW ROOMS
        else if (e.getSource() == viewRoomBtn) {

            try {

                Connection con = DBConnection.getConnection();

                String query = "SELECT * FROM rooms";

                PreparedStatement ps = con.prepareStatement(query);

                ResultSet rs = ps.executeQuery();

                outputArea.setText("");

                outputArea.append(
                        "ROOM\tTYPE\tCAPACITY\tOCCUPIED\tRENT\n");

                outputArea.append(
                        "============================================================\n");

                while (rs.next()) {

                    outputArea.append(
                            rs.getInt("room_no") + "\t" +
                            rs.getString("type") + "\t" +
                            rs.getInt("capacity") + "\t\t" +
                            rs.getInt("occupied") + "\t\t" +
                            rs.getDouble("rent") + "\n");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // VIEW STUDENTS
        else if (e.getSource() == viewStudentBtn) {

            try {

                Connection con = DBConnection.getConnection();

                String query = "SELECT * FROM students";

                PreparedStatement ps = con.prepareStatement(query);

                ResultSet rs = ps.executeQuery();

                outputArea.setText("");

                outputArea.append(
                        "ID\tNAME\tCOURSE\tYEAR\tCONTACT\n");

                outputArea.append(
                        "============================================================\n");

                while (rs.next()) {

                    outputArea.append(
                            rs.getInt("student_id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getString("course") + "\t" +
                            rs.getInt("year") + "\t" +
                            rs.getString("contact") + "\n");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // EXIT
        else if (e.getSource() == exitBtn) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new HostelMateGUI();
    }
}