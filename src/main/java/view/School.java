package view;

import java.io.Console;
import java.util.ArrayList;

import entities.CardID;
import entities.Course;
import entities.Professor;
import entities.Student;

public class School {
  private boolean flag = false;
  private Console sc = System.console();
  private ArrayList<Student> students;
  private ArrayList<Professor> professors;
  private ArrayList<Course> courses;
  private ArrayList<CardID> cardIDs;
  private boolean running;

  public School() {
    this.professors = new ArrayList<Professor>();
    this.students = new ArrayList<Student>();
    this.courses = new ArrayList<Course>();
    this.cardIDs = new ArrayList<CardID>();
    this.running = true;
  }

  public void run() {
    while (running) {
      System.out.println("<==================== MENU ====================>");
      System.out.println("1. Register student;");
      System.out.println("2. Register professor;");
      System.out.println("3. Register course;");
      System.out.println("4. Enroll a professor in a course;");
      System.out.println("5. Enroll a student in a course;");
      System.out.println("6. Disenroll a student from a course;");
      System.out.println("7. List students from a course;");
      System.out.println("8. List courses from a professor;");
      System.out.println("9. Register cardID for a student by name;");
      System.out.println("10. List courses from a student;");
      System.out.println("11. Remove cardID from a student;");
      System.out.println("12. List all students;");
      System.out.println("13. List all courses;");
      System.out.println("14. List all professors;");
      System.out.println("15. List all CardIDs;");
      System.out.println("[X] Type any another number to exit;");

      int cmd = Integer.parseInt(sc.readLine("Set command: "));
      switch (cmd) {
        case 1:
          String name = sc.readLine("Set student name: ");
          int age = Integer.parseInt(sc.readLine("Set student age: "));
          students.add(new Student(name, age));
          System.out.println("Student registered!");
          break;

        case 2:
          String profName = sc.readLine("Set prof name: ");
          int profAge = Integer.parseInt(sc.readLine("Set prof age: "));
          professors.add(new Professor(profName, profAge));
          System.out.println("Professor registered!");
          break;

        case 3:
          String courseName = sc.readLine("Set course name: ");
          courses.add(new Course(courseName));
          System.out.println("Course " + courseName + " has been created!");
          break;

        case 4:
          profName = sc.readLine("Set prof name: ");
          courseName = sc.readLine("Set course name: ");

          for (Professor prof : professors) {
            if (prof.getName().equals(profName)) {
              for (Course course : courses) {
                if (course.getName().equals(courseName)) {
                  prof.setCourse(course);
                  System.out.println("Operação feita com sucesso");
                  flag = true;
                  break;
                }
              }
            }
          }

          if (!flag)
            System.out.println("Set professor ou Course nao encontrado, operação negada");

          break;

        case 5:
          name = sc.readLine("Set student name: ");
          courseName = sc.readLine("Set course name: ");

          for (Student student : students) {
            if (student.getName().equals(name)) {
              for (Course course : courses) {
                if (course.getName().equals(courseName)) {
                  if (student.getCardID() != null) {
                    course.setStudent(student);
                    System.out.println("Operação feita com sucesso");
                    flag = true;
                    break;
                  } else {
                    System.out.println("Student does not have a card!");
                    flag = true;
                    break;
                  }
                }
              }
            }
          }

          if (!flag)
            System.out.println("Student ou Course nao encontrado, operação negada");

          break;

        case 6:
          name = sc.readLine("Set student name: ");
          courseName = sc.readLine("Set course name: ");

          for (Student student : students) {
            if (student.getName().equals(name)) {
              for (Course course : courses) {
                if (course.getName().equals(courseName)) {
                  course.removeStudent(student);
                  System.out.println("Operação feita com sucesso");
                  flag = true;
                  break;
                }
              }
            }
          }
          if (!flag)
            System.out.println("Student ou Course nao encontrado, operação negada");

          break;

        case 7:
          courseName = sc.readLine("Set course name: ");

          for (Course course : courses) {
            if (course.getName().equals(courseName)) {
              if (course.getStudents().size() > 0) {
                System.out.println("<==[ Students of " + courseName + " ]==>");
                for (Student student : course.getStudents())
                  System.out.println("- " + student.getName());
              } else
                System.out.println("This course is empty");
              flag = true;
            }
          }

          if (!flag)
            System.out.println("Course nao encontrado, operação negada");

          break;

        case 8:
          profName = sc.readLine("Set prof name: ");

          for (Professor prof : professors) {
            if (prof.getName().equals(profName)) {
              if (prof.getCourses().size() > 0) {
                System.out.println("<==[ Courses of " + profName + " ]==>");
                for (Course course : courses)
                  System.out.println("- " + course.getName());
              } else
                System.out.println("This course is empty");
              flag = true;
            }
          }

          if (!flag)
            System.out.println("Set professor nao encontrado, operação negada");

          break;

        case 9:
          name = sc.readLine("nome do Student: ");

          for (Student student : students) {
            if (student.getName().equals(name)) {
              CardID card = new CardID();
              card.setStudent(student);
              cardIDs.add(card);
              flag = true;
              break;
            }
          }
          if (!flag)
            System.out.println("Student não encontrado ou ja registered, operação negada");

          break;

        case 10:
          String UUID = sc.readLine("Set card UUID: ");

          for (CardID card : cardIDs) {
            if (card.getCode().toString().equals(UUID)) {
              if (card.getStudent().getCourses().size() > 0) {
                System.out.println("<==[ Courses of " + card.getStudent().getName() + " ]==>");
                for (Course course : card.getStudent().getCourses())
                System.out.println("- " + course.getName());
              } else
                System.out.println("Student não possui Courses");
              flag = true;
            }
          }

          if (!flag)
            System.out.println("UUID não encontrado, operação negada");

          break;

        case 11:
          name = sc.readLine("nome do Student: ");

          for (CardID cardID : cardIDs) {
            if (cardID.getStudent().getName().equals(name)) {
              cardID.removeStudent();
              flag = true;
              System.out.println("Operação feita com sucesso");
            }
          }
          if (!flag)
            System.out.println("Student nem cartão tem, operação negada");

          break;
        
        case 12:
          String op = sc.readLine("Show only students with cardID? (y/n): ").toLowerCase();

          if (students.size() > 0) {
            System.out.println("<----- Students (with cardID) ----->");
            for(Student _student : students) {
              if (op.equals("y")) {
                if (_student.getCardID() != null)
                  System.out.println("- " + _student.getName() + ";");
              } else
                System.out.println("- " + _student.getName() + ";");
            }
          } else
            System.out.println("No students registered!");
          
          break;
          
        case 13:
          if (courses.size() > 0) {
            System.out.println("<----- Courses ----->");
            for(Course _course : courses)
              System.out.println("- " + _course.getName() + ";");
          } else
            System.out.println("No courses registered!");

          break;

        case 14:
          if (professors.size() > 0) {
            System.out.println("<----- Professors ----->");
            for(Professor _professor : professors)
              System.out.println("- " + _professor.getName() + ";");
          } else
            System.out.println("No professors registered!");

          break;
        case 15:
          if (cardIDs.size() > 0) {
            System.out.println("<----- Cards ----->");
            for(CardID _cardID : cardIDs)
              System.out.println("- UUID: " + _cardID.getCode() + ";");
          } else
            System.out.println("No cards registered!");

          break;

        default:
          System.out.println("Exiting program...");
          running = false;
          break;
      }
    }
  }

}