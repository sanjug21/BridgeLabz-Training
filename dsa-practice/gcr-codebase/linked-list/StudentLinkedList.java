import java.util.*;

class Student {
    String name;
    String rollNo;
    char grade;
    int age;

    Student(String name, String rollNo, char grade, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
        this.age = age;
    }
}

public class StudentLinkedList {
    static Scanner sc = new Scanner(System.in);
    Node<Student> head;

    public void addStudent(Student student) {
        Node<Student> nn = new Node<>(student);
        if (head == null) {
            head = nn;
            return;
        }
        Node<Student> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nn;
    }

    public void deleteStudent(String rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<Student> temp = head;
        Node<Student> prev = null;
        while (temp != null) {
            if (temp.data.rollNo.equals(rollNo)) {
                if (prev == null) {
                    head = temp.next;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Student with roll number " + rollNo + " deleted successfully.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }

        System.out.println("Student with roll number " + rollNo + " not found.");
    }

    public void updateStudentDetails(String rollNo, String name, char grade, int age) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<Student> temp = head;
        while (temp != null) {
            if (temp.data.rollNo.equals(rollNo)) {
                temp.data.name = name;
                temp.data.grade = grade;
                temp.data.age = age;
                System.out.println("Student with roll number " + rollNo + " updated successfully.");
                return;
            }
            temp = temp.next;
        }

    }

    public void searchStudent(String rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<Student> temp = head;
        while (temp != null) {
            if (temp.data.rollNo.equals(rollNo)) {
                System.out.printf("%-15s %-15s %-5s %-5s%n", "Name", "Roll No", "Grade", "Age");
                System.out.printf("%-15s %-15s %-5c %-5d%n",
                        temp.data.name,
                        temp.data.rollNo,
                        temp.data.grade,
                        temp.data.age);
                return;
            }
            temp = temp.next;
        }
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<Student> temp = head;
        System.out.printf("%-15s %-15s %-5s %-5s%n", "Name", "Roll No", "Grade", "Age");
        System.out.println("----------------------------------------------------------");
        while (temp != null) {
            System.out.printf("%-15s %-15s %-5c %-5d%n",
                    temp.data.name,
                    temp.data.rollNo,
                    temp.data.grade,
                    temp.data.age);

            temp = temp.next;
        }

    }

    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        System.out.println("==== Welcome to Student Management System ====");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    list.addStudentsInList();
                    break;
                case 2:
                    System.out.println("Enter Roll No to delete: ");
                    String rollNo = sc.nextLine();
                    list.deleteStudent(rollNo);
                    break;
                case 3:
                    System.out.println("Enter Roll No to update: ");
                    String rollNo1 = sc.nextLine();
                    list.updateStudent(rollNo1);
                    break;
                case 4:
                    System.out.println("Enter Roll No to search: ");
                    String rollNo2 = sc.nextLine();
                    list.searchStudent(rollNo2);
                    break;
                case 5:
                    list.displayStudents();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public void addStudentsInList() {

        System.out.println("Enter details for Student ");
        try {
            System.out.println("Enter Name: ");
            String name = sc.nextLine();
            if (name.trim().length() == 0)
                throw new InvalidInputException("Name can not be Empty");

            System.out.println("Enter Roll No: ");
            // roll no should be of 10 digits
            String rollNo = sc.nextLine();
            if (rollNo.length() != 10 || !rollNo.matches("\\d+")) {
                throw new InvalidInputException("Roll No must be a 10-digit number.");
            }

            System.out.println("Enter grade ('A', 'B', 'C', 'D'): ");
            char grade = sc.nextLine().trim().charAt(0);
            if (grade < 'A' && grade > 'D')
                throw new InvalidInputException("Entered invalid grade.");

            System.out.println("Enter age: ");
            int age = sc.nextInt();
            if (age < 0 && age > 100)
                throw new InvalidInputException("Entered invalid age");

            Student student = new Student(name, rollNo, grade, age);
            addStudent(student);
            System.out.println("Student Added Successfully!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to add student.");

        }
    }

    public void updateStudent(String rollNo) {
        try {
            System.out.println("Enter updated details for Student ");
            System.out.println("Enter Name: ");
            String name = sc.nextLine();
            if (name.trim().length() == 0)
                throw new InvalidInputException("Name can not be Empty");
            System.out.println("Enter grade ('A', 'B', 'C', 'D'): ");
            char grade = sc.nextLine().trim().charAt(0);
            if (grade < 'A' && grade > 'D')
                throw new InvalidInputException("Entered invalid grade.");

            System.out.println("Enter age: ");
            int age = sc.nextInt();
            if (age < 0 && age > 100)
                throw new InvalidInputException("Entered invalid age");

            updateStudentDetails(rollNo, name, grade, age);
        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Unable to update student.");
        }
    }
}
