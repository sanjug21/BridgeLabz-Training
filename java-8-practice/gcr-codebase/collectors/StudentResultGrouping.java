import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    String name;
    String gradeLevel;

    public Student(String name, String gradeLevel) {
        this.name = name;
        this.gradeLevel = gradeLevel;
    }

    public String getName() {
        return name;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }
}

public class StudentResultGrouping {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Aarav", "Grade 10"));
        students.add(new Student("Isha", "Grade 9"));
        students.add(new Student("Neha", "Grade 10"));
        students.add(new Student("Rohan", "Grade 8"));
        students.add(new Student("Priya", "Grade 9"));

        Map<String, List<String>> namesByGrade = students.stream()
                .collect(Collectors.groupingBy(Student::getGradeLevel,
                        Collectors.mapping(Student::getName, Collectors.toList())));

        System.out.println("Student Names Grouped By Grade:");
        namesByGrade.forEach((grade, names) -> System.out.println(grade + " -> " + names));
    }
}
