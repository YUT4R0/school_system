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
      System.out.println("0. Exit program;");
      System.out.println("1. Cadastrar Student");
      System.out.println("2. Cadastrar Professor");
      System.out.println("3. Cadastrar Course");
      System.out.println("4. Matricular Professor em Course (ministrar)");
      System.out.println("5. Matricular Student em Course");
      System.out.println("6. Desmatricular Student de Course");
      System.out.println("7. Listar students de um Course");
      System.out.println("8. Listar courses de um ProfessorCourse");
      System.out.println("9. Register cardID for a student by name;");
      System.out.println("10. Listar courses de um Student Course CardID");
      System.out.println("11. Remover cardIDs de um Student CardID nome");

      int cmd = Integer.parseInt(sc.readLine("Set command: "));
      switch (cmd) {
        case 1:
          String name = sc.readLine("Student name: ");
          int age = Integer.parseInt(sc.readLine("Student age: "));
          students.add(new Student(name, age));
          System.out.println("Student cadastrado!");
          break;

        case 2:
          String profName = sc.readLine("Prof name: ");
          int profAge = Integer.parseInt(sc.readLine("Prof age: "));
          professors.add(new Professor(profName, profAge));
          System.out.println("Professor cadastrado!");
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
            System.out.println("Professor ou Course nao encontrado, operação negada");

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
            System.out.println("Professor nao encontrado, operação negada");

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
            System.out.println("Student não encontrado ou ja cadastrado, operação negada");

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

        default:
          System.out.println("Exiting program...");
          running = false;
          break;
      }
    }
  }

}