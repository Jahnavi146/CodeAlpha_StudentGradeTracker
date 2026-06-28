import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allGrades = new ArrayList<>();
        
        System.out.println("================================================");
        System.out.println("        STUDENT GRADE TRACKER");
        System.out.println("================================================");
        System.out.println("Welcome to the Student Grade Management System!");
        System.out.println("================================================\n");
        
        while (true) {
            System.out.println("\n================================================");
            System.out.println("                  MAIN MENU");
            System.out.println("================================================");
            System.out.println("1. Add New Student");
            System.out.println("2. Add Grade to Student");
            System.out.println("3. Display All Students Report");
            System.out.println("4. Display Student Details");
            System.out.println("5. Display Class Summary");
            System.out.println("6. Exit");
            System.out.println("================================================");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.print("\nEnter student name: ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    names.add(name);
                    allGrades.add(new ArrayList<>());
                    System.out.println("Student '" + name + "' added successfully!");
                } else {
                    System.out.println("Name cannot be empty!");
                }
            }
            else if (choice == 2) {
                if (names.isEmpty()) {
                    System.out.println("\nNo students in the system!");
                    continue;
                }
                
                System.out.println("\n=== STUDENT LIST ===");
                for (int i = 0; i < names.size(); i++) {
                    System.out.println((i + 1) + ". " + names.get(i));
                }
                System.out.print("Enter student number: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
                
                if (index >= 0 && index < names.size()) {
                    System.out.print("Enter grade (0-100): ");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (grade >= 0 && grade <= 100) {
                        allGrades.get(index).add(grade);
                        System.out.println("Grade added successfully!");
                    } else {
                        System.out.println("Invalid grade! Please enter 0-100.");
                    }
                } else {
                    System.out.println("Invalid student number!");
                }
            }
            else if (choice == 3) {
                if (names.isEmpty()) {
                    System.out.println("\nNo students in the system!");
                    continue;
                }
                
                System.out.println("\n================================================");
                System.out.println("              ALL STUDENTS REPORT");
                System.out.println("================================================");
                for (int i = 0; i < names.size(); i++) {
                    System.out.println((i + 1) + ". " + names.get(i));
                    System.out.println("   Grades: " + allGrades.get(i));
                    if (!allGrades.get(i).isEmpty()) {
                        int sum = 0;
                        for (int g : allGrades.get(i)) {
                            sum += g;
                        }
                        double avg = (double) sum / allGrades.get(i).size();
                        System.out.println("   Average: " + String.format("%.2f", avg));
                        
                        String gradeLetter;
                        if (avg >= 90) gradeLetter = "A";
                        else if (avg >= 80) gradeLetter = "B";
                        else if (avg >= 70) gradeLetter = "C";
                        else if (avg >= 60) gradeLetter = "D";
                        else gradeLetter = "F";
                        System.out.println("   Grade: " + gradeLetter);
                    }
                    System.out.println();
                }
                System.out.println("================================================");
            }
            else if (choice == 4) {
                if (names.isEmpty()) {
                    System.out.println("\nNo students in the system!");
                    continue;
                }
                
                System.out.println("\n=== STUDENT LIST ===");
                for (int i = 0; i < names.size(); i++) {
                    System.out.println((i + 1) + ". " + names.get(i));
                }
                System.out.print("Enter student number to view details: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
                
                if (index >= 0 && index < names.size()) {
                    System.out.println("\n================================================");
                    System.out.println("            STUDENT DETAILS");
                    System.out.println("================================================");
                    System.out.println("Name: " + names.get(index));
                    System.out.println("Grades: " + allGrades.get(index));
                    
                    if (!allGrades.get(index).isEmpty()) {
                        ArrayList<Integer> grades = allGrades.get(index);
                        int sum = 0;
                        int highest = grades.get(0);
                        int lowest = grades.get(0);
                        for (int g : grades) {
                            sum += g;
                            if (g > highest) highest = g;
                            if (g < lowest) lowest = g;
                        }
                        double avg = (double) sum / grades.size();
                        System.out.println("Average Score: " + String.format("%.2f", avg));
                        System.out.println("Highest Score: " + highest);
                        System.out.println("Lowest Score: " + lowest);
                        
                        String gradeLetter;
                        if (avg >= 90) gradeLetter = "A";
                        else if (avg >= 80) gradeLetter = "B";
                        else if (avg >= 70) gradeLetter = "C";
                        else if (avg >= 60) gradeLetter = "D";
                        else gradeLetter = "F";
                        System.out.println("Grade Letter: " + gradeLetter);
                    } else {
                        System.out.println("No grades available for this student.");
                    }
                    System.out.println("================================================");
                } else {
                    System.out.println("Invalid student number!");
                }
            }
            else if (choice == 5) {
                if (names.isEmpty()) {
                    System.out.println("\nNo students in the system!");
                    continue;
                }
                
                System.out.println("\n================================================");
                System.out.println("              CLASS SUMMARY");
                System.out.println("================================================");
                System.out.println("Total Students: " + names.size());
                
                double classTotal = 0;
                int studentsWithGrades = 0;
                double highestGrade = 0;
                double lowestGrade = 100;
                
                for (ArrayList<Integer> grades : allGrades) {
                    if (!grades.isEmpty()) {
                        int sum = 0;
                        for (int g : grades) {
                            sum += g;
                        }
                        double avg = (double) sum / grades.size();
                        classTotal += avg;
                        studentsWithGrades++;
                        if (avg > highestGrade) highestGrade = avg;
                        if (avg < lowestGrade) lowestGrade = avg;
                    }
                }
                
                if (studentsWithGrades > 0) {
                    System.out.println("Class Average: " + String.format("%.2f", classTotal / studentsWithGrades));
                    System.out.println("Highest Class Grade: " + String.format("%.2f", highestGrade));
                    System.out.println("Lowest Class Grade: " + String.format("%.2f", lowestGrade));
                } else {
                    System.out.println("No grades have been entered yet.");
                }
                System.out.println("================================================");
            }
            else if (choice == 6) {
                System.out.println("\n================================================");
                System.out.println("  Thank you for using Student Grade Tracker!");
                System.out.println("================================================");
                break;
            }
            else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}