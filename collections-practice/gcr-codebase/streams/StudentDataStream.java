import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataStream {

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "students.dat";

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice", 3.8));
        students.add(new Student(2, "Bob", 3.5));
        students.add(new Student(3, "Charlie", 3.9));

        try {
            writeStudents(filePath, students);
            List<Student> loaded = readStudents(filePath);
            System.out.println("Students from file:");
            for (Student s : loaded) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    private static void writeStudents(String filePath, List<Student> students) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {
            for (Student s : students) {
                dos.writeInt(s.rollNumber);
                dos.writeUTF(s.name);
                dos.writeDouble(s.gpa);
            }
            System.out.println("Students saved to " + filePath);
        }
    }

    private static List<Student> readStudents(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    int roll = dis.readInt();
                    String name = dis.readUTF();
                    double gpa = dis.readDouble();
                    students.add(new Student(roll, name, gpa));
                } catch (EOFException eof) {
                    break;
                }
            }
        }
        return students;
    }

    private static class Student {
        private final int rollNumber;
        private final String name;
        private final double gpa;

        Student(int rollNumber, String name, double gpa) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.gpa = gpa;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "rollNumber=" + rollNumber +
                    ", name='" + name + '\'' +
                    ", gpa=" + gpa +
                    '}';
        }
    }
}
