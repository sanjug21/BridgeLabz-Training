import java.util.Scanner;

public class CourierShiftSystem {

    public static void main(String[] args) {
        ShiftScheduler scheduler = new ShiftScheduler();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(scheduler);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  COURIER STAFF SHIFT SCHEDULER");
            System.out.println("========================================");
            System.out.println("1. Add Employee");
            System.out.println("2. Create Shift");
            System.out.println("3. Assign Shift to Employee");
            System.out.println("4. Remove Shift Assignment");
            System.out.println("5. Display All Employees");
            System.out.println("6. Display All Shifts");
            System.out.println("7. Display Shift Assignments");
            System.out.println("8. Display All Shift Assignments");
            System.out.println("9. Display Statistics");
            System.out.println("10. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addEmployee(scheduler, sc);
                        break;
                    case 2:
                        createShift(scheduler, sc);
                        break;
                    case 3:
                        assignShift(scheduler, sc);
                        break;
                    case 4:
                        removeShiftAssignment(scheduler, sc);
                        break;
                    case 5:
                        scheduler.displayAllEmployees();
                        break;
                    case 6:
                        scheduler.displayAllShifts();
                        break;
                    case 7:
                        displayShiftAssignments(scheduler, sc);
                        break;
                    case 8:
                        scheduler.displayAllShiftAssignments();
                        break;
                    case 9:
                        scheduler.displayStatistics();
                        break;
                    case 10:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (ShiftAlreadyAssignedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(ShiftScheduler scheduler) {
        scheduler.addEmployee(new Employee("E001", "Rahul", "9876543210"));
        scheduler.addEmployee(new Employee("E002", "Priya", "9876543211"));
        scheduler.addEmployee(new Employee("E003", "Amit", "9876543212"));
        scheduler.addEmployee(new Employee("E004", "Sneha", "9876543213"));

        ShiftTime morning = new ShiftTime("Morning", "06:00", "14:00");
        ShiftTime afternoon = new ShiftTime("Afternoon", "14:00", "22:00");
        ShiftTime night = new ShiftTime("Night", "22:00", "06:00");

        scheduler.createShift(morning);
        scheduler.createShift(afternoon);
        scheduler.createShift(night);

        try {
            scheduler.assignShift("E001", morning);
            scheduler.assignShift("E002", morning);
            scheduler.assignShift("E003", afternoon);
            scheduler.assignShift("E004", night);

            System.out.println("\nDemo data initialized successfully!");

        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addEmployee(ShiftScheduler scheduler, Scanner sc) {
        System.out.print("\nEnter Employee ID: ");
        String employeeId = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        scheduler.addEmployee(new Employee(employeeId, name, phone));
    }

    private static void createShift(ShiftScheduler scheduler, Scanner sc) {
        System.out.print("\nEnter Shift Name: ");
        String shiftName = sc.nextLine();

        System.out.print("Enter Start Time (HH:MM): ");
        String startTime = sc.nextLine();

        System.out.print("Enter End Time (HH:MM): ");
        String endTime = sc.nextLine();

        scheduler.createShift(new ShiftTime(shiftName, startTime, endTime));
    }

    private static void assignShift(ShiftScheduler scheduler, Scanner sc) 
            throws ShiftAlreadyAssignedException {
        System.out.print("\nEnter Employee ID: ");
        String employeeId = sc.nextLine();

        System.out.print("Enter Shift Name: ");
        String shiftName = sc.nextLine();

        ShiftTime shift = scheduler.findShiftByName(shiftName);
        if (shift == null) {
            System.out.println("Shift not found: " + shiftName);
            return;
        }

        scheduler.assignShift(employeeId, shift);
    }

    private static void removeShiftAssignment(ShiftScheduler scheduler, Scanner sc) {
        System.out.print("\nEnter Employee ID: ");
        String employeeId = sc.nextLine();

        System.out.print("Enter Shift Name: ");
        String shiftName = sc.nextLine();

        ShiftTime shift = scheduler.findShiftByName(shiftName);
        if (shift == null) {
            System.out.println("Shift not found: " + shiftName);
            return;
        }

        scheduler.removeShiftAssignment(employeeId, shift);
    }

    private static void displayShiftAssignments(ShiftScheduler scheduler, Scanner sc) {
        System.out.print("\nEnter Shift Name: ");
        String shiftName = sc.nextLine();

        ShiftTime shift = scheduler.findShiftByName(shiftName);
        if (shift == null) {
            System.out.println("Shift not found: " + shiftName);
            return;
        }

        scheduler.displayShiftAssignments(shift);
    }
}
