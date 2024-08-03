package entities;

import java.util.ArrayList;

public class Course {
  private String name;
  private Professor professor;
  private ArrayList<Student> students;

  public Course(String name) {
    this.name = name;
    students = new ArrayList<Student>();
  }

  public String getName() {
    return name;
  }

  public Professor getProfessor() {
    return professor;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public void setName(String name) {
    if (name.length() > 0)
      this.name = name;
    else
      System.out.println("name cannot be public void");
  }

  public void setProfessor(Professor professor) {
    if (this.getProfessor() == null)
      this.professor = professor;
    else
      throw new Error("Esse Course ja tem professor");
  }

  public void setStudent(Student student) {
    students.add(student);
    student.setCourse(this);
  }

  public void removeStudent(Student student) {
    students.remove(student); 
    student.removeCourse(this);
  }

  public void removeProfessor() {
    if (this.professor != null)
      this.professor = null;
  }
}