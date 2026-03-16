import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("---- HostelMate CLI ----");
            System.out.println("1 Admin Login");
            System.out.println("2 Exit");

            int choice = sc.nextInt();

            if (choice == 1) {

                while (true) {

                    System.out.println("1 Add Room");
                    System.out.println("2 Register Student");
                    System.out.println("3 Allocate Room");
                    System.out.println("4 Vacate Room");
                    System.out.println("5 Record Fee Payment");
                    System.out.println("6 View Room Occupancy");
                    System.out.println("7 View Unpaid Students");
                    System.out.println("8 Logout");

                    int option = sc.nextInt();

                    switch (option) {

                        case 1:
                            RoomModule.addRoom();
                            break;

                        case 2:
                            StudentModule.registerStudent();
                            break;

                        case 3:
                            AllocationModule.allocateRoom();
                            break;

                        case 4:
                            AllocationModule.vacateRoom();
                            break;

                        case 5:
                            FeeModule.recordPayment();
                            break;

                        case 6:
                            ReportModule.viewRooms();
                            break;

                        case 7:
                            ReportModule.unpaidStudents();
                            break;

                        case 8:
                            System.exit(0);

                    }
                }
            } else {

                System.exit(0);

            }
        }
    }
}