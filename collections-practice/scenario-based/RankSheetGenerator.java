import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " (" + score + ")";
    }
}

public class RankSheetGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // 1. Input Student Data
        System.out.print("Enter number of students: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println("Enter Name and Score for each student:");
            for (int i = 0; i < n; i++) {
                String name = sc.next();
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    students.add(new Student(name, score));
                }
            }
        }

        System.out.println("\nOriginal List:");
        for (Student s : students) System.out.println(s);

        // 2. Perform Merge Sort
        List<Student> sortedStudents = mergeSort(students);

        // 3. Display Rank Sheet
        System.out.println("\nRank Sheet (Sorted by Score):");
        for (int i = 0; i < sortedStudents.size(); i++) {
            System.out.println("Rank " + (i + 1) + ": " + sortedStudents.get(i));
        }
        
        sc.close();
    }

    // Recursive Merge Sort function
    public static List<Student> mergeSort(List<Student> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Student> left = new ArrayList<>(list.subList(0, mid));
        List<Student> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    // Merge two sorted lists into one
    public static List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            // Sort descending by score.
            // Stability check: if scores are equal, prefer left (original order) to maintain stability.
            if (left.get(i).score >= right.get(j).score) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        // Add remaining elements
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }
}