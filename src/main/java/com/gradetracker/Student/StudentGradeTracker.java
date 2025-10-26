package com.gradetracker.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===");

        while (true) {
            System.out.print("\nEnter student name (or type 'done' to finish): ");
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter grade for " + name + ": ");
            double grade;

            // Validate grade input
            while (true) {
                try {
                    grade = Double.parseDouble(sc.nextLine());
                    if (grade < 0 || grade > 100) {
                        System.out.print("Grade must be between 0 and 100. Try again: ");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid number! Enter a valid grade: ");
                }
            }

            students.add(new Student(name, grade));
        }

        if (students.isEmpty()) {
            System.out.println("\nNo student data entered.");
            sc.close();
            return;
        }

        double total = 0, highest = students.get(0).getGrade(), lowest = students.get(0).getGrade();
        String topStudent = students.get(0).getName(), lowStudent = students.get(0).getName();

        for (Student s : students) {
            total += s.getGrade();
            if (s.getGrade() > highest) {
                highest = s.getGrade();
                topStudent = s.getName();
            }
            if (s.getGrade() < lowest) {
                lowest = s.getGrade();
                lowStudent = s.getName();
            }
        }

        double average = total / students.size();

        System.out.println("\n=== Grade Summary Report ===");
        System.out.printf("%-20s %-10s\n", "Student Name", "Grade");
        System.out.println("----------------------------------");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f\n", s.getName(), s.getGrade());
        }

        System.out.println("----------------------------------");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.printf("Highest Grade: %.2f (%s)\n", highest, topStudent);
        System.out.printf("Lowest Grade : %.2f (%s)\n", lowest, lowStudent);

        sc.close();
    }
}
