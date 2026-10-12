import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        double grade;

        Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1) Add student");
            System.out.println("2) Show summary report");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": addStudent(); break;
                case "2": showReport(); break;
                case "3": System.out.println("Bye!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Student name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name can't be empty.");
            return;
        }
        double grade = readGrade();
        students.add(new Student(name, grade));
        System.out.println("Added " + name + ".");
    }

    static double readGrade() {
        while (true) {
            System.out.print("Grade (0-100): ");
            try {
                double g = Double.parseDouble(sc.nextLine().trim());
                if (g >= 0 && g <= 100) return g;
            } catch (NumberFormatException e) {
                // fall through to the message below
            }
            System.out.println("Please enter a number between 0 and 100.");
        }
    }

    static void showReport() {
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }

        double sum = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        System.out.println("\n--- Summary Report ---");
        System.out.printf("%-20s %s%n", "Name", "Grade");
        for (Student s : students) {
            System.out.printf("%-20s %.2f%n", s.name, s.grade);
            sum += s.grade;
            if (s.grade > highest.grade) highest = s;
            if (s.grade < lowest.grade) lowest = s;
        }

        System.out.println("----------------------");
        System.out.printf("Students : %d%n", students.size());
        System.out.printf("Average  : %.2f%n", sum / students.size());
        System.out.printf("Highest  : %.2f (%s)%n", highest.grade, highest.name);
        System.out.printf("Lowest   : %.2f (%s)%n", lowest.grade, lowest.name);
    }
}
